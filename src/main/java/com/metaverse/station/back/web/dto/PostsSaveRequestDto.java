package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;
    private String link;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String link) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.link = link;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).link(link).build();
    }
}

