package com.webnovel.member.exception;

import com.webnovel.global.exception.ErrorCode;
import com.webnovel.global.exception.WebnovelException;
import org.springframework.http.HttpStatus;

public class MemberNonFoundException extends WebnovelException {
    public MemberNonFoundException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "회원 정보가 없습니다."));
    }
}
