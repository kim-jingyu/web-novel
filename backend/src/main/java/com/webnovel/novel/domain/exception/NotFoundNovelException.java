package com.webnovel.novel.domain.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class NotFoundNovelException extends WebnovelException {
    public NotFoundNovelException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "작품을 찾을 수 없습니다."));
    }
}
