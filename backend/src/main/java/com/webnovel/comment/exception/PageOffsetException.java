package com.webnovel.comment.exception;

import org.springframework.http.HttpStatus;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

public class PageOffsetException extends WebnovelException{
    public PageOffsetException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "페이지 오프셋이 잘못되었습니다."));
    }
}
