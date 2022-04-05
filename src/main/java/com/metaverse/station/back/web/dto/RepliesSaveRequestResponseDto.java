package com.metaverse.station.back.web.dto;


import com.metaverse.station.back.domain.comments.Replies;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RepliesSaveRequestResponseDto {
    private Long replyId;
    private Long userId;
    private String content;
    private String username;
    private String profileImageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public RepliesSaveRequestResponseDto(Replies replies) {
        this.replyId = replies.getId();
        this.userId = replies.getUser().getUserSeq();
        this.content = replies.getContent();
        this.username = replies.getUser().getUsername();
        this.createdDate = replies.getCreatedDate();
        this.modifiedDate = replies.getModifiedDate();
        this.profileImageUrl = replies.getUser().getProfileImageUrl();
    }
}


