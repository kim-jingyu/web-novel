package com.webnovel.genre.domain.service;

import com.webnovel.genre.domain.Genre;
import com.webnovel.genre.domain.dto.CreateGenreDto;
import com.webnovel.genre.domain.dto.GenreDto;
import com.webnovel.genre.domain.repository.GenreRepository;
import com.webnovel.novel.domain.Novel;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre findBynovel(GenreDto genreDto){
        Genre byNovelId = genreRepository.findByNovel(genreDto.getNovel());
        return byNovelId;
    }

    public void save(CreateGenreDto createGenreDto){
        Genre genre = new Genre(createGenreDto.getNovel(), createGenreDto.getGenreTypes());
        genreRepository.save(genre);
    }
}
