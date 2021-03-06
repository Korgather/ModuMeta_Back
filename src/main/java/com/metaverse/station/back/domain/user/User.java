package com.metaverse.station.back.domain.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metaverse.station.back.domain.BaseTimeEntity;
import com.metaverse.station.back.domain.notification.Notification;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.oauth.domain.ProviderType;
import com.metaverse.station.back.oauth.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
@DynamicInsert
public class User extends BaseTimeEntity {
    @JsonIgnore
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Column(name = "USER_ID", length = 64, unique = true)
    @NotNull
    @Size(max = 64)
    private String userId;

    @Column(name = "USERNAME", length = 100)
    @NotNull
    @Size(max = 100)
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    @Column(name = "EMAIL", length = 512, unique = true)
//    @NotNull
    @Size(max = 512)
    private String email;

    @Column(name = "EMAIL_VERIFIED_YN", length = 1)
    @NotNull
    @Size(min = 1, max = 1)
    private String emailVerifiedYn;

    @Column(name = "PROFILE_IMAGE_URL", length = 512)
    @NotNull
    @Size(max = 512)
    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "USER_NAME_MODIFIED_YN", length = 1)
    @Size(min = 1, max = 1)
    @ColumnDefault("'N'")
    private String usernameModifiedYn;

    @Column(length = 500)
    private String bio;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST},orphanRemoval = true)
    private List<Posts> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST},orphanRemoval = true)
    private List<Notification> notificationList = new ArrayList<>();


    @Builder
    public User(
            @NotNull @Size(max = 64) String userId,
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 1) String emailVerifiedYn,
            @NotNull @Size(max = 512) String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType
//            List<Posts> postList
    ) {
        this.userId = userId;
        this.username = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
    }


//    public void addPost(Posts posts) {
//        postList.add(posts);
//    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public void update(String userName, String bio, String profileImageUrl) {
        this.username = userName;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
    }

    public void setProfileImageUrl(String imageUrl) {
        this.profileImageUrl = imageUrl;
    }

    public void setUserNameModifiedYn(String usernameModifiedYn) {
        this.usernameModifiedYn = usernameModifiedYn;
    }
}
