package com.webnovel.login.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(value = SnakeCaseStrategy.class)
public class OAuthAccessToken {
    private String accessToken;
    private int expiresIn;
    private int tokenIn;
    private String scope;
    private String refreshToken;
}
