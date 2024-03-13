package com.webnovel.subscribe.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webnovel.member.domain.repository.MemberRepository;
import com.webnovel.subscribe.dto.SubscribeActivateDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscribeService {
    private final MemberRepository memberRepository;
    // private final NovelRepository novelRepository;

    @Transactional
    public void doSubscribe(SubscribeActivateDto request) {
        
    }

    @Transactional
    public void unSubscribe(SubscribeActivateDto request) {
        
    }
}
