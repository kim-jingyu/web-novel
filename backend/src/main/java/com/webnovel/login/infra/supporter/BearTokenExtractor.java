package com.webnovel.login.infra.supporter;

import com.webnovel.login.exception.InvalidTokenException;
import org.springframework.stereotype.Component;

@Component
public class BearTokenExtractor {
    private static final String BEARER_TYPE = "Bearer ";
    private static final String BEARER_JWT_REGEX = "^Bearer [A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$";

    public String getAccessToken(String header) {
        if (header == null || !header.startsWith(BEARER_TYPE) || !header.matches(BEARER_JWT_REGEX)) {
            throw new InvalidTokenException();
        }
        return header.substring(BEARER_TYPE.length()).trim();
    }
}
