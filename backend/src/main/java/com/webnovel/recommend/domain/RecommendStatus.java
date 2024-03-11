package com.webnovel.recommend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RecommendStatus {
    ALREADY("누름"),
    NOT_YET("안누름")
    ;

    private final String value;
}
