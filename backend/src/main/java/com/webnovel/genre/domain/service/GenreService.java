package com.webnovel.genre.domain.service;

import com.webnovel.genre.domain.Genre;
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

    public Genre findBynovel(Novel novel){
        Genre byNovelId = genreRepository.findByNovel(novel);
        return byNovelId;
    }

    public void save(Genre genre){
        genreRepository.save(genre);
    }
}
