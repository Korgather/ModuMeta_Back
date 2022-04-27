package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.comments.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentsSaveRequestResponseDto {
    private Long commentId;
    private Long userId;
    private String content;
    private String username;
    private String profileImageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentsSaveRequestResponseDto(Comments comments) {
        this.commentId = comments.getId();
        this.userId = comments.getUser().getUserSeq();
        this.content = comments.getContent();
        this.username = comments.getUser().getUsername();
        this.createdDate = comments.getCreatedDate();
        this.modifiedDate = comments.getModifiedDate();
        this.profileImageUrl = comments.getUser().getProfileImageUrl();
    }
}
