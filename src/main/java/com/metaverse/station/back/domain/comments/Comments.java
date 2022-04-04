package com.metaverse.station.back.domain.comments;

import com.metaverse.station.back.domain.BaseTimeEntity;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Posts posts;

    @OneToMany(mappedBy = "comments", cascade = CascadeType.ALL)
    private List<Replies> repliesList = new ArrayList<>();


//    public void addUser(User user){
//        this.user = user;
//    }

    @Builder
    public Comments(Long id, String content,User user, Posts posts){
        this.id = id;
        this.content = content;
        this.user = user;
        this.posts = posts;
    }

    public void update(String content) {
        this.content = content;
    }

}
