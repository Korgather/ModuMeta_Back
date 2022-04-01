package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.images.Images;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String title;
    private String content;
    private String link;
    private List<Images> images;

    @Builder
    public PostsUpdateRequestDto(String title,String link, String content, List<Images> images) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.images = images;
    }
}
