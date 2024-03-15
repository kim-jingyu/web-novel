package com.webnovel.subscribe.domain.repository;

import com.webnovel.subscribe.domain.Subscribe;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    Optional<Subscribe> findByMember_MemberIdAndNovel_NovelId(Long memberId, Long novelId);
}
