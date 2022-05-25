package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.comments.Comments;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CommentsSaveRequestDto {

    @NotBlank(message = "공백을 입력 할 수 없습니다.")
    private String content;

    private User user;
    private Posts posts;

    public void setUser(User user){

        this.user = user;
    }

    public void setPost(Posts posts){

        this.posts = posts;
    }

    public Comments toEntity() {

        return Comments.builder().content(content).user(user).posts(posts).build();
    }

}
