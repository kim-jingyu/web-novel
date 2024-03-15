package com.webnovel.cover.dto;

import com.webnovel.cover.domain.Cover;
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
    private Long novelId;
    private String imgName;
    private String imgPath;

    public Cover toEntity() {
        Cover build = Cover.builder()
                .writerId(writerId)
                .novelId(novelId)
                .imgName(imgName)
                .imgPath(imgPath)
                .build();
        return build;
    }

    @Builder
    public CoverDto (Long writerId, Long novelId ,String imageName,String imagePath) {
        this.writerId = writerId;
        this.novelId = novelId;
        this.imgName = imageName;
        this.imgPath = imagePath;
    }
}
