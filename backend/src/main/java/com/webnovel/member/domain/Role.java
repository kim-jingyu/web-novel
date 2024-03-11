package com.webnovel.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("일반사용자"),
    WRITER("작가"),
    ADMIN("관리자");

    private final String value;

}
