package com.webnovel.round.dto;

import com.webnovel.recommend.domain.Recommend;
import com.webnovel.round.domain.Round;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoundResponseDto {
    private int roundNum;
    private String name;
    private String content;
    private int likes;
    private List<Recommend> recommends;

    public static RoundResponseDto of(Round round) {
        return RoundResponseDto.builder()
                .roundNum(round.getRoundNum())
                .name(round.getTitle())
                .content(round.getContent())
                .likes(round.getLikes())
                .recommends(round.getRecommends())
                .build();
    }
}
