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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Novel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long novelId;

    @Column(nullable = false)
    private Long writerId;

    @Column
    private String cover;

    @Column
    private String content;

    @Column
    private Long view;

    @ColumnDefault("0")
    private Long subscribe;
}
