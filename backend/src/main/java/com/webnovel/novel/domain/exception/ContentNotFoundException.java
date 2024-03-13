package com.webnovel.novel.domain.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class ContentNotFoundException extends WebnovelException {
    public ContentNotFoundException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "content 데이터가 없습니다."));
    }
}
