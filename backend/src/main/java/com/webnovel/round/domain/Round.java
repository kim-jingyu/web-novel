package com.webnovel.round.domain;

import com.webnovel.global.entity.BaseEntity;
import com.webnovel.recommend.domain.Recommend;
import com.webnovel.round.dto.RoundCreateDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.Builder.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Round extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roundId;

    @Column(nullable = false)
    private int roundNum;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    private String content;

    @Default
    private int likes = 0;

    @Default
    @ToString.Exclude
    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommend> recommends = new ArrayList<>();

    public static Round createRound(RoundCreateDto request, int roundNum) {
        return Round.builder()
                .roundNum(roundNum)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public void like() {
        likes++;
    }

    public void dislike() {
        likes--;
    }
}
