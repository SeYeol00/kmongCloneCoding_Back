package com.sparta.kmongclonecoding.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.kmongclonecoding.dto.LoginResponseDto;
import com.sparta.kmongclonecoding.dto.ResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        LoginResponseDto responseDto = new LoginResponseDto(true, "로그아웃 성공"); // 로그아웃인데 귀찮아서 LoginResponseDto로 씀
        String result =mapper.writeValueAsString(responseDto);
        response.getWriter().write(result);

    }
}
