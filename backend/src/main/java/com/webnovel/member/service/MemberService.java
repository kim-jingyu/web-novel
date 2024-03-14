package com.webnovel.member.service;

import com.webnovel.member.domain.Member;
import com.webnovel.member.domain.repository.MemberRepository;
import com.webnovel.member.exception.MemberNonFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(MemberNonFoundException::new);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNonFoundException::new);
    }
}
