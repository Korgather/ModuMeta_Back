package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.comments.Comments;
import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostsResponseDto {

    private Long id;
    private postUser postUser;
    private String title;
    private String content;
    private int view;
    private List<String> imageList = new ArrayList<>();
    private String link;
    private List<postComment> postCommentList = new ArrayList<>();
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    @Getter
    @NoArgsConstructor
    static class postUser {
        private Long userId;
        private String username;
        private String profileImageUrl;
        private String email;
        private String roleType;

        private postUser(User user) {
            this.userId = user.getUserSeq();
            this.username = user.getUsername();
            this.profileImageUrl = user.getProfileImageUrl();
            this.email = user.getEmail();
            this.roleType = user.getRoleType().toString();
        }
    }

    @Getter
    @NoArgsConstructor
    static class postComment {
        private Long userId;
        private String username;
        private String content;
        private String profileImageUrl;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        private postComment(Comments comment) {
            this.userId = comment.getUser().getUserSeq();
            this.username = comment.getUser().getUsername();
            this.content = comment.getContent();
            this.profileImageUrl = comment.getUser().getProfileImageUrl();
            this.createdDate = comment.getCreatedDate();
            this.modifiedDate = comment.getModifiedDate();
        }
    }

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.postUser = new postUser(entity.getUser());
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.link = entity.getLink();
        this.view = entity.getView();
        if(entity.getImages() != null){
            for( Images image : entity.getImages()){
                this.imageList.add(image.getImagePath());
            }
        }
        if(entity.getCommentsList().size() != 0){
            for (Comments comment : entity.getCommentsList()) {
                this.postCommentList.add(new postComment(comment));
            }
        }
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}


