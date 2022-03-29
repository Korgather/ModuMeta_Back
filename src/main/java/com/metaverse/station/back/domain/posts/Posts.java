package com.metaverse.station.back.domain.posts;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.metaverse.station.back.domain.BaseTimeEntity;
import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "posts")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String link;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(
            mappedBy = "posts",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Images> images = new ArrayList<>();

    @Builder
    public Posts(Long id, String title, String content, String author, String link, List<Images> images, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.link = link;
        this.images = images;
        this.user = user;
    }

    public void addImages(Images image) {
        image.setPosts(this);
    }

    public void addUser(User user){
        this.user = user;
        user.addPost(this);
    }
}
