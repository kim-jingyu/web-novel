package com.webnovel.global.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends WebnovelException{
    public BadRequestException() {
        super(new ErrorCode(BAD_REQUEST, "잘못된 요청입니다."));
    }
}
