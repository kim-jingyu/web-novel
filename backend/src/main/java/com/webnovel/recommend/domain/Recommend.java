package com.webnovel.recommend.domain;

import com.webnovel.global.entity.BaseEntity;
import com.webnovel.member.domain.Member;
import com.webnovel.round.domain.Round;
import jakarta.persistence.*;
import lombok.*;

import static com.webnovel.recommend.domain.RecommendStatus.*;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Recommend extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id")
    private Round round;

    @Enumerated(value = EnumType.STRING)
    private RecommendStatus recommendStatus;

    public static Recommend createRecommend(Member member, Round round) {
        Recommend recommend = Recommend.builder()
                .member(member)
                .round(round)
                .recommendStatus(ALREADY)
                .build();

        round.like();
        return recommend;
    }

    public void like() {
        recommendStatus = ALREADY;
        round.like();
    }

    public void cancel() {
        recommendStatus = NOT_YET;
        round.dislike();
    }
}
