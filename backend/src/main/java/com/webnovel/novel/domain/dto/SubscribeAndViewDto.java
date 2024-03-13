package com.webnovel.novel.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscribeAndViewDto {
    @NotNull
    private final Long novelId;
}
