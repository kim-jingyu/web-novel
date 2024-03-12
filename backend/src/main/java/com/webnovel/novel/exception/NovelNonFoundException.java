package com.webnovel.novel.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class NovelNonFoundException extends WebnovelException {
    public NovelNonFoundException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "작품이 없습니다."));
    }
}
