package com.sparta.kmongclonecoding.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.kmongclonecoding.dto.LoginRequestDto;
import com.sparta.kmongclonecoding.dto.LoginResponseDto;
import com.sparta.kmongclonecoding.dto.ResponseDto;
import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import com.sparta.kmongclonecoding.dto.google.GoogleUserResponseDto;
import com.sparta.kmongclonecoding.service.GoogleUserService;
import com.sparta.kmongclonecoding.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    private final GoogleUserService googleUserService;

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

    @GetMapping("/api/user/google/callback")
    public ResponseDto<GoogleUserResponseDto> googleLogin(@RequestParam String code) throws JsonProcessingException {
        return ResponseDto.<GoogleUserResponseDto>builder()
                .status(HttpStatus.OK.toString())
                .message("구글 소셜 로그인 요청")
                .data(googleUserService.googleLogin(code))
                .build();
    }

/*
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        return userService.logout(request);
    }
*/
}
