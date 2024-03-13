package com.webnovel.cover.domain.controller;

import com.webnovel.cover.domain.Cover;
import com.webnovel.cover.domain.CoverDto;
import com.webnovel.cover.domain.service.CoverService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.aspectj.util.FileUtil;
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

    private final CoverService coverService;

    @Autowired
    public CoverController(CoverService coverService) {
        this.coverService = coverService;
    }

    //이미지 업로드
    @PostMapping("/{writerId}/{novelId}/img")
    public void coverUpload(@PathVariable long writerId
            ,@PathVariable long novelId,
            @RequestParam("cover") MultipartFile cover) throws IOException{


        // 폴더 생성과 파일명 새로 부여를 위한 현재 시간 알아내기
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int millis = now.get(ChronoField.MILLI_OF_SECOND);

        String absolutePath = new File("저장경로").getAbsolutePath() + "/"; // 파일이 저장될 절대 경로
        String newFileName = ""+hour + minute + second + millis; // 새로 부여한 이미지명
        String fileExtension = '.' + cover.getOriginalFilename().replaceAll("^.*\\.(.*)$", "$1"); // 확장자만 추출
        String path = "test/" + year + "/" + month + "/" + day; // 저장될 폴더 경로

        try {
            if(!cover.isEmpty()) {
                File file = new File(absolutePath + path);
                if(!file.exists()){
                    file.mkdirs(); // mkdir()과 다르게 상위 폴더가 없을 때 상위폴더까지 생성
                }

                file = new File(absolutePath + path + "/" + newFileName + fileExtension);
                cover.transferTo(file);

                CoverDto coverDto = new CoverDto(writerId, novelId,newFileName + fileExtension,absolutePath+path);

                coverService.saveCover(coverDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //이미지 출력
    @PostMapping("{novelId}")
    public ResponseEntity<?> coverPrint(@PathVariable long novelId) throws IOException{
        Cover byNovelId = coverService.findByNovelId(novelId);
        File coverImage = new File(byNovelId.getImgPath()+"/"+byNovelId.getImgName());

        ResponseEntity<byte[]> result=null;
        try {
            HttpHeaders headers=new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(coverImage.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(coverImage),headers, HttpStatus.OK);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
