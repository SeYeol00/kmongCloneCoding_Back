package com.sparta.kmongclonecoding.dto.securityDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenInfo {
    private String REFRESH_TOKEN;
    private Long refreshTokenExpirationTime;
}
