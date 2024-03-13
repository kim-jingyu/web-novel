package com.webnovel.novel.domain.repository;

import com.webnovel.member.domain.Member;
import com.webnovel.member.exception.MemberNonFoundException;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.exception.NovelNonFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NovelRepository extends JpaRepository<Novel , Long> {

    List<Novel> findAllByWriterId(Long writerId);
    Novel findByNovelId(Long novelId);
    default Novel getById(Long novelId) {
        return findById(novelId)
                .orElseThrow(NovelNonFoundException::new);
    }


}