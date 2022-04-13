package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.posts.PostsCategory;
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
public class PostsUpdateRequestDto {

    @Enum(enumClass = PostsCategory.class, ignoreCase = true, message = "카테고리를 설정해주세요.")
//    private PostsCategory category;
    private String category;

    @Length(min = 5, message = "제목을 최소 5글자 이상 입력해주세요.")
    private String title;

    @NotBlank(message = "공백을 입력 할 수 없습니다.")
    @Length(min = 10, message = "내용을 최소 10글자 이상 입력해주세요")
    private String content;

    @URL(message = "유효한 URL을 입력해주세요.")
    private String link;

    private List<Images> images;

    @Builder
    public PostsUpdateRequestDto(String category, String title,String link, String content, List<Images> images) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.link = link;
        this.images = images;
    }
}
