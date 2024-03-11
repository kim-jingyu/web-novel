package com.webnovel.recommend.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class AlreadyRecommendException extends WebnovelException {
    public AlreadyRecommendException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "이미 추천하셨습니다."));
    }
}
