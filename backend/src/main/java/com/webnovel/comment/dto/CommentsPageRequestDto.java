package com.webnovel.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentsPageRequestDto {
    @NotNull
    private final Long roundId;
    @NotNull
    private final int offset;
}
