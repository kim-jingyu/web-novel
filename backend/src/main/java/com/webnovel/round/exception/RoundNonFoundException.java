package com.webnovel.round.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class RoundNonFoundException extends WebnovelException {
    public RoundNonFoundException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "회차 정보가 없습니다."));
    }
}
