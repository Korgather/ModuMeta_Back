package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsCategory;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.web.exception.Enum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    @Length(min = 5, message = "제목을 최소 5글자 이상 입력해주세요.")
    private String title;

    @Enum(enumClass = PostsCategory.class, ignoreCase = true, message = "카테고리를 설정해주세요.")
//    private PostsCategory category;
    private String category;

    @NotBlank(message = "공백을 입력 할 수 없습니다.")
    @Length(max = 2000, message = "본문 내용 길이가 너무 깁니다.")
    private String content;

    @URL(message = "유효한 URL을 입력해주세요.")
    private String link;
    private User user;
    private List<Images> images;


    @Builder
    public PostsSaveRequestDto(String title, String content, String link, List<Images> images, User user) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.images = images;
        this.user = user;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).link(link).images(images).user(user).build();
    }

    public void setUser(User user){
        this.user = user;
    }
}

