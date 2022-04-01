package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.comments.Comments;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsSaveRequestDto {
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
