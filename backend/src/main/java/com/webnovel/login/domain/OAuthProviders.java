package com.webnovel.login.domain;

import com.webnovel.login.exception.OAuthServiceNonFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuthProviders {
    private final List<OAuthProvider> providers;

    public OAuthProvider getProvider(String providerName) {
        return providers.stream()
                .filter(oAuthProvider -> oAuthProvider.is(providerName))
                .findFirst()
                .orElseThrow(OAuthServiceNonFoundException::new);
    }
}
