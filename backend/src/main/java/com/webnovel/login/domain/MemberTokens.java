package com.webnovel.login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class MemberTokens {
    private final String accessToken;
    private final String refreshToken;
}
