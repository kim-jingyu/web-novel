package com.webnovel.auth.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class InvalidAuthorizationException extends WebnovelException {
    public InvalidAuthorizationException() {
        super(new ErrorCode(HttpStatus.UNAUTHORIZED, "권한이 부여되지 않은 사용자입니다."));
    }
}
