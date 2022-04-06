package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.comments.Comments;
import com.metaverse.station.back.domain.comments.Replies;
import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.likes.Likes;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
public class PostsResponseDto {

    private Long id;
    private postUser postUser;
    private String title;
    private String content;
    private int view;
    private final List<ImagesDto> imageList = new ArrayList<>();
    private String link;
    private final List<postComment> postCommentList = new ArrayList<>();
    private final Map<Long,String> likeUserList = new HashMap<>();
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
        private String bio;

        private postUser(User user) {
            this.userId = user.getUserSeq();
            this.username = user.getUsername();
            this.profileImageUrl = user.getProfileImageUrl();
            this.email = user.getEmail();
            this.roleType = user.getRoleType().toString();
            this.bio = user.getBio();
        }
    }

    @Getter
    @NoArgsConstructor
    static class postComment {
        private Long commentId;
        private Long userId;
        private String username;
        private String content;
        private String profileImageUrl;
        private List<postCommentReply> postCommentReplyList = new ArrayList<>();
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        private postComment(Comments comment) {
            this.commentId = comment.getId();
            this.userId = comment.getUser().getUserSeq();
            this.username = comment.getUser().getUsername();
            this.content = comment.getContent();
            this.profileImageUrl = comment.getUser().getProfileImageUrl();
            if(!comment.getRepliesList().isEmpty()){
                for (Replies reply : comment.getRepliesList()) {
                    this.postCommentReplyList.add(new postCommentReply(reply));
                }
            }
            this.createdDate = comment.getCreatedDate();
            this.modifiedDate = comment.getModifiedDate();
        }
    }

    @Getter
    @NoArgsConstructor
    static class postCommentReply {
        private Long commentId;
        private Long replyId;
        private Long userId;
        private String username;
        private String content;
        private String profileImageUrl;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        private postCommentReply(Replies replies) {
            this.commentId = replies.getComments().getId();
            this.replyId = replies.getId();
            this.userId = replies.getUser().getUserSeq();
            this.username = replies.getUser().getUsername();
            this.content = replies.getContent();
            this.profileImageUrl = replies.getUser().getProfileImageUrl();
            this.createdDate = replies.getCreatedDate();
            this.modifiedDate = replies.getModifiedDate();
        }
    }

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.postUser = new postUser(entity.getUser());
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.link = entity.getLink();
        this.view = entity.getView();
        if(!entity.getLikes().isEmpty()) {
            for (Likes likes : entity.getLikes()) {
                likeUserList.put(likes.getUser().getUserSeq(),likes.getUser().getUsername());
            }
        }
        if(entity.getImages() != null){
            for( Images image : entity.getImages()){
                this.imageList.add(new ImagesDto(image));
            }
        }
        if(!entity.getCommentsList().isEmpty()){
            for (Comments comment : entity.getCommentsList()) {
                this.postCommentList.add(new postComment(comment));
            }
        }
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}


