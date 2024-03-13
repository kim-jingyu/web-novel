package com.webnovel.genre.domain.dto;

import com.webnovel.genre.domain.GenreType;
import com.webnovel.novel.domain.Novel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateGenreDto {
    private final Long genreId;
    @NotNull
    private final Novel novel;
    @NotNull
    private final List<GenreType> genreTypes;
}
