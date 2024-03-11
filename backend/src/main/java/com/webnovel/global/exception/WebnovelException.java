package com.webnovel.global.exception;

import lombok.Getter;

@Getter
public class WebnovelException extends RuntimeException {
    private final ErrorCode errorCode;

    public WebnovelException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }
}
