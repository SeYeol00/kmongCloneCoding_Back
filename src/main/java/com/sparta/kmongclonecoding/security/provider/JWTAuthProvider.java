package com.sparta.kmongclonecoding.security.provider;


import com.mini.babmeokeon.model.User;
import com.mini.babmeokeon.repository.UserRepository;
import com.mini.babmeokeon.security.UserDetailsImpl;
import com.mini.babmeokeon.security.jwt.JwtDecoder;
import com.mini.babmeokeon.security.jwt.JwtPreProcessingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

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


        User user = userRepository.findByUsername(username);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
