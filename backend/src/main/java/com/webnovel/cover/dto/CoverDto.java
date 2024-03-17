package com.webnovel.cover.dto;

import com.webnovel.cover.domain.Cover;
import com.webnovel.novel.domain.Novel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class CoverDto {

    private Long memberId;
    private Novel novel;
    private String imgName;
    private String imgPath;

    public Cover toEntity() {
        Cover build = Cover.builder()
                .memberId(memberId)
                .novel(novel)
                .imgName(imgName)
                .imgPath(imgPath)
                .build();
        return build;
    }

    @Builder
    public CoverDto (Long memberId, Novel novel , String imageName, String imagePath) {
        this.memberId = memberId;
        this.novel = novel;
        this.imgName = imageName;
        this.imgPath = imagePath;
    }
}
