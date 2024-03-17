package com.webnovel.cover.domain.controller;

import com.webnovel.cover.domain.Cover;
import com.webnovel.cover.dto.CoverDto;
import com.webnovel.cover.domain.service.CoverService;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.service.NovelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

@Controller
@Slf4j
@RequestMapping("/cover")
public class CoverController {

    @Autowired
    private CoverService coverService;
    @Autowired
    private NovelService novelService;



    //이미지 업로드
    @PostMapping("/{writerId}/{novelId}/img")
    public void coverUpload(@PathVariable long writerId
            ,@PathVariable long novelId,
            @RequestParam("cover") MultipartFile cover) throws IOException{
        Novel byNovelId = novelService.findByNovelId(novelId);
        coverService.changeNameAndSaveCover(cover, byNovelId, writerId);
    }

    //이미지 출력
    @PostMapping("{novelId}")
    public ResponseEntity<?> coverPrint(@PathVariable long novelId) throws IOException{
        return coverService.coverPrint(novelId);
    }

}
