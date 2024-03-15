package com.webnovel.genre.domain.repository;

import com.webnovel.genre.domain.Genre;
import com.webnovel.novel.domain.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByNovel_NovelId(Long novelId);
}
