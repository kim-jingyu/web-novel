package com.webnovel.login.domain.userinfo.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webnovel.login.domain.userinfo.OAuthUserInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class KakaoUserInfo implements OAuthUserInfo {
    @JsonProperty("id")
    private String email;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getNickName() {
        return kakaoAccount.kakaoProfile.nickname;
    }

    @Override
    public String getImageUrl() {
        return kakaoAccount.kakaoProfile.image;
    }

    @NoArgsConstructor(access = PRIVATE)
    private static class KakaoAccount {
        @JsonProperty("profile")
        private KakaoProfile kakaoProfile;
    }

    @NoArgsConstructor(access = PRIVATE)
    private static class KakaoProfile {
        @JsonProperty("nickname")
        private String nickname;
        @JsonProperty("profile_image_url")
        private String image;
    }
}
