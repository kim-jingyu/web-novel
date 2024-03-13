package com.webnovel.auth.domain;

import lombok.Getter;

@Getter
public class AuthToken {
    private String accessToken;
    private String refreshToken;

    public AuthToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
