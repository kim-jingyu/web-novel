package com.webnovel.round.domain;

import com.webnovel.global.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Round extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int round;
    private String name;
    @Lob
    private String content;
    private int like;
}
