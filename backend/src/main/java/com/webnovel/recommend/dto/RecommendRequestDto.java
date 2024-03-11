package com.webnovel.recommend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendRequestDto {
    private Long memberId;
    private Long roundId;
}
