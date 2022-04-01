package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostsSaveRequestResponseDto {

    private String title;
    private String content;
    private String link;
    private postUser user;
    private List<String> imageList = new ArrayList<>();
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    @Getter
    @NoArgsConstructor
    static class postUser {
        private String username;
        private String profileImageUrl;
        private String email;
//        private List<Long> postList = new ArrayList<>();
        private String roleType;

        private postUser(User user) {
            this.username = user.getUsername();
            this.profileImageUrl = user.getProfileImageUrl();
            this.email = user.getEmail();
//            if (user.getPostList() != null) {
//                for (Posts post : user.getPostList()) {
//                    System.out.println(user.getPostList().size());
//                    postList.add(post.getId());
//                }
//            }
            this.roleType = user.getRoleType().toString();
        }
    }

    public PostsSaveRequestResponseDto(Posts posts) {
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.link = posts.getLink();
        if(posts.getImages() != null){
            for( Images image : posts.getImages()){
                imageList.add(image.getImagePath());
            }
        }
        this.user = new postUser(posts.getUser());
        this.modifiedDate = posts.getModifiedDate();
        this.createdDate = posts.getCreatedDate();
    }

}

