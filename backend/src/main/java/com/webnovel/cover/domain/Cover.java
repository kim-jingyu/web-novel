package com.webnovel.cover.domain;

import com.webnovel.novel.domain.Novel;
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
    private Long memberId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="novel_Id")
    private Novel novel;

    @Column
    private String imgName;  //저장되는 파일명
    @Column
    private String imgPath;  //저장경로

    @Builder
    public Cover(Long memberId, Novel novel,String imgName, String imgPath) {
        this.memberId = memberId;
        this.novel = novel;
        this.imgName = imgName;
        this.imgPath = imgPath;
    }
}
