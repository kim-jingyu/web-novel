package com.webnovel.login.presentation;

import com.webnovel.auth.Auth;
import com.webnovel.auth.Member;
import com.webnovel.login.domain.LoginMember;
import com.webnovel.login.domain.MemberTokens;
import com.webnovel.login.dto.AccessTokenResponseDto;
import com.webnovel.login.dto.LoginRequestDto;
import com.webnovel.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login/{provider}")
    public ResponseEntity<AccessTokenResponseDto> login(@PathVariable String provider, @RequestBody LoginRequestDto request) {
        MemberTokens memberTokens = loginService.login(provider, request.getCode());

        ResponseCookie cookie = ResponseCookie.from("refresh-token", memberTokens.getRefreshToken())
                .path("/")
                .httpOnly(true)
                .secure(false)
                .maxAge(Duration.ofDays(14))
                .sameSite("Strict")
                .build();

        return ResponseEntity
                .status(CREATED)
                .header(SET_COOKIE, cookie.toString())
                .body(AccessTokenResponseDto.of(memberTokens.getAccessToken()));
    }

    @PostMapping("/token")
    public ResponseEntity<AccessTokenResponseDto> reissueAccessToken(@CookieValue("refresh-token") String refreshToken, @RequestHeader("Authorization") String authorizationHeader) {
        String reissuedAccessToken = loginService.reissueAccessToken(refreshToken, authorizationHeader);
        return ResponseEntity
                .status(CREATED)
                .body(AccessTokenResponseDto.of(reissuedAccessToken));
    }

    @Member
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@Auth LoginMember member, @CookieValue("refresh-token") String refreshToken) {
        loginService.removeRefreshToken(refreshToken);
        return ResponseEntity
                .noContent()
                .build();
    }
}
