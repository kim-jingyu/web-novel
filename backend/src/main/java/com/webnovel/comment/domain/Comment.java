package com.webnovel.comment.domain;

import com.webnovel.global.entity.BaseEntity;
import com.webnovel.member.domain.Member;
import com.webnovel.round.domain.Round;

import jakarta.persistence.Column;
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
public class Comment extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY) // 닉네임 즉시 가져옴
    @JoinColumn(name = "member_id")
    private Member member;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id")
    private Round round;

    @Column(columnDefinition = "TEXT", nullable = false) 
    private String content;

    public static Comment create(Member member, Round round, String contentBody) {
        Comment comment = Comment.builder()
            .member(member)
            .round(round)
            .content(contentBody)
            .build();

        return comment;
    }
}
