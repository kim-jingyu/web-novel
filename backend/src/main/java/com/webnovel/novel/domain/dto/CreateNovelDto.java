package com.webnovel.novel.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNovelDto {
    @NotNull
    private final Long writerId;
    @NotNull
    private final String content;
    @NotNull
    private final String cover;

}
