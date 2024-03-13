package com.webnovel.auth.domain;

import com.webnovel.auth.domain.google.GoogleOAuthUserInfo;
import com.webnovel.auth.domain.kakao.KakaoOAuthUserInfo;
import com.webnovel.auth.domain.naver.NaverOAuthUserInfo;
import com.webnovel.auth.exception.InvalidOAuthRegistrationException;

import java.util.Map;

public class OAuthUserInfoFactory {
    public static OAuthUserInfo getOAuthUserInfo(String registrationId, String accessToken, Map<String, Object> attributes) {
        if (OAuthProvider.GOOGLE.getRegistrationId().equals(registrationId)) {
            return new GoogleOAuthUserInfo(accessToken, attributes);
        }
        if (OAuthProvider.KAKAO.getRegistrationId().equals(registrationId)) {
            return new KakaoOAuthUserInfo(accessToken, attributes);
        }
        if (OAuthProvider.NAVER.getRegistrationId().equals(registrationId)) {
            return new NaverOAuthUserInfo(accessToken, attributes);
        }
        throw new InvalidOAuthRegistrationException("잘못된 인증 방식입니다.");
    }
}
