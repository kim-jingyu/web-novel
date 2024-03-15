package com.webnovel.login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.webnovel.login.domain.Authority.*;

@Getter
@RequiredArgsConstructor
@Builder
public class LoginMember {
    private final Long memberId;
    private final Authority authority;

    public static LoginMember guest() {
        return LoginMember.builder()
                .memberId(0L)
                .authority(GUEST)
                .build();
    }

    public static LoginMember member(Long memberId) {
        return LoginMember.builder()
                .memberId(memberId)
                .authority(MEMBER)
                .build();
    }

    public static LoginMember admin(Long memberId) {
        return LoginMember.builder()
                .memberId(memberId)
                .authority(ADMIN)
                .build();
    }

    public boolean isMember() {
        return this.authority.equals(MEMBER);
    }

    public boolean isAdmin() {
        return this.authority.equals(ADMIN);
    }
}
