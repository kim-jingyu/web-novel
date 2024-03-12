package com.webnovel.cover.domain.repository;

import com.webnovel.cover.domain.Cover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverRepository extends JpaRepository<Cover ,Long> {
    Cover findByNovelId(Long novelID);
}
