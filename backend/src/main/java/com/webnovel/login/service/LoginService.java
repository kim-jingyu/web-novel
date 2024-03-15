package com.webnovel.login.service;

import com.webnovel.login.domain.MemberTokens;
import com.webnovel.login.domain.OAuthProvider;
import com.webnovel.login.domain.OAuthProviders;
import com.webnovel.login.domain.RefreshToken;
import com.webnovel.login.domain.repository.RefreshTokenRepository;
import com.webnovel.login.domain.userinfo.OAuthUserInfo;
import com.webnovel.login.exception.InvalidAuthCodeException;
import com.webnovel.login.exception.LoginAttemptsExceededException;
import com.webnovel.login.infra.providers.JwtProvider;
import com.webnovel.login.infra.supporter.BearTokenExtractor;
import com.webnovel.member.domain.Member;
import com.webnovel.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static java.lang.String.format;
import static java.lang.String.valueOf;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
    private final OAuthProviders oAuthProviders;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BearTokenExtractor extractor;

    public MemberTokens login(String providerName, String code) {
        OAuthProvider provider = oAuthProviders.getProvider(providerName);
        OAuthUserInfo userInfo = provider.getUserInfo(code);
        Member member = memberRepository.findByEmail(userInfo.getEmail())
                .orElseGet(() -> createMember(userInfo));
        MemberTokens memberTokens = jwtProvider.issueLoginTokens(valueOf(member.getMemberId()));

        refreshTokenRepository.save(RefreshToken.builder()
                .token(memberTokens.getRefreshToken())
                .memberId(member.getMemberId())
                .build());

        return memberTokens;
    }

    public String reissueAccessToken(String refreshToken, String authorizationHeader) {
        String accessToken = extractor.getAccessToken(authorizationHeader);
        jwtProvider.validateToken(MemberTokens.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build());

        RefreshToken findRefreshToken = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(InvalidAuthCodeException::new);

        return jwtProvider.reissueAccessToken(valueOf(findRefreshToken.getMemberId()));
    }

    public void removeRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }

    private Member createMember(OAuthUserInfo userInfo) {
        int tryCount = 0;
        while (tryCount < 5) {
            String randomNickName = getRandomNickName(userInfo.getNickName());
            if (!memberRepository.existsByNickName(randomNickName)) {
                return memberRepository.save(Member.createMember(userInfo));
            }
            tryCount++;
        }
        throw new LoginAttemptsExceededException();
    }

    private String getRandomNickName(String nickName) {
        String formattedNumber = format("%04d", new Random().nextInt(10000));
        return format("%s_%s", nickName, formattedNumber);
    }
}
