package com.metaverse.station.back.domain.notification;

import com.metaverse.station.back.domain.BaseTimeEntity;
import com.metaverse.station.back.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_userId")
    private User user;

    private String pub_username;

    private Long postId;

    private String postTitle;



    // xx님이 '제목'글에 댓글을 달았습니다.

    @Builder
    public Notification(Long id, User user, String pub_username, Long postId, String postTitle) {
        this.id = id;
        this.user = user;
        this.pub_username = pub_username;
        this.postId = postId;
        this.postTitle = postTitle;
    }

}
