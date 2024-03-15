package com.webnovel.subscribe.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webnovel.member.domain.Member;
import com.webnovel.member.domain.repository.MemberRepository;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.repository.NovelRepository;
import com.webnovel.novel.domain.service.NovelService;
import com.webnovel.subscribe.domain.Subscribe;
import com.webnovel.subscribe.domain.repository.SubscribeRepository;
import com.webnovel.subscribe.dto.SubscribeActivateDto;
import com.webnovel.subscribe.exception.AlreadySubscribeException;
import com.webnovel.subscribe.exception.SubscribeFailException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscribeService {
    private final MemberRepository memberRepository;
    private final NovelService novelService;
    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void doSubscribe(SubscribeActivateDto request) {
        Member member = memberRepository.getById(request.getMemberId());
        Novel novel = novelService.findByNovelId(request.getNovelId());
        subscribeRepository.findByMember_MemberIdAndNovel_NovelId(member.getMemberId(), novel.getNovelId())
            .ifPresentOrElse((result) -> {
                throw new AlreadySubscribeException();
            }, () -> {
                novelService.plusSubscribe(novel);
                subscribeRepository.save(
                    Subscribe.creatSubscribe(member, novel)
                );
            });
    }

    @Transactional
    public void unSubscribe(SubscribeActivateDto request) {
        Member member = memberRepository.getById(request.getMemberId());
        Novel novel = novelService.findByNovelId(request.getNovelId());
        subscribeRepository.findByMember_MemberIdAndNovel_NovelId(member.getMemberId(), novel.getNovelId())
            .ifPresentOrElse((subscribe) -> {
                novelService.plusSubscribe(novel);
                subscribeRepository.delete(subscribe);
            }, () -> {
                throw new SubscribeFailException();
            });
    }
}
