package com.webnovel.genre.domain.dto;

import com.webnovel.genre.domain.GenreType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FindGenreDto {
    @NotNull
    private final Long novelId;
    @NotNull
    String genreTypes;

}
