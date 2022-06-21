package com.sparta.kmongclonecoding.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private final boolean ok;
    private final String message;

    public SignupResponseDto(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }
}