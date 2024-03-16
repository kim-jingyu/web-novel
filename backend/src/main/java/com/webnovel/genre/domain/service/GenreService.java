package com.webnovel.genre.domain.service;

import com.webnovel.genre.domain.Genre;
import com.webnovel.genre.domain.dto.CreateGenreDto;
import com.webnovel.genre.domain.dto.FindGenreDto;
import com.webnovel.genre.domain.dto.GenresResponseDto;
import com.webnovel.genre.domain.repository.GenreRepository;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.service.NovelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final NovelService novelService;

    public GenreService(GenreRepository genreRepository, NovelService novelService) {
        this.genreRepository = genreRepository;
        this.novelService = novelService;
    }

    public Genre findBynovel(FindGenreDto findGenreDto){
        Genre byNovelId = genreRepository.findByNovel_NovelId(findGenreDto.getNovelId());
        return byNovelId;
    }

    public Page<GenresResponseDto> findNovel_GenreTypes(Pageable pageable){
        return genreRepository.findNovel_GenreTypes(pageable);
    }

    public Page<GenresResponseDto> findAllNovelByGenreTypes(int genreType , Pageable pageable){
        return genreRepository.findAllNovelByGenreTypes(genreType, pageable);
    }

    public void save(CreateGenreDto createGenreDto){
        Novel byNovelId = novelService.findByNovelId(createGenreDto.getNovelId());
        Genre genre = new Genre(byNovelId, createGenreDto.getGenreTypes());
        genreRepository.save(genre);
    }

}
