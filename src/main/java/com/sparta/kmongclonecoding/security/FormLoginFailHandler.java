package com.sparta.kmongclonecoding.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.kmongclonecoding.dto.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginFailHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String errorMsg = "";

        if(exception instanceof UsernameNotFoundException) {
            errorMsg = "존재하지 않는 아이디 입니다.";

        }else if(exception instanceof BadCredentialsException) {
            errorMsg = "비밀번호가 맞지 않습니다.";
        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        LoginResponseDto loginResponseDto = new LoginResponseDto(false, errorMsg);
        String result = mapper.writeValueAsString(loginResponseDto);
        response.getWriter().write(result);

    }
}
