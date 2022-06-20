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
    //https://accounts.google.com/o/oauth2/v2/auth?client_id=693007930110-5u66i5c992figci0e3oh3n53sm8q6hb7.apps.googleusercontent.com&redirect_uri=http://localhost:3000/redirect/google&response_type=code&scope=email%20profile%20openid&access_type=offline

/*
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        return userService.logout(request);
    }
*/
}
