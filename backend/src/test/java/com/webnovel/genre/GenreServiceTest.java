package com.webnovel.genre;

import com.webnovel.genre.domain.Genre;
import com.webnovel.genre.domain.GenreType;
import com.webnovel.genre.domain.dto.CreateGenreDto;
import com.webnovel.genre.domain.dto.FindGenreDto;
import com.webnovel.genre.domain.service.GenreService;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.service.NovelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Novel byNovelId1 = novelService.findByNovelId(2L);
        List<GenreType> genreType = new ArrayList<>();
        genreType.add(GenreType.ROMANCE);
        genreType.add(GenreType.ACTION);


        CreateGenreDto createGenreDto = CreateGenreDto.builder()
                .genreTypes(genreType)
                .novelId(byNovelId1.getNovelId())
                .build();
        genreService.save(createGenreDto);
    }
    @Test
    void GenreTest(){

        Novel byNovelId1 = novelService.findByNovelId(2L);


        GenreType genreType = GenreType.ACTION;
        FindGenreDto findGenreDto = FindGenreDto.builder()
                .novelId(byNovelId1.getNovelId())
                .genreTypes(genreType)
                .build();

        Genre bynovel = genreService.findBynovel(findGenreDto);
        log.info(bynovel.getGenreTypes().toString());
    }

}
