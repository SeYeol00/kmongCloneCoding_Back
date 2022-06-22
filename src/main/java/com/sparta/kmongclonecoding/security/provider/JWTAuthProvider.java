package com.sparta.kmongclonecoding.security.provider;


import com.sparta.kmongclonecoding.domain.User;
import com.sparta.kmongclonecoding.repository.UserRepository;
import com.sparta.kmongclonecoding.security.UserDetailsImpl;
import com.sparta.kmongclonecoding.security.jwt.JwtDecoder;
import com.sparta.kmongclonecoding.security.jwt.JwtPreProcessingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class JWTAuthProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        String username = jwtDecoder.decodeUsername(token);

//        User user = userRepository.findUserByUsername(username).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        User user = userRepository.findUserByUsername(username);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
