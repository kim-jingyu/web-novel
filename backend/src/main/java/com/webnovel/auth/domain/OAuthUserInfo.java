package com.webnovel.auth.domain;

import java.util.Map;

public interface OAuthUserInfo {
    OAuthProvider getProvider();
    Map<String, Object> getAttributes();
    String getEmail();
}
