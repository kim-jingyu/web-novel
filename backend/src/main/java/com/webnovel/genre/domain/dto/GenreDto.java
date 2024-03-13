package com.webnovel.genre.domain.dto;

import com.webnovel.genre.domain.GenreType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GenreDto {
    @NotNull
    private final Long genreId;
    @NotNull
    private final Long novelId;
    @NotNull
    GenreType genreTypes;

}
