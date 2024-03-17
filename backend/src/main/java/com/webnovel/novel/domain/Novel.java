package com.webnovel.novel.domain;

import com.webnovel.global.entity.BaseEntity;
import com.webnovel.member.domain.Member;
import com.webnovel.member.domain.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Novel extends BaseEntity {

    public Novel(Member member, String cover, String content) {
        this.member = member;
        this.cover = cover;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long novelId;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String cover;

    @Column(nullable = false)
    private String content;

    @Column
    private Long view=0L;

    @Column
    private Long subscribe=0L;
}
