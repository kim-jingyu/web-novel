package com.webnovel.subscribe.exception;

import org.springframework.http.HttpStatus;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

public class SubscribeFailException extends WebnovelException {
    public SubscribeFailException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "구독에 실패했습니다."));
    }
}
