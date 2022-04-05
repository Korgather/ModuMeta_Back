package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.likes.Likes;
import com.metaverse.station.back.domain.likes.LikesRepository;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsRepository;
import com.metaverse.station.back.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final PostsRepository postsRepository;
    private final UserService userService;

    @Transactional
    public boolean addLike(Long postId) {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        Posts posts = postsRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));;

        Optional<Likes> likes = likesRepository.findByUserAndPosts(user, posts);

        if (likes.isEmpty()) {
            likesRepository.save(new Likes(posts, user));
            return true;
        } else {
            likesRepository.delete(likes.orElseThrow(() -> new IllegalArgumentException("잘못된 요청입니다.") ));
            return false;
        }

    }



}
