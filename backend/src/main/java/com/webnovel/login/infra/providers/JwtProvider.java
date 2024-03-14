package com.webnovel.login.infra.providers;

import com.webnovel.login.domain.MemberTokens;
import com.webnovel.login.exception.InvalidTokenException;
import com.webnovel.login.exception.TokenExpiredException;
import com.webnovel.login.infra.config.credentials.JwtCredentials;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);

    private final JwtCredentials jwtCredentials;

    public MemberTokens issueLoginTokens(String subject) {
        String refreshToken = makeToken("", REFRESH_TOKEN_DURATION);
        String accessToken = makeToken(subject, ACCESS_TOKEN_DURATION);
        return new MemberTokens(refreshToken, accessToken);
    }

    // JWT 토큰 유효성 검증 메서드

    public void validateToken(MemberTokens memberTokens) {
        validateRefreshToken(memberTokens.getRefreshToken());
        validateAccessToken(memberTokens.getAccessToken());
    }
    public String getSubject(String token) {
        return parseToken(token)
                .getPayload()
                .getSubject();
    }

    public String reissueAccessToken(String subject) {
        return makeToken(subject, ACCESS_TOKEN_DURATION);
    }

    private String makeToken(String subject, Duration expireDuration) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expireDuration.toMillis());

        return Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(jwtCredentials.getSecretKey())
                .compact();
    }

    private void validateRefreshToken(String refreshToken) {
        try {
            parseToken(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("액세스 토큰의 기한이 만료되었습니다.");
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException();
        }
    }

    private void validateAccessToken(String accessToken) {
        try {
            parseToken(accessToken);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("리프레쉬 토큰의 기한이 만료되었습니다.");
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException();
        }
    }

    private Jws<Claims> parseToken(String refreshToken) {
        return Jwts.parser()
                .verifyWith(jwtCredentials.getSecretKey())
                .build()
                .parseSignedClaims(refreshToken);
    }
}
