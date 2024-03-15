package com.webnovel.login.domain;

import com.webnovel.login.domain.userinfo.OAuthUserInfo;
import org.springframework.web.client.RestTemplate;

public interface OAuthProvider {
    RestTemplate restTemplate = new RestTemplate();

    boolean is(String name);
    OAuthUserInfo getUserInfo(String code);
}
