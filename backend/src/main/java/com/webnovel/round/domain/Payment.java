package com.webnovel.round.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Payment {
    FREE("무료"),
    CHARGED("유료"),
    ;

    private final String value;

}
