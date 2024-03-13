package com.webnovel.genre.domain;

public enum GenreType {
    ROMANCE("로맨스"),
    ACTION("액션"),
    FANTASY("판타지");

    private final String value;

    GenreType(String value) {
        this.value = value;
    }
}
