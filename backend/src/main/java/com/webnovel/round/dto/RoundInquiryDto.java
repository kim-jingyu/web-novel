package com.webnovel.round.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoundInquiryDto {
    @NotBlank(message = "회차 정보는 필수값입니다.")
    private Long roundId;
}
