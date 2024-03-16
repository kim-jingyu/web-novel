package com.webnovel.round.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoundCreateDto {
    @NotBlank(message = "타이틀을 입력해주세요.")
    @Size(max = 50, message = "타이틀은 50자 이상 입력할 수 없습니다.")
    private String title;
    private String content;
    @NotBlank(message = "설정할 작품 정보는 필수입니다.")
    private Long novelId;
}
