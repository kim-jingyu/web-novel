package com.webnovel.global.exception;

import org.springframework.http.HttpStatus;

public record ErrorCode(HttpStatus status, String message) {
}
