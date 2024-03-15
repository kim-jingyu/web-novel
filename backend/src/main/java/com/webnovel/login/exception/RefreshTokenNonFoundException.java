package com.webnovel.login.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class RefreshTokenNonFoundException extends WebnovelException {
    public RefreshTokenNonFoundException() {
        super(new ErrorCode(UNAUTHORIZED, "리프레시 토큰 정보가 없습니다."));
    }
}
