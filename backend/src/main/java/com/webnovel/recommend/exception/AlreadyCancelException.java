package com.webnovel.recommend.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class AlreadyCancelException extends WebnovelException {
    public AlreadyCancelException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "이미 취소하셨습니다."));
    }
}
