package com.sparta.kmongclonecoding.domain;


import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
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
}


/*
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "userInfo")
@Builder
public class User extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String businessPart;

    @Column(nullable = false)
    private String job;

    public User(SignupRequestDto signupRequestDto) {
        super();
    }

//    @OneToMany
//    @JoinColumn(name = "BigCategory")
//    private BigCategory bigCategory;

//    public User(SignupRequestDto dto) {
//        this.nickname = dto.getNickname();
//        this.email = dto.getEmail();
//        this.password = dto.getPassword();
//    }
}
*/




