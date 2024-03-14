package com.webnovel.login.infra.providers;

import com.webnovel.login.domain.OAuthAccessToken;
import com.webnovel.login.domain.OAuthProvider;
import com.webnovel.login.domain.userinfo.OAuthUserInfo;
import com.webnovel.login.domain.userinfo.kakao.KakaoUserInfo;
import com.webnovel.login.exception.InvalidAuthCodeException;
import com.webnovel.login.exception.OAuthServiceNonFoundException;
import com.webnovel.login.infra.config.credentials.KakaoCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Component
@RequiredArgsConstructor
public class KakaoOAuthProvider implements OAuthProvider {
    private static final String PROVIDER_NAME = "kakao";
    private static final String SECURE_RESOURCE = "secure_resource";

    private final KakaoCredentials kakaoCredentials;

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

        Map<String, Boolean> queryParam = new HashMap<>();
        queryParam.put(SECURE_RESOURCE, TRUE);

        ResponseEntity<KakaoUserInfo> userInfoResponseEntity = restTemplate.exchange(
                kakaoCredentials.getResourceUri(),
                GET,
                userInfoRequestEntity,
                KakaoUserInfo.class,
                queryParam
        );

        if (userInfoResponseEntity.getStatusCode().is2xxSuccessful()) {
            return userInfoResponseEntity.getBody();
        }
        throw new OAuthServiceNonFoundException();
    }

    private String requestAccessToken(String code) {
        HttpEntity<MultiValueMap<String, String>> accessTokenRequestEntity = getAccessTokenRequestEntity(code);

        ResponseEntity<OAuthAccessToken> accessTokenResponseEntity = restTemplate.exchange(
                kakaoCredentials.getTokenUri(),
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
        headers.setBasicAuth(kakaoCredentials.getClientId(), kakaoCredentials.getClientSecret());

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", kakaoCredentials.getClientId());
        params.add("client_secret", kakaoCredentials.getClientSecret());
        params.add("redirect_uri", kakaoCredentials.getRedirectUri());
        params.add("grant_type", "authorization_code");

        return new HttpEntity<>(params, headers);
    }
}
