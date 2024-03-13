package com.webnovel.novel.domain;

import com.webnovel.global.entity.BaseEntity;
import com.webnovel.member.domain.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Novel extends BaseEntity {

    public Novel(Long writerId, String cover, String content) {
        this.writerId = writerId;
        this.cover = cover;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long novelId;

    @Column(nullable = false)
    private Long writerId;

    @Column(nullable = false)
    private String cover;

    @Column(nullable = false)
    private String content;

    @Column
    private Long view=0L;

    @Column
    private Long subscribe=0L;
}
