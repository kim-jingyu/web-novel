package com.webnovel.auth.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class InvalidExpirationException extends WebnovelException {
    public InvalidExpirationException() {
        super(new ErrorCode(UNAUTHORIZED, "토큰이 만료되었습니다."));
    }
}
