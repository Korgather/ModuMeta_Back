package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.images.Images;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImagesDto {

    private String origFileName;
    private String imagePath;
    private Long fileSize;

    public ImagesDto(Images images) {
        this.origFileName = images.getOrigFileName();
        this.imagePath = images.getImagePath();
        this.fileSize = images.getFileSize();
    }
}
