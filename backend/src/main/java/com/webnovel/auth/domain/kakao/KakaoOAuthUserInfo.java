package com.webnovel.auth.domain.kakao;

import com.webnovel.auth.domain.OAuthProvider;
import com.webnovel.auth.domain.OAuthUserInfo;
import lombok.Getter;

import java.util.Map;

import static com.webnovel.auth.domain.OAuthProvider.*;

@Getter
public class KakaoOAuthUserInfo implements OAuthUserInfo {
    private final String accessToken;
    private final Map<String, Object> attributes;
    private final String id;
    private final String email;
    private final String name;
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String profileImageUrl;

    public KakaoOAuthUserInfo(String accessToken, Map<String, Object> attributes) {
        this.accessToken = accessToken;
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");
        this.attributes = kakaoProfile;

        this.id = ((Long) attributes.get("id")).toString();
        this.email = (String) kakaoAccount.get("email");

        this.name = null;
        this.firstName = null;
        this.lastName = null;
        this.nickName = (String) attributes.get("nickname");
        ;
        this.profileImageUrl = (String) attributes.get("profile_image_url");

        this.attributes.put("id", id);
        this.attributes.put("email", this.email);
    }

    @Override
    public OAuthProvider getProvider() {
        return KAKAO;
    }
}
