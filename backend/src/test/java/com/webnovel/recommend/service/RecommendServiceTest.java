package com.webnovel.recommend.service;

import com.webnovel.member.domain.Member;
import com.webnovel.member.domain.Role;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class RecommendServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RoundRepository roundRepository;
    @Autowired
    RecommendRepository recommendRepository;
    @Autowired
    RecommendService recommendService;

    @Test
    @DisplayName("회차 추천 성공")
    void recommend() {
        // given
        Round round = Round.builder()
                .likes(0)
                .roundNum(1)
                .title("회차1")
                .content("내용")
                .build();
        Round savedRound = roundRepository.save(round);

        Member member = Member.builder()
                .email("user@email.com")
                .nickName("user")
                .password("1234")
                .role(Role.USER)
                .money(1000)
                .build();
        Member savedMember = memberRepository.save(member);

        RecommendRequestDto request = RecommendRequestDto.builder()
                .roundId(savedRound.getRoundId())
                .memberId(savedMember.getMemberId())
                .build();

        // when
        recommendService.recommend(request);

        // then
        Round findRound = roundRepository.findAll().get(0);
        assertThat(findRound.getLikes()).isEqualTo(1);
    }

    @Test
    @DisplayName("회차 추천 실패")
    void recommendEx() {
        // given
        Round round = Round.builder()
                .likes(0)
                .roundNum(1)
                .title("회차1")
                .content("내용")
                .build();
        Round savedRound = roundRepository.save(round);

        Member member = Member.builder()
                .email("user@email.com")
                .nickName("user")
                .password("1234")
                .role(Role.USER)
                .money(1000)
                .build();
        Member savedMember = memberRepository.save(member);

        RecommendRequestDto request = RecommendRequestDto.builder()
                .roundId(savedRound.getRoundId())
                .memberId(savedMember.getMemberId())
                .build();

        // when
        recommendService.recommend(request);
        assertThatThrownBy(() -> recommendService.recommend(request))
                .isInstanceOf(AlreadyRecommendException.class);
    }

    @Test
    @DisplayName("회차 추천 취소 성공")
    void cancelRecommend() {
        // given
        Round round = Round.builder()
                .likes(0)
                .roundNum(1)
                .title("회차1")
                .content("내용")
                .build();
        Round savedRound = roundRepository.save(round);

        Member member = Member.builder()
                .email("user@email.com")
                .nickName("user")
                .password("1234")
                .role(Role.USER)
                .money(1000)
                .build();
        Member savedMember = memberRepository.save(member);

        Recommend recommend = Recommend.createRecommend(savedMember, savedRound);
        Recommend savedRecommend = recommendRepository.save(recommend);

        RecommendCancelDto request = RecommendCancelDto.builder()
                .recommendId(savedRecommend.getRecommendId())
                .memberId(savedMember.getMemberId())
                .roundId(savedRound.getRoundId())
                .build();

        // when
        recommendService.cancelRecommend(request);

        // then
        Recommend findRecommend = recommendRepository.getById(savedRecommend.getRecommendId());
        assertThat(findRecommend.getRecommendStatus()).isEqualTo(RecommendStatus.NOT_YET);
    }

    @Test
    @DisplayName("회차 추천 실패")
    void cancelRecommendEx() {
        // given
        Round round = Round.builder()
                .likes(0)
                .roundNum(1)
                .title("회차1")
                .content("내용")
                .build();
        roundRepository.save(round);

        Member member = Member.builder()
                .email("user@email.com")
                .nickName("user")
                .password("1234")
                .role(Role.USER)
                .money(1000)
                .build();
        memberRepository.save(member);

        Recommend savedRecommend = recommendRepository.save(Recommend.createRecommend(member, round));

        RecommendCancelDto request = RecommendCancelDto.builder()
                .memberId(member.getMemberId())
                .roundId(round.getRoundId())
                .recommendId(savedRecommend.getRecommendId())
                .build();
        recommendService.cancelRecommend(request);

        // when
        assertThatThrownBy(() -> recommendService.cancelRecommend(request))
                .isInstanceOf(AlreadyCancelException.class);
    }


}