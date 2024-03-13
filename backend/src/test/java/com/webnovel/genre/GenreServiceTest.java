package com.webnovel.genre;

import com.webnovel.genre.domain.Genre;
import com.webnovel.genre.domain.GenreType;
import com.webnovel.genre.domain.dto.CreateGenreDto;
import com.webnovel.genre.domain.dto.GenreDto;
import com.webnovel.genre.domain.service.GenreService;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.service.NovelService;
import lombok.extern.slf4j.Slf4j;
import org.ietf.jgss.GSSName;
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
        Novel byNovelId1 = novelService.findByNovelId(1L);
        List<GenreType> genreType = new ArrayList<>();
        genreType.add(GenreType.ROMANCE);
        genreType.add(GenreType.ACTION);


        CreateGenreDto createGenreDto = CreateGenreDto.builder()
                .genreTypes(genreType)
                .novel(byNovelId1)
                .build();
        genreService.save(createGenreDto);
    }
    @Test
    void GenreTest(){

        Novel byNovelId1 = novelService.findByNovelId(1L);


        GenreType genreType = GenreType.ACTION;
        GenreDto genreDto = GenreDto.builder()
                .novel(byNovelId1)
                .genreTypes(genreType)
                .build();

        Genre bynovel = genreService.findBynovel(genreDto);
        log.info(bynovel.getGenreTypes().toString());
    }

}
