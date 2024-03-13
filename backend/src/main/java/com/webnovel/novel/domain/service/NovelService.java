package com.webnovel.novel.domain.service;

import com.webnovel.comment.exception.LengthOverException;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.dto.CreateNovelDto;
import com.webnovel.novel.domain.dto.ModifyContentDto;
import com.webnovel.novel.domain.dto.SubscribeAndViewDto;
import com.webnovel.novel.domain.exception.ContentNotFoundException;
import com.webnovel.novel.domain.exception.CoverNotFoundException;
import com.webnovel.novel.domain.exception.NotFoundNovelException;
import com.webnovel.novel.domain.exception.UnderBoundaryException;
import com.webnovel.novel.domain.repository.NovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class NovelService {
    @Autowired
    private final NovelRepository novelRepository;


    public NovelService(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    public Novel findByNovelId(Long novelId){
        Novel byNovelId = novelRepository.findByNovelId(novelId);
        if (byNovelId == null) {
            throw new NotFoundNovelException();
        }
        return byNovelId;
    }

    public List<Novel> findByWriterId(Long writerId){
        List<Novel> allByWriterId = novelRepository.findAllByWriterId(writerId);
        return allByWriterId;
    }

    public void save(Novel novel){
        novelRepository.save(novel);
    }

    public Novel createNovel(CreateNovelDto createNovelDto){

        if (createNovelDto.getContent() == null) {
            throw new ContentNotFoundException();
        } else if (createNovelDto.getContent().length() > 255) {
            throw new LengthOverException();
        }else if(createNovelDto.getCover() == null) {
            throw new CoverNotFoundException();
        }

        Novel novel = new Novel(createNovelDto.getWriterId(),
                createNovelDto.getCover(), createNovelDto.getContent());
        save(novel);
        return novel;
    }

    public void modifyContent(ModifyContentDto modifyContentDto, String content){
        Novel byNovelId = novelRepository.findByNovelId(modifyContentDto.getNovelId());
        byNovelId.setContent(content);
        save(byNovelId);
    }

    public void plusSubscribe(SubscribeAndViewDto subscribeAndViewDto){
        Novel byNovelId = novelRepository.findByNovelId(subscribeAndViewDto.getNovelId());
        long plus = byNovelId.getSubscribe() + 1;
        byNovelId.setSubscribe(plus);
        save(byNovelId);
    }

    public void minusSubscribe(SubscribeAndViewDto subscribeAndViewDto){
        Novel byNovelId = novelRepository.findByNovelId(subscribeAndViewDto.getNovelId());
        long minus = byNovelId.getSubscribe() - 1;

        if(minus < 0 ){
            throw new UnderBoundaryException();
        }
        byNovelId.setSubscribe(minus);
        save(byNovelId);
    }

    public void plusView(SubscribeAndViewDto subscribeAndViewDto){
        Novel byNovelId = novelRepository.findByNovelId(subscribeAndViewDto.getNovelId());
        byNovelId.setView(byNovelId.getView() + 1);
        save(byNovelId);
    }

}
