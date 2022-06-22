package com.sparta.kmongclonecoding.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.kmongclonecoding.dto.KakaoUserResponseDto;
import com.sparta.kmongclonecoding.dto.ResponseDto;
import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import com.sparta.kmongclonecoding.dto.SignupResponseDto;
import com.sparta.kmongclonecoding.dto.google.GoogleUserResponseDto;
import com.sparta.kmongclonecoding.service.GoogleUserService;
import com.sparta.kmongclonecoding.service.KakaoUserService;
import com.sparta.kmongclonecoding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    private final GoogleUserService googleUserService;

    private  final KakaoUserService kakaoUserService;

    //회원가입
    @PostMapping("/api/signup")
    public SignupResponseDto signUp(@RequestBody SignupRequestDto signUpRequestDto) {
        return userService.signUp(signUpRequestDto);
    }

    @GetMapping("/api/user/google/callback")
    public ResponseDto<GoogleUserResponseDto> googleLogin(@RequestParam String code, HttpServletResponse httpServletResponse) throws JsonProcessingException {
        GoogleUserResponseDto googleUserResponseDto = googleUserService.googleLogin(code);
        httpServletResponse.addHeader("Authorization",googleUserResponseDto.getToken());
        return ResponseDto.<GoogleUserResponseDto>builder()
                .status(HttpStatus.OK.toString())
                .message("구글 소셜 로그인 요청")
                .data(googleUserResponseDto)
                .build();
    }
    //https://accounts.google.com/o/oauth2/v2/auth?client_id=693007930110-5u66i5c992figci0e3oh3n53sm8q6hb7.apps.googleusercontent.com&redirect_uri=http://localhost:8080/api/user/google/callback&response_type=code&scope=email%20profile%20openid&access_type=offline
    @GetMapping("/user/kakao/callback")
    public ResponseEntity<KakaoUserResponseDto>  kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        KakaoUserResponseDto kakaoUserResponseDto = kakaoUserService.kakaoLogin(code, response);
        return ResponseEntity.ok().body(kakaoUserResponseDto);
    }
    //https://kauth.kakao.com/oauth/authorize?client_id=346b2f15b0bcf829529a506449139680&redirect_uri=http://localhost:8080/user/kakao/callback&response_type=code




}
