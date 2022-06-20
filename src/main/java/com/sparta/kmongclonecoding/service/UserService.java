package com.sparta.kmongclonecoding.service;

import com.sparta.kmongclonecoding.Vaildator.UserValidator;
import com.sparta.kmongclonecoding.domain.User;
import com.sparta.kmongclonecoding.dto.LoginRequestDto;
import com.sparta.kmongclonecoding.dto.LoginResponseDto;
import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import com.sparta.kmongclonecoding.dto.securityDto.HeaderResponseDto;
import com.sparta.kmongclonecoding.dto.securityDto.RefreshTokenInfo;
import com.sparta.kmongclonecoding.repository.UserRepository;
import com.sparta.kmongclonecoding.security.UserDetailsImpl;
import com.sparta.kmongclonecoding.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TYPE = "Bearer";
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final BCryptPasswordEncoder encoder;
    private final RedisTemplate<String, Object> redisTemplate;

    // refreshToken store
    public void setRedisTemplate(String username, String refreshToken, Long refreshTokenExpirationTime) {
        redisTemplate.opsForValue()
                .set("RefreshToken:" + username, refreshToken, refreshTokenExpirationTime, TimeUnit.MILLISECONDS);
    }

    //tokens add to header
    public void addMultiHeader(HttpServletResponse response, HeaderResponseDto headerResponseDto) {
        response.addHeader(AUTHORIZATION_HEADER, BEARER_TYPE + " " + headerResponseDto.getACCESS_TOKEN());
        response.addHeader("RefreshToken", BEARER_TYPE + " " + headerResponseDto.getREFRESH_TOKEN());
        response.addHeader("RefreshTokenExpirationTime", String.valueOf(headerResponseDto.getRefreshTokenExpirationTime()));
    }

    public ResponseEntity<?> signup(SignupRequestDto signupRequestDto) {
        userValidator.signupValidation(signupRequestDto);

        signupRequestDto.setPassword(encoder.encode(signupRequestDto.getPassword()));
        return new ResponseEntity<>(userRepository.save(new User(signupRequestDto)), HttpStatus.OK);
    }

    public ResponseEntity<?> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        User user = userValidator.loginValidator(loginRequestDto);
        User userFoundInDb = userRepository.findUserByUsername(loginRequestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), userFoundInDb.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        HeaderResponseDto headerResponseDto = jwtTokenProvider.createToken(userFoundInDb.getUsername());

        setRedisTemplate(
                userFoundInDb.getUsername(),
                headerResponseDto.getREFRESH_TOKEN(),
                headerResponseDto.getRefreshTokenExpirationTime()
        );

        addMultiHeader(response, headerResponseDto);

        return new ResponseEntity<>(new LoginResponseDto(true , "로그인 되었습니다."), HttpStatus.OK);
    }

    public void reIssuance(UserDetailsImpl userDetails, HttpServletRequest request, HttpServletResponse response) {
        String token = jwtTokenProvider.resolveToken(request);
        if (!(jwtTokenProvider.getExpiration(token) <= 180000)) return;
        RefreshTokenInfo tokenInfo = jwtTokenProvider.resolveRefreshToken(request);
        // refresh 토큰 검증
        if (!jwtTokenProvider.validateToken(tokenInfo.getREFRESH_TOKEN())) {
            throw new RuntimeException("다시 로그인 해주세요.");
        }

        // redis 에서 저장된 refresh 가져오기
        String refreshToken = (String) redisTemplate.opsForValue().get("RT:" + userDetails.getUsername());
        if (ObjectUtils.isEmpty(refreshToken)) {
            throw new RuntimeException("잘못된 요청입니다.");
        } else if (!refreshToken.equals(tokenInfo.getREFRESH_TOKEN())) {
            throw new RuntimeException("다시 로그인 해주세요.");
        }

        HeaderResponseDto headerDto = jwtTokenProvider.createToken(userDetails.getUsername());
        setRedisTemplate(
                userDetails.getUsername(),
                tokenInfo.getREFRESH_TOKEN(),
                tokenInfo.getRefreshTokenExpirationTime()
        );

        addMultiHeader(response, headerDto);
    }

    public ResponseEntity<?> logout(HttpServletRequest request) {
        // 1. Access Token 검증
        String token = jwtTokenProvider.resolveToken(request);
        if (!jwtTokenProvider.validateToken(token)) {
            throw new RuntimeException("잘못된 요청입니다.");
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제.
        if (redisTemplate.opsForValue().get("RefreshToken:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RefreshToken:" + authentication.getName());
        }

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        Long expiration = jwtTokenProvider.getExpiration(token);
        redisTemplate.opsForValue()
                .set(token, "logout", expiration, TimeUnit.MILLISECONDS);

        return new ResponseEntity<>("로그아웃 되었습니다.", HttpStatus.OK);
    }
}