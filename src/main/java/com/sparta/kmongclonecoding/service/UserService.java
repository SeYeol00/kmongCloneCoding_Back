package com.sparta.kmongclonecoding.service;


import com.sparta.kmongclonecoding.dto.ResponseDto;
import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import com.sparta.kmongclonecoding.dto.SignupResponseDto;
import com.sparta.kmongclonecoding.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public SignupResponseDto signUp(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String passwordCheck = signupRequestDto.getPasswordCheck();
        String businessPart = signupRequestDto.getBusinessPart();
        String job = signupRequestDto.getJob();

        if (username == null || password == null || passwordCheck == null || businessPart == null || job == null) {
            return new SignupResponseDto(false, "모두 입력해 주세요");
        }
        if (!username.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")){
            return new SignupResponseDto(false, "이메일 형식이 아닙니다.");
        }
        if (password.length() < 8 || password.length() > 20 ) {
            return new SignupResponseDto(false,"비밀번호는 최소 8글자 이상, 20글자 이하로 작성해 주세요.");
        }
        if (!password.matches("^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,20}$")) {
            return new SignupResponseDto(false,"비밀번호는 영문 + 숫자로 작성해 주세요.");
        }
        if (!password.equals(passwordCheck)) {
            return new SignupResponseDto(false,"확인용 비밀번호가 일치하지 않습니다.");
        }
        if (businessPart.length() < 4 || businessPart.length() > 20 ) {
            return new SignupResponseDto(false,"닉네임은 최소 4글자 이상, 20글자 이하로 작성해 주세요.");
        }
        if (!businessPart.matches("^[0-9a-zA-Z]{4,20}$")) {
            return new SignupResponseDto(false,"닉네임은 영문 + 숫자로 작성해 주세요.");
        }
        if (!this.checkId(username).isResponse()) {
            return new SignupResponseDto(false, "아이디 중복");
        }
        if (!this.checkNickname(businessPart).isResponse()) {
            return new SignupResponseDto(false, "닉네임 중복");
        }
        signupRequestDto.setPassword(passwordEncoder.encode(password));
        User user = new User(signupRequestDto);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return new SignupResponseDto(false, "알수 없는 에러 " + e.getMessage());
        }

        return new SignupResponseDto<>(true, "회원가입 성공");
    }

    public ResponseDto<Object> checkId(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            if (!username.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")){
                return new ResponseDto<>(false, "이메일 형식이 아닙니다.");
            }
            return new ResponseDto<>(true);
        }
        return new ResponseDto<>(false, "아이디 중복");
    }

    public ResponseDto<Object> checkNickname(String nickname) {
        User user = userRepository.findByNickname(nickname);
        if (user == null) {
            if (nickname.length() < 4 || nickname.length() > 20 ) {
                return new ResponseDto<>(false,"닉네임은 최소 4글자 이상, 20글자 이하로 작성해 주세요.");
            }
            if (!nickname.matches("^[0-9a-zA-Z]{4,20}$")) {
                return new ResponseDto<>(false,"닉네임은 영문 + 숫자로 작성해 주세요.");
            }
            return new ResponseDto<>(true);
        }
        return new ResponseDto<>(false, "닉네임 중복");
    }
}
