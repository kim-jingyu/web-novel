package com.webnovel.recommend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendCancelDto {
    private Long memberId;
    private Long roundId;
    private Long recommendId;
}
