package com.webnovel.novel.domain.repository;

import com.webnovel.novel.domain.Novel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NovelRepository extends JpaRepository<Novel , Long> {

    List<Novel> findAllByMember_MemberId(Long writerId);
    Novel findByNovelId(Long novelId);

    Page<Novel> findAll(Pageable pageable);


}
