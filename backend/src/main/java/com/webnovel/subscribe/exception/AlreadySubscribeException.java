package com.webnovel.subscribe.exception;

import org.springframework.http.HttpStatus;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

public class AlreadySubscribeException extends WebnovelException {
    public AlreadySubscribeException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "이미 구독했습니다."));
    }
}
