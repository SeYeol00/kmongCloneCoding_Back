package com.sparta.kmongclonecoding.controller;

import com.sparta.kmongclonecoding.dto.ResponseDto;
import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import com.sparta.kmongclonecoding.dto.SignupResponseDto;
import com.sparta.kmongclonecoding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/api/signup")
    public SignupResponseDto signUp(@RequestBody SignupRequestDto signUpRequestDto) {
        return userService.signUp(signUpRequestDto);
    }


}
