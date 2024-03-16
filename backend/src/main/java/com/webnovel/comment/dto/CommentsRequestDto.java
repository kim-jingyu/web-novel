package com.webnovel.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentsRequestDto {
    @NotNull
    private Long roundId;
}
