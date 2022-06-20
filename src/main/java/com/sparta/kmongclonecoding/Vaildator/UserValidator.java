package com.sparta.kmongclonecoding.Vaildator;

import com.sparta.kmongclonecoding.domain.User;
import com.sparta.kmongclonecoding.dto.LoginRequestDto;
import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import com.sparta.kmongclonecoding.repository.UserRepository;
import com.sparta.kmongclonecoding.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    public void signupValidation(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String passwordCheck = signupRequestDto.getPasswordCheck();
        String businessPart = signupRequestDto.getBusinessPart();
        String job = signupRequestDto.getJob();

        String passwordPattern = "^[a-zA-Z0-9]{4,30}$"; //소문자, 대문자, 숫자를 4~30자 내로 사용 가능.
        boolean isPasswordTrue = Pattern.matches(passwordPattern, password);

        String usernamePattern = "^[a-zA-Z0-9]{7,20}$"; // 소문자, 대문자, 한글, 숫자를 7~20자 내로 가능
        boolean isUsernameTrue = Pattern.matches(usernamePattern, username);

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        if (!isUsernameTrue) {
            throw new IllegalArgumentException("아이디는 소문자, 대문자, 숫자만 가능합니다.");
        }

        if (!isPasswordTrue) {
            throw new IllegalArgumentException("비밀번호는 소문자, 대문자, 숫자를 4~30자로 가능합니다.");
        }

        if (!password.equals(passwordCheck)) {
            throw new IllegalArgumentException("입력하신 두 비밀번호가 일치하지 않습니다.");
        }

        if (businessPart == null) {
            throw new IllegalArgumentException("카테고리를 선택해주세요.");
        }

        if (job == null) {
            throw new IllegalArgumentException("직업을 입력해주세요.");
        }
    }

    public User loginValidator(LoginRequestDto loginRequestDto) {
        User userFoundInDb = userRepository.findUserByUsername(loginRequestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), userFoundInDb.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        return userFoundInDb;
    }



}

