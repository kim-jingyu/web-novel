package com.webnovel.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AccessTokenResponseDto {
    private String accessToken;

    public static AccessTokenResponseDto of(String accessToken) {
        return AccessTokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
