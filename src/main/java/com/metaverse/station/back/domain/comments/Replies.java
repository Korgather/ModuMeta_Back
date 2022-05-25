package com.metaverse.station.back.domain.comments;

import com.metaverse.station.back.domain.BaseTimeEntity;
import com.metaverse.station.back.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Replies extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentId")
    private Comments comments;

    @Builder
    public Replies(Long id, String content,User user, Comments comments){
        this.id = id;
        this.content = content;
        this.user = user;
        this.comments = comments;
    }

    public void update(String content) {
        this.content = content;
    }
}
