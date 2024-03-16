package com.webnovel.genre;

import com.webnovel.genre.domain.Genre;
import com.webnovel.genre.domain.GenreType;
import com.webnovel.genre.domain.GenreTypeAttributeConverter;
import com.webnovel.genre.domain.dto.CreateGenreDto;
import com.webnovel.genre.domain.dto.FindGenreDto;
import com.webnovel.genre.domain.dto.GenresResponseDto;
import com.webnovel.genre.domain.service.GenreService;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.service.NovelService;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class GenreServiceTest {
    @Autowired
    GenreService genreService;
    @Autowired
    NovelService novelService;

    @Test
    void saveTest(){
        Novel byNovelId1 = novelService.findByNovelId(1L);
        Novel byNovelId2 = novelService.findByNovelId(2L);
        Novel byNovelId3 = novelService.findByNovelId(3L);


        CreateGenreDto createGenreDto = CreateGenreDto.builder()
                .genreTypes("ROMANCE")
                .novelId(byNovelId1.getNovelId())
                .build();

//        CreateGenreDto createGenreDto1 = CreateGenreDto.builder()
//                .genreTypes("ACTION")
//                .novelId(byNovelId2.getNovelId())
//                .build();
//
//        CreateGenreDto createGenreDto2 = CreateGenreDto.builder()
//                .genreTypes("ACTION")
//                .novelId(byNovelId3.getNovelId())
//                .build();

        genreService.save(createGenreDto);
//        genreService.save(createGenreDto1);
//        genreService.save(createGenreDto2);
    }

    @Test
    void GenreTest(){
        Sort sort = Sort.by(Sort.Order.desc("novelId"));
        Pageable pageable = PageRequest.of(0,10,sort);
        Page<GenresResponseDto> allByGenreTypes = genreService.findAllNovelByGenreTypes(1, pageable);
        System.out.println("allByGenreTypes.getContent() = " + allByGenreTypes.getContent());
    }

}
