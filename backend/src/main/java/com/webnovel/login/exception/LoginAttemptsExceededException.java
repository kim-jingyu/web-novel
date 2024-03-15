package com.webnovel.login.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class LoginAttemptsExceededException extends WebnovelException {
    public LoginAttemptsExceededException() {
        super(new ErrorCode(UNAUTHORIZED, "로그인 시도 횟수를 초과하셨습니다."));
    }
}
