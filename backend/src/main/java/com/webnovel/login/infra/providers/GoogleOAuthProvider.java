package com.webnovel.login.infra.providers;

import com.webnovel.login.domain.OAuthAccessToken;
import com.webnovel.login.domain.OAuthProvider;
import com.webnovel.login.domain.userinfo.OAuthUserInfo;
import com.webnovel.login.domain.userinfo.google.GoogleUserInfo;
import com.webnovel.login.exception.InvalidAuthCodeException;
import com.webnovel.login.exception.OAuthServiceNonFoundException;
import com.webnovel.login.infra.config.credentials.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.POST;

@Component
@RequiredArgsConstructor
public class GoogleOAuthProvider implements OAuthProvider {
    private static final String PROVIDER_NAME = "google";
    private final GoogleCredentials googleCredentials;

    @Override
    public boolean is(String name) {
        return PROVIDER_NAME.equals(name);
    }

    @Override
    public OAuthUserInfo getUserInfo(String code) {
        String accessToken = requestAccessToken(code);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<MultiValueMap<String, String>> userInfoRequestEntity = new HttpEntity<>(headers);

        ResponseEntity<GoogleUserInfo> userInfoResponseEntity = restTemplate.exchange(
                googleCredentials.getResourceUri(),
                GET,
                userInfoRequestEntity,
                GoogleUserInfo.class
        );

        if (userInfoResponseEntity.getStatusCode().is2xxSuccessful()) {
            return userInfoResponseEntity.getBody();
        }
        throw new OAuthServiceNonFoundException();
    }

    private String requestAccessToken(String code) {
        HttpEntity<MultiValueMap<String, String>> accessTokenRequestEntity = getAccessTokenRequestEntity(code);

        ResponseEntity<OAuthAccessToken> accessTokenResponseEntity = restTemplate.exchange(
                googleCredentials.getTokenUri(),
                POST,
                accessTokenRequestEntity,
                OAuthAccessToken.class
        );

        return Optional.ofNullable(accessTokenResponseEntity.getBody())
                .orElseThrow(InvalidAuthCodeException::new)
                .getAccessToken();
    }

    private HttpEntity<MultiValueMap<String, String>> getAccessTokenRequestEntity(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(googleCredentials.getClientId(), googleCredentials.getClientSecret());

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", googleCredentials.getClientId());
        params.add("client_secret", googleCredentials.getClientSecret());
        params.add("redirect_uri", googleCredentials.getRedirectUri());
        params.add("grant_type", "authorization_code");

        return new HttpEntity<>(params, headers);
    }
}
