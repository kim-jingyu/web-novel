package com.webnovel.login.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor
@Builder
@RedisHash(value = "refreshToken", timeToLive = 1209600)
public class RefreshToken {
    @Id
    private String token;
    private Long memberId;
}
