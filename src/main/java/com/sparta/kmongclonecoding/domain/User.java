package com.sparta.kmongclonecoding.domain;


import com.sparta.kmongclonecoding.dto.SignupRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Entity(name="userinfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String businessPart;

    @Column(nullable = true)
    private String job;

    @Column(nullable = true,unique = true)//중복 불가 널값 허용
    private Long kakaoId;

    public User(SignupRequestDto signupRequestDto) {
        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();
        this.businessPart = signupRequestDto.getBusinessPart();
        this.job = signupRequestDto.getJob();
        this.kakaoId = null;
    }

    public User(String username, String password,String nickname, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.businessPart = null;
        this.job = null;
        this.kakaoId = kakaoId;
    }

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




