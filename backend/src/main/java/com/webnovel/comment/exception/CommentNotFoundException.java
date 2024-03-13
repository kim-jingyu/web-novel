package com.webnovel.comment.exception;

import com.webnovel.global.exception.WebnovelException;
import com.webnovel.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends WebnovelException{
    public CommentNotFoundException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "댓글 작성에 실패했습니다."));
    }
}
