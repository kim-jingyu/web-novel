package com.webnovel.login.infra.config.credentials;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;

import static java.nio.charset.StandardCharsets.UTF_8;

@Getter
@ConfigurationProperties(prefix = "jwt.secret-key")
public class JwtCredentials {
    private SecretKey secretKey;

    public void setSecretKey(String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(UTF_8));
    }
}
