package com.metaverse.station.back.domain.images;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.metaverse.station.back.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
//    @JsonBackReference
    private Posts posts;

    @Column(nullable = false)
    private String origFileName;

    @Column(nullable = false)
    private String imagePath;

    private Long fileSize;

    @Builder
    public Images(Posts posts, String origFileName, String imagePath, Long fileSize) {
        this.posts = posts;
        this.origFileName = origFileName;
        this.imagePath = imagePath;
        this.fileSize = fileSize;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

//    public void updateImages(String origFileName, String imagePath, Long fileSize){
//        this.origFileName = origFileName;
//        this.imagePath = imagePath;
//        this.fileSize = fileSize;
//    }
}