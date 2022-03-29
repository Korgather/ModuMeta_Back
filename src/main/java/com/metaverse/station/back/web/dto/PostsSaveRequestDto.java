package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;
    private String link;
    private User user;

    private List<Images> images;


    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String link, List<Images> images) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.link = link;
        this.images = images;
//        this.user = user;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).link(link).images(images).user(user).build();
    }

    public void setUser(User user){
        this.user = user;
    }
}

