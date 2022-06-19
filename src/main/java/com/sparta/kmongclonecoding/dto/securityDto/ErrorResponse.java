package com.sparta.kmongclonecoding.dto.securityDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ErrorResponse {
    private boolean reLogin;
    private String error;

    public ErrorResponse(String error) {
        this.reLogin = true;
        this.error = error;
    }
}
