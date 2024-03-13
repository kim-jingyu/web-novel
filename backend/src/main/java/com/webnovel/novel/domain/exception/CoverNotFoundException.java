package com.webnovel.novel.domain.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class CoverNotFoundException extends WebnovelException {
    public CoverNotFoundException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "작품 생성에 실패했습니다(cover)."));
    }
}
