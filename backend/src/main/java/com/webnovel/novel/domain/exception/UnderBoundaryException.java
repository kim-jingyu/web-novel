package com.webnovel.novel.domain.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class UnderBoundaryException extends WebnovelException {
    public UnderBoundaryException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "subscribe는 음수가 될 수 없습니다."));
    }
}
