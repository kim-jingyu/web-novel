package com.webnovel.auth.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class InvalidTokenException extends WebnovelException {
    public InvalidTokenException() {
        super(new ErrorCode(FORBIDDEN, "토큰이 유효하지 않습니다."));
    }
}
