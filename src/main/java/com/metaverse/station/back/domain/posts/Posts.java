package com.metaverse.station.back.domain.posts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.metaverse.station.back.domain.BaseTimeEntity;
import com.metaverse.station.back.domain.comments.Comments;
import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.likes.Likes;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.oauth.domain.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "POST_CATEGORY", length = 20)
    @Enumerated(EnumType.STRING)
    private PostsCategory postsCategory;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
//    @JsonIgnore
    private User user;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;


    @OneToMany(
            mappedBy = "posts",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Images> images = new ArrayList<>();

    @OneToMany(
            mappedBy = "posts",
            cascade = {CascadeType.REMOVE},
            fetch = FetchType.EAGER
    )
    private List<Comments> commentsList;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    Set<Likes> likes = new HashSet<>();

    @Builder
    public Posts(Long id,PostsCategory category, String title, String content, String link, List<Images> images, User user, List<Comments> commentsList) {
        this.id = id;
        this.postsCategory = category;
        this.title = title;
        this.content = content;
        this.link = link;
        this.images = images;
        this.user = user;
        this.commentsList = commentsList;
    }

    public void update(String title, String content, List<Images> images, String link) {
        this.title = title;
        this.content = content;
        this.images.clear();
        this.images.addAll(images);
        this.link = link;
    }

    public void addImages(Images image) {
        image.setPosts(this);
    }

    public void addUser(User user){
        this.user = user;
//        user.addPost(this);
    }
}
