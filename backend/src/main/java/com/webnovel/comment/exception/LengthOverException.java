package com.webnovel.comment.exception;

import org.springframework.http.HttpStatus;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;

public class LengthOverException extends WebnovelException{
    public LengthOverException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "댓글 본문 글자수가 255자를 초과했습니다."));
    }
}
