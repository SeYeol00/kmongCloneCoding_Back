package com.sparta.kmongclonecoding.domain;


import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity(name="userinfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String businessPart;

    @Column(nullable = false)
    private String job;

    public User(SignupRequestDto signupRequestDto) {
        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();
        this.businessPart = signupRequestDto.getBusinessPart();
        this.job = signupRequestDto.getJob();
    }
}
