package com.webnovel.global.config;

import com.webnovel.login.infra.config.credentials.GoogleCredentials;
import com.webnovel.login.infra.config.credentials.JwtCredentials;
import com.webnovel.login.infra.config.credentials.KakaoCredentials;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        GoogleCredentials.class,
        KakaoCredentials.class,
        JwtCredentials.class
})
public class CredentialConfig {
}
