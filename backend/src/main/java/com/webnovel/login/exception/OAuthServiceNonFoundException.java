package com.webnovel.login.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class OAuthServiceNonFoundException extends WebnovelException {
    public OAuthServiceNonFoundException() {
        super(new ErrorCode(UNAUTHORIZED, "OAuth 서비스를 찾을 수 없습니다."));
    }
}
