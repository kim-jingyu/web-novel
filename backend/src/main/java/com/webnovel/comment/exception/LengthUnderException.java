package com.webnovel.comment.exception;

import org.springframework.http.HttpStatus;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

public class LengthUnderException extends WebnovelException{
    public LengthUnderException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "댓글 본문 글자수가 10자 미만입니다."));
    }
}
