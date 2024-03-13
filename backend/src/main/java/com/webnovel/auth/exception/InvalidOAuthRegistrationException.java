package com.webnovel.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidOAuthRegistrationException extends AuthenticationException {
    public InvalidOAuthRegistrationException(String msg) {
        super(msg);
    }
}
