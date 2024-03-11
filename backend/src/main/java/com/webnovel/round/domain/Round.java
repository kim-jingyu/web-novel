package com.webnovel.round.domain;

import com.webnovel.global.entity.BaseEntity;
import com.webnovel.recommend.domain.Recommend;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Round extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roundId;
    private Integer roundNum;
    private String name;
    @Lob
    private String content;
    private Integer likes;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommend> recommends = new ArrayList<>();

    public void like() {
        likes++;
    }

    public void dislike() {
        likes--;
    }
}
