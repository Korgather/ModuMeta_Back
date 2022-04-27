package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.notification.Notification;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationResponseDto {

    private Long notificationId;
    private String pub_username;
    private Long postId;
    private String postTitle;

    public NotificationResponseDto(Notification notification) {
        this.notificationId = notification.getId();
        this.pub_username = notification.getPub_username();
        this.postId = notification.getPostId();
        this.postTitle = notification.getPostTitle();
    }

}
