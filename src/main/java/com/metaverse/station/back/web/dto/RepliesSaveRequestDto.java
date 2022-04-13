package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.comments.Comments;
import com.metaverse.station.back.domain.comments.Replies;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class RepliesSaveRequestDto {

    @NotBlank(message = "공백을 입력 할 수 없습니다.")
    private String content;

    private User user;
    private Comments comments;

    public void setUser(User user){

        this.user = user;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Replies toEntity() {

        return Replies.builder().content(content).user(user).comments(comments).build();
    }

}
