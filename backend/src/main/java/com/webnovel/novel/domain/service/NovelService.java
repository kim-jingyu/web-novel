package com.webnovel.novel.domain.service;

import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.repository.NovelRepository;
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

    public Optional<Novel> findByNovelId(Long novelId){
        Optional<Novel> byNovelId = novelRepository.findById(novelId);
        return byNovelId;
    }

    public List<Novel> findByWriterId(Long writerId){
        List<Novel> allByWriterId = novelRepository.findAllByWriterId(writerId);
        return allByWriterId;
    }

    public void save(Novel novel){
        novelRepository.save(novel);
    }

    public void addContent(Novel novel, String content){
        novel.setContent(content);
        save(novel);
    }

    public void plusSubscribe(Novel novel){

    }

    public void plusView(Novel novel){
        novel.setView(novel.getView()+1);
        save(novel);
    }

}
