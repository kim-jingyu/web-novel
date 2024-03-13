package com.webnovel.auth.service;

import com.webnovel.auth.domain.OAuthUserInfo;
import com.webnovel.auth.domain.OAuthUserInfoFactory;
import com.webnovel.auth.exception.InvalidOAuthRegistrationException;
import com.webnovel.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuthUserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return updateUserInfo(userRequest, oAuth2User);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e);
        }
    }

    private OAuth2User updateUserInfo(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String accessToken = userRequest.getAccessToken().getTokenValue();

        OAuthUserInfo oAuthUserInfo = OAuthUserInfoFactory.getOAuthUserInfo(registrationId, accessToken, oAuth2User.getAttributes());
        if (!StringUtils.hasText(oAuthUserInfo.getEmail())) {
            throw new InvalidOAuthRegistrationException("사용자 정보를 찾을 수 없습니다.");
        }

        return new OAuthUserPrincipal(oAuthUserInfo);
    }
}
