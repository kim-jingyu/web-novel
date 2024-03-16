package com.webnovel.genre.domain.repository;

import com.webnovel.genre.domain.Genre;
import com.webnovel.genre.domain.GenreType;
import com.webnovel.genre.domain.dto.GenresResponseDto;
import com.webnovel.novel.domain.Novel;

import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByNovel_NovelId(Long novelId);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    @Query("select new com.webnovel.genre.domain.dto.GenresResponseDto(n.novelId, m.nickName, group_concat(g.genreTypes)) " +
            "from Novel n left join Genre g on n.novelId = g.novel.novelId " +
            "left join Member m on n.member.memberId = m.memberId " +
            "group by n.novelId")
    Page<GenresResponseDto> findNovel_GenreTypes(Pageable pageable);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    @Query("select new com.webnovel.genre.domain.dto.GenresResponseDto(n.novelId, m.nickName, group_concat(g.genreTypes)) " + "from Novel n left join Genre g on n.novelId = g.novel.novelId " +
            "left join Member m on n.member.memberId = m.memberId where g.genreTypes = :genretype " + "group by n.novelId")
    Page<GenresResponseDto> findAllNovelByGenreTypes(@Param("genretype")int genretype,
                                                      Pageable pageable);

}
