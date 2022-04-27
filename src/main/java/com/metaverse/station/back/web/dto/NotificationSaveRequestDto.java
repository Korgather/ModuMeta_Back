package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.notification.Notification;
import com.metaverse.station.back.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class NotificationSaveRequestDto {


    private User user;
    private String pub_username;
    private String postTitle;
    private Long postId;

    @Builder
    public NotificationSaveRequestDto(User user, String pub_username, Long postId, String postTitle) {
        this.user = user;
        this.pub_username = pub_username;
        this.postId = postId;
        this.postTitle = postTitle;
    }

    public Notification toEntity() {
        return Notification.builder().user(user).pub_username(pub_username).postId(postId).postTitle(postTitle).build();
    }

}
