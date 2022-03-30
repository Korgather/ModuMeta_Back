package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String userName;
    private String profileImageUrl;
    private String email;
    private String roleType;
    private String emailVerifiedYn;
    private List<Long> postList = new ArrayList<>();

    public UserResponseDto(User user) {

        this.userId = user.getUserSeq();
        this.userName = user.getUsername();
        this.profileImageUrl = user.getProfileImageUrl();
        this.email = user.getEmail();
        this.roleType = user.getRoleType().toString();
        this.emailVerifiedYn = user.getEmailVerifiedYn();

        if (user.getPostList() != null) {
            for (Posts post : user.getPostList()) {
                postList.add(post.getId());
            }
        }

    }
}
