package com.webnovel.login.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class InvalidTokenException extends WebnovelException {
    public InvalidTokenException() {
        super(new ErrorCode(UNAUTHORIZED, "토큰이 유효하지 않습니다."));
    }
}
