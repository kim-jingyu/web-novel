package com.webnovel.cover.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Cover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    @Column
    private Long writerId;
    @Column
    private Long novelId;
    @Column
    private String imgName;  //저장되는 파일명
    @Column
    private String imgPath;  //저장경로

    @Builder
    public Cover(Long writerId, Long novelId,String imgName, String imgPath) {
        this.writerId = writerId;
        this.novelId = novelId;
        this.imgName = imgName;
        this.imgPath = imgPath;
    }
}
