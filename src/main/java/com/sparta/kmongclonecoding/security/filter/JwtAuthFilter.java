package com.sparta.kmongclonecoding.security.filter;

import com.sparta.kmongclonecoding.security.jwt.HeaderTokenExtractor;
import com.sparta.kmongclonecoding.security.jwt.JwtPreProcessingToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token 을 내려주는 Filter 가 아닌  client 에서 받아지는 Token 을 서버 사이드에서 검증하는 클레스 SecurityContextHolder 보관소에 해당
 * Token 값의 인증 상태를 보관 하고 필요할때 마다 인증 확인 후 권한 상태 확인 하는 기능
 **/
public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {
    private final HeaderTokenExtractor extractor;

    public JwtAuthFilter(
            RequestMatcher requiresAuthenticationRequestMatcher,
            HeaderTokenExtractor extractor
    ) {
        super(requiresAuthenticationRequestMatcher);
        this.extractor = extractor;
    }
    /**
     * JWT 값을 담아주는 변수 TokenPayload
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException, IOException {
        String tokenPayload = request.getHeader("Authorization");
        if (tokenPayload == null) {
            response.sendRedirect("/");
            return null;
        }

        JwtPreProcessingToken jwtToken = new JwtPreProcessingToken(
                extractor.extract(tokenPayload, request));

        return super
                .getAuthenticationManager()
                .authenticate(jwtToken);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        chain.doFilter(
                request,
                response
        );
    }
    /**
     *	<p> 로그인을 한 상태에서 Token값을 주고받는 상황에서 잘못된 Token값이라면 인증이 성공하지 못한 단계 이기 때문에 잘못된 Token값을 제거합니다.</p>
     *	<p>모든 인증받은 Context 값이 삭제 됩니다.</p>
     */
    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException, ServletException {

        SecurityContextHolder.clearContext();

        super.unsuccessfulAuthentication(
                request,
                response,
                failed
        );
    }
}
