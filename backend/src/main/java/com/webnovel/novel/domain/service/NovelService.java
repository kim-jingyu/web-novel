package com.webnovel.novel.domain.service;

import com.webnovel.comment.exception.LengthOverException;
import com.webnovel.member.domain.Member;
import com.webnovel.member.domain.repository.MemberRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class NovelService {
    @Autowired
    private final NovelRepository novelRepository;
    private final MemberRepository memberRepository;


    public NovelService(NovelRepository novelRepository, MemberRepository memberRepository) {
        this.novelRepository = novelRepository;
        this.memberRepository = memberRepository;
    }

    public Page<Novel> findAllSorted(Pageable pageable){
        return novelRepository.findAll(pageable);
    }

    public Page<Novel> findAllNoSorted(Pageable pageable){
        return novelRepository.findAll(pageable);
    }

    public Novel findByNovelId(Long novelId){
        Novel byNovelId = novelRepository.findByNovelId(novelId);
        if (byNovelId == null) {
            throw new NotFoundNovelException();
        }
        return byNovelId;
    }

    public List<Novel> findByMemberId(Long writerId){
        List<Novel> allByMemberId = novelRepository.findAllByMember_MemberId(writerId);
        return allByMemberId;
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

        Member byId = memberRepository.getById(createNovelDto.getMemberId());
        Novel novel = new Novel(byId,
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
