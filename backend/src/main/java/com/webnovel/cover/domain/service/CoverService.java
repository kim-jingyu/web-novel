package com.webnovel.cover.domain.service;

import com.webnovel.cover.domain.Cover;
import com.webnovel.cover.dto.CoverDto;
import com.webnovel.cover.domain.repository.CoverRepository;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.exception.ContentNotFoundException;
import com.webnovel.novel.domain.exception.CoverNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

@Service
public class CoverService {
    private final CoverRepository coverRepository;

    @Autowired
    public CoverService(CoverRepository coverRepository) {
        this.coverRepository = coverRepository;
    }

    @Transactional
    public void saveCover(CoverDto coverDto) {
        coverRepository.save(coverDto.toEntity());
    }

    public Cover findByNovelId(Long novelId) {
        return coverRepository.findByNovel_NovelId(novelId);
    }

    public void changeNameAndSaveCover(MultipartFile cover, Novel novel, Long writerId) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int millis = now.get(ChronoField.MILLI_OF_SECOND);

        String absolutePath = new File("C:\\Users\\hyun\\Desktop\\image").getAbsolutePath() + "/"; // 파일이 저장될 절대 경로
        String newFileName = ""+year+month+day+hour + minute + second + millis; // 새로 부여한 이미지명
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

                CoverDto coverDto = new CoverDto(writerId, novel,newFileName + fileExtension,absolutePath+path);

                saveCover(coverDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<?> coverPrint(Long novelId){
        Cover byNovelId = findByNovelId(novelId);
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
