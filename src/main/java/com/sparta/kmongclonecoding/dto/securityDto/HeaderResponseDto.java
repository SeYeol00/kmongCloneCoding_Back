package com.sparta.kmongclonecoding.dto.securityDto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HeaderResponseDto {
    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;
    private String grantType;
    private Long refreshTokenExpirationTime;
}