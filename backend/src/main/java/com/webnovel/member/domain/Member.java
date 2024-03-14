package com.webnovel.member.domain;

import com.webnovel.global.entity.BaseEntity;
import com.webnovel.login.domain.userinfo.OAuthUserInfo;
import com.webnovel.recommend.domain.Recommend;
import com.webnovel.recommend.domain.RecommendStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String imageUrl;

    @Enumerated
    private Role role;

    @Enumerated
    private RecommendStatus recommendStatus;

    private int money;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommend> recommends = new ArrayList<>();

    public static Member createMember(OAuthUserInfo userInfo) {
        return Member.builder()
                .email(userInfo.getEmail())
                .nickName(userInfo.getNickName())
                .imageUrl(userInfo.getImageUrl())
                .build();
    }

    public void updateNickName(String nickName) {
        this.nickName = nickName;
    }
}
