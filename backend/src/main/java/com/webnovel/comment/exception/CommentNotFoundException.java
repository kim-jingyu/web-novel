package com.webnovel.comment.exception;

import com.webnovel.global.exception.WebnovelException;
import com.webnovel.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends WebnovelException{
    public CommentNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "댓글을 찾지 못했습니다."));
    }
}
