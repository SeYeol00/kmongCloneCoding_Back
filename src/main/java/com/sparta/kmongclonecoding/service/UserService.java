package com.sparta.kmongclonecoding.service;


import com.sparta.kmongclonecoding.Vaildator.UserValidator;
import com.sparta.kmongclonecoding.domain.User;
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
    private final UserValidator userValidator;
    
    public SignupResponseDto signUp(SignupRequestDto signupRequestDto) {
        userValidator.signupValidator(signupRequestDto);

        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        signupRequestDto.setPassword(passwordEncoder.encode(password));

        User user = User.builder()
                .username(signupRequestDto.getUsername())
                .password(signupRequestDto.getPassword())
                .businessPart(signupRequestDto.getBusinessPart())
                .job(signupRequestDto.getJob())
                .kakaoId(null)
                .build();

        userRepository.save(user);

        return new SignupResponseDto(true, "회원가입 성공");
    }
}