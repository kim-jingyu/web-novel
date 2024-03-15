package com.webnovel.novel.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModifyContentDto {
    @NotNull
    private final Long novelId;
    private final Long memberId;
    private final String content;
    private final String cover;
}
