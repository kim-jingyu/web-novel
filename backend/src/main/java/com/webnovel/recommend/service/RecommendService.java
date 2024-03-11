package com.webnovel.recommend.service;

import com.webnovel.member.domain.Member;
import com.webnovel.member.domain.repository.MemberRepository;
import com.webnovel.recommend.domain.Recommend;
import com.webnovel.recommend.domain.RecommendStatus;
import com.webnovel.recommend.domain.repository.RecommendRepository;
import com.webnovel.recommend.dto.RecommendCancelDto;
import com.webnovel.recommend.dto.RecommendRequestDto;
import com.webnovel.recommend.exception.AlreadyCancelException;
import com.webnovel.recommend.exception.AlreadyRecommendException;
import com.webnovel.round.domain.Round;
import com.webnovel.round.domain.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendService {
    private final MemberRepository memberRepository;
    private final RoundRepository roundRepository;
    private final RecommendRepository recommendRepository;

    @Transactional
    public void recommend(RecommendRequestDto request) {
        recommendRepository.findByMember_MemberIdAndRound_RoundId(request.getMemberId(), request.getRoundId())
                .ifPresentOrElse(recommend -> {
                    if (recommend.getRecommendStatus().equals(RecommendStatus.ALREADY)) {
                        throw new AlreadyRecommendException();
                    }
                    recommend.like();
                }, () -> {
                    Member member = memberRepository.getById(request.getMemberId());
                    Round round = roundRepository.getById(request.getRoundId());
                    Recommend recommend = Recommend.createRecommend(member, round);
                    recommendRepository.save(recommend);
                });
    }

    @Transactional
    public void cancelRecommend(RecommendCancelDto request) {
        recommendRepository.findByMember_MemberIdAndRound_RoundIdAndRecommendStatus(request.getMemberId(), request.getRoundId(), RecommendStatus.NOT_YET)
                .ifPresent(recommend -> {
                    throw new AlreadyCancelException();
                });

        Recommend recommend = recommendRepository.getById(request.getRecommendId());

        recommend.cancel();
    }
}
