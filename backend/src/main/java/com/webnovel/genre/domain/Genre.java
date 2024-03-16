package com.webnovel.genre.domain;

import com.webnovel.novel.domain.Novel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id", nullable = false)
    private Novel novel;

    @Column
    @Convert(converter = GenreTypeAttributeConverter.class)
    private String genreTypes;

    public Genre(Novel novel, String genreTypes) {
        this.novel = novel;
        this.genreTypes = genreTypes;
    }
}
