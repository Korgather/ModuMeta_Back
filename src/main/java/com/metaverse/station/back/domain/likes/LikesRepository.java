package com.metaverse.station.back.domain.likes;

import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByUserAndPosts(User user, Posts posts);

}

