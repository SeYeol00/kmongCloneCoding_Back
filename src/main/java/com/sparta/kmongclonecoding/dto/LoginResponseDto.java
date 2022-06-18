package com.sparta.kmongclonecoding.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final boolean ok;
    private final String message;

    public LoginResponseDto(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }
}