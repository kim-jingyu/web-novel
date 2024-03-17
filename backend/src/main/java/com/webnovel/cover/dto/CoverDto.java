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

    private Long writerId;
    private Novel novel;
    private String imgName;
    private String imgPath;

    public Cover toEntity() {
        Cover build = Cover.builder()
                .writerId(writerId)
                .novel(novel)
                .imgName(imgName)
                .imgPath(imgPath)
                .build();
        return build;
    }

    @Builder
    public CoverDto (Long writerId, Novel novel , String imageName, String imagePath) {
        this.writerId = writerId;
        this.novel = novel;
        this.imgName = imageName;
        this.imgPath = imagePath;
    }
}
