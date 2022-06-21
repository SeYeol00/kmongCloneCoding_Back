package com.sparta.kmongclonecoding.Vaildator;

import com.sparta.kmongclonecoding.domain.User;
import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import com.sparta.kmongclonecoding.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void signupValidator(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String passwordCheck = signupRequestDto.getPasswordCheck();
        String businessPart = signupRequestDto.getBusinessPart();
        String job = signupRequestDto.getJob();

        String usernamePattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"; // 이메일 패턴. usernamePattern이라 했지만 이메일이 맞음.
        String passwordPattern = "^[a-zA-Z0-9]{4,20}$"; //소문자, 대문자, 숫자를 6~20자 내로 사용 가능.
        boolean isPasswordTrue = Pattern.matches(passwordPattern, password);

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        if (!Pattern.matches(usernamePattern, username)) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }

        if (!isPasswordTrue) {
            throw new IllegalArgumentException("비밀번호는 소문자, 대문자, 숫자를 4~20자로 가능합니다.");
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
}
