package com.webnovel.login.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class TokenExpiredException extends WebnovelException {
    public TokenExpiredException(String message) {
        super(new ErrorCode(UNAUTHORIZED, message));
    }
}
