package com.webnovel.subscribe.domain;

import com.webnovel.member.domain.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscribeId;
 
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // @ToString.Exclude
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "novel_id", nullable = false)
    // private Novel novel;

    public static Subscribe creatSubscribe(Member member /*, Novel novel*/) {
        Subscribe subscribe = Subscribe.builder()
            .member(member)
            /*.novel(novel)
            */.build();

        return subscribe;
    }

    public void activate() {
        //novel.doSubscribe();
    }

    public void deactivate() {
        //novel.unSubscribe();
    }
}
