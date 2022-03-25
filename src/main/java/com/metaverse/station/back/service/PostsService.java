package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsRepository;
import com.metaverse.station.back.web.dto.PostsResponseDto;
import com.metaverse.station.back.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Posts save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity());
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
