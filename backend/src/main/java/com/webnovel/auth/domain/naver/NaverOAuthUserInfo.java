package com.webnovel.auth.domain.naver;

import com.webnovel.auth.domain.OAuthProvider;
import com.webnovel.auth.domain.OAuthUserInfo;
import lombok.Getter;

import java.util.Map;

@Getter
public class NaverOAuthUserInfo implements OAuthUserInfo {
    private final String accessToken;
    private final Map<String, Object> attributes;
    private final String id;
    private final String email;
    private final String name;
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String profileImageUrl;

    public NaverOAuthUserInfo(String accessToken, Map<String, Object> attributes) {
        this.accessToken = accessToken;
        this.attributes = (Map<String, Object>) attributes.get("response");
        this.id = (String) this.attributes.get("id");
        this.email = (String) this.attributes.get("email");
        this.name = (String) this.attributes.get("name");
        this.firstName = null;
        this.lastName = null;
        this.nickName = (String) attributes.get("nickname");
        this.profileImageUrl = (String) attributes.get("profile_image");
    }

    @Override
    public OAuthProvider getProvider() {
        return OAuthProvider.NAVER;
    }
}
