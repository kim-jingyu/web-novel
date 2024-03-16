package com.webnovel.genre.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenresResponseDto {
    private Long novelId;
    private String nickName;
    private Object genreTypes;
}
