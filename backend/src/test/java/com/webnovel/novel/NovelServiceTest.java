package com.webnovel.novel;

import com.webnovel.genre.domain.service.GenreService;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.dto.CreateNovelDto;
import com.webnovel.novel.domain.dto.ModifyContentDto;
import com.webnovel.novel.domain.dto.SubscribeAndViewDto;
import com.webnovel.novel.domain.service.NovelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NovelServiceTest {
    @Autowired
    NovelService novelService;



    @Test
    void CreateTest(){
        Novel novel = new Novel(3L,"qqqqq","wwwww");
        CreateNovelDto createNovelDto = CreateNovelDto.builder()
                .writerId(novel.getWriterId())
                .cover(novel.getCover())
                .content(novel.getContent())
                .build();

        Novel novel1= novelService.createNovel(createNovelDto);

    }

    @Test
    void ModifyNovelTest(){
        Novel byNovelId = novelService.findByNovelId(1L);
        ModifyContentDto modifyContentDto = ModifyContentDto.builder()
                .novelId(byNovelId.getNovelId())
                .writerId(byNovelId.getWriterId())
                .content(byNovelId.getContent())
                .cover(byNovelId.getCover())
                .build();


        novelService.modifyContent(modifyContentDto,"hello");
    }

    @Test
    void SubscribeTest(){
        SubscribeAndViewDto subscribeAndViewDto = SubscribeAndViewDto.builder()
                .novelId(1L)
                .build();

        novelService.plusSubscribe(subscribeAndViewDto);
        //novelService.minusSubscribe(subscribeAndViewDto);
    }

    @Test
    void PlusViewTest(){
        SubscribeAndViewDto subscribeAndViewDto = SubscribeAndViewDto.builder()
                .novelId(1L)
                .build();
        novelService.plusView(subscribeAndViewDto);
    }
}
