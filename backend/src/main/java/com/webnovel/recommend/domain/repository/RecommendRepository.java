package com.webnovel.recommend.domain.repository;

import com.webnovel.recommend.domain.Recommend;
import com.webnovel.recommend.domain.RecommendStatus;
import com.webnovel.recommend.exception.RecommendNonFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    default Recommend getById(Long recommendId) {
        return findById(recommendId)
                .orElseThrow(RecommendNonFoundException::new);
    }

    Optional<Recommend> findByMember_MemberIdAndRound_RoundIdAndRecommendStatus(Long memberId, Long roundId, RecommendStatus recommendStatus);

    Optional<Recommend> findByMember_MemberIdAndRound_RoundId(Long memberId, Long roundId);
}
