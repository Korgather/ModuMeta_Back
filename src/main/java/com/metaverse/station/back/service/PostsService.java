package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsRepository;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.web.dto.PostsResponseDto;
import com.metaverse.station.back.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UserService userService;

    @Transactional
    public PostsSaveRequestDto save(PostsSaveRequestDto requestDto) {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        List<Images> images = requestDto.getImages();

        Posts posts = requestDto.toEntity();

        posts.addUser(user);
        images.forEach(posts::addImages);

        postsRepository.save(posts);

        return requestDto;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
