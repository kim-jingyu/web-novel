package com.webnovel.global.exception.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(int statusCode, String message) {
}
