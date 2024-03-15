package com.webnovel.login.domain.userinfo.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webnovel.login.domain.userinfo.OAuthUserInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class GoogleUserInfo implements OAuthUserInfo {
    @JsonProperty("id")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("profile_image_url")
    private String picture;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getNickName() {
        return name;
    }

    @Override
    public String getImageUrl() {
        return picture;
    }
}
