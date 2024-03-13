package com.webnovel.auth.domain.google;

import com.webnovel.auth.domain.OAuthProvider;
import com.webnovel.auth.domain.OAuthUserInfo;
import lombok.Getter;

import java.util.Map;

import static com.webnovel.auth.domain.OAuthProvider.GOOGLE;

@Getter
public class GoogleOAuthUserInfo implements OAuthUserInfo {
    private final String accessToken;
    private final Map<String, Object> attributes;
    private final String id;
    private final String email;
    private final String name;
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String profileImageUrl;

    public GoogleOAuthUserInfo(String accessToken, Map<String, Object> attributes) {
        this.accessToken = accessToken;
        this.attributes = attributes;
        this.id = (String) attributes.get("sub");
        this.email = (String) attributes.get("email");
        this.name = (String) attributes.get("name");
        this.firstName = (String) attributes.get("given_name");
        this.lastName = (String) attributes.get("family_name");
        this.nickName = null;
        this.profileImageUrl = (String) attributes.get("picture");
    }

    @Override
    public OAuthProvider getProvider() {
        return GOOGLE;
    }
}
