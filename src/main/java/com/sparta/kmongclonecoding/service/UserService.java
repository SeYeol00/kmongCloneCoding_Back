package com.sparta.kmongclonecoding.service;

import com.sparta.kmongclonecoding.domain.User;
import com.sparta.kmongclonecoding.dto.LoginRequestDto;
import com.sparta.kmongclonecoding.dto.LoginResponseDto;
import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import com.sparta.kmongclonecoding.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@AllArgsConstructor
public class UserService {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TYPE = "Bearer";
    /*
        private final JwtTokenProvider jwtTokenProvider;
        private final PasswordEncoder passwordEncoder;
        private final BCryptPasswordEncoder encoder;

        private final RedisTemplate<String, Object> redisTemplate;
    */
    private final UserRepository userRepository;

    // 회원가입
    public ResponseEntity<?> signup(SignupRequestDto signupRequestDto) {
        if (signupRequestDto == null) {
            throw new IllegalArgumentException("모든 항목을 채워주세요.");
        } else if (userRepository.existsByUsername(signupRequestDto.getUsername())) {
            throw new IllegalArgumentException("아이디가 존재합니다.");
        }
        return new ResponseEntity<>(userRepository.save(new User(signupRequestDto)), HttpStatus.OK);
    }

    // 로그인
//    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findUserByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
/*
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        HeaderResponseDto headerResponseDto = jwtTokenProvider.createToken(user.getEmail());
        setRedisTemplate(user.getEmail(), headerResponseDto.getREFRESH_TOKEN(), headerResponseDto.getRefreshTokenExpirationTime());
        addMultiHeader(response, headerResponseDto);
*/
        return new LoginResponseDto();
    }

    public ResponseEntity<?> logout(HttpServletRequest request) {
//        logoutProcess(request);
        return new ResponseEntity<>("로그아웃 되었습니다.", HttpStatus.OK);
    }


}
