package com.webnovel.login.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class InvalidAuthCodeException extends WebnovelException {
    public InvalidAuthCodeException() {
        super(new ErrorCode(HttpStatus.UNAUTHORIZED, "인증 코드가 유효하지 않습니다."));
    }
}
