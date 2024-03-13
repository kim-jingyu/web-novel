package com.webnovel.genre.domain.dto;

import com.webnovel.genre.domain.GenreType;
import com.webnovel.novel.domain.Novel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GenreDto {
    @NotNull
    private final Novel novel;
    @NotNull
    GenreType genreTypes;

}
