package com.webnovel.comment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentCreateDto {
    @NotNull
    private Long memberId;
    @NotNull
    private Long roundId;
    @NotEmpty
    private String content;
}
