package com.webnovel.subscribe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscribeActivateDto {
    @NotNull
    private final Long memberId;
    // @NotNull
    // private final Long novelId;
}
