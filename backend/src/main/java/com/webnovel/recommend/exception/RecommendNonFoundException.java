package com.webnovel.recommend.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class RecommendNonFoundException extends WebnovelException {
    public RecommendNonFoundException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "추천하지 못했습니다."));
    }
}
