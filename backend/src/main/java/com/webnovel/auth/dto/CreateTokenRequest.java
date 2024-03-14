package com.webnovel.auth.dto;

import lombok.Data;

@Data
public class CreateTokenRequest {
    private String refreshToken;
}
