package com.sparta.kmongclonecoding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
    private String passwordCheck;
    private String businessPart;
    private String job;
//    private BigCategory bigCatergory;
}
