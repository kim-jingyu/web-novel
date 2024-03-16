package com.webnovel.comment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Builder
public class CommentsResponseDto {
    @NotNull
    private Long memberId;
    @NotNull
    private Long commentId;
    @NotNull
    private Long roundId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String content;

}
