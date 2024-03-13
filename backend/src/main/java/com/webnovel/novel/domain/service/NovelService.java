package com.webnovel.novel.domain.service;

import com.webnovel.comment.exception.LengthOverException;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.dto.CreateNovelDto;
import com.webnovel.novel.domain.exception.ContentNotFoundException;
import com.webnovel.novel.domain.exception.CoverNotFoundException;
import com.webnovel.novel.domain.exception.NotFoundNovelException;
import com.webnovel.novel.domain.repository.NovelRepository;
import com.webnovel.subscribe.domain.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public void addContent(Novel novel, String content){
        novel.setContent(content);
        save(novel);
    }

    public void plusSubscribe(Novel novel){
        long plus = novel.getSubscribe() + 1;
        novel.setSubscribe(plus);
        save(novel);
    }

    public void minusSubscribe(Novel novel){
        long minus = novel.getSubscribe() -1 ;
        novel.setSubscribe(minus);
        save(novel);
    }

    public void plusView(Novel novel){
        novel.setView(novel.getView()+1);
        save(novel);
    }

}
