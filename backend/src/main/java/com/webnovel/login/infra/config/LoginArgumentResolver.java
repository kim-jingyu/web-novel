package com.webnovel.login.infra.config;

import com.webnovel.auth.Auth;
import com.webnovel.global.exception.BadRequestException;
import com.webnovel.login.domain.LoginMember;
import com.webnovel.login.domain.MemberTokens;
import com.webnovel.login.domain.repository.RefreshTokenRepository;
import com.webnovel.login.exception.RefreshTokenNonFoundException;
import com.webnovel.login.infra.providers.JwtProvider;
import com.webnovel.login.infra.supporter.BearTokenExtractor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String REFRESH_TOKEN = "refresh-token";
    private final JwtProvider jwtProvider;
    private final BearTokenExtractor extractor;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter
                .withContainingClass(Long.class)
                .hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null) {
            throw new BadRequestException();
        }

        try {
            String refreshToken = getRefreshToken(request);
            String accessToken = extractor.getAccessToken(webRequest.getHeader(AUTHORIZATION));
            jwtProvider.validateToken(new MemberTokens(accessToken, refreshToken));
            Long memberId = Long.valueOf(jwtProvider.getSubject(accessToken));
            return LoginMember.member(memberId);
        } catch (RefreshTokenNonFoundException e) {
            return LoginMember.guest();
        }
    }

    private String getRefreshToken(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.equals(REFRESH_TOKEN) && refreshTokenRepository.existsById(cookie.getValue()))
                .findFirst()
                .orElseThrow(RefreshTokenNonFoundException::new)
                .getValue();
    }
}
