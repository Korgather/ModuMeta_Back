package com.metaverse.station.back.domain.likes;

import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Posts posts;

    public Likes(Posts posts, User user) {
        this.posts = posts;
        this.user = user;
    }
}
