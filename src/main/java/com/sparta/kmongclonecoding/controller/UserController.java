package com.sparta.kmongclonecoding.controller;

import com.sparta.kmongclonecoding.dto.LoginRequestDto;
import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import com.sparta.kmongclonecoding.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 가입 요청 처리
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    // 회원 로그인
    @PostMapping("/login")
//    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
//        return userService.login(loginRequestDto, response);
        return userService.login(loginRequestDto);
    }

/*
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        return userService.logout(request);
    }
*/
}
