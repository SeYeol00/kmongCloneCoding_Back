package com.sparta.kmongclonecoding.Vaildator;

import com.sparta.kmongclonecoding.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;
    // FIXME: 2022/06/18 
}