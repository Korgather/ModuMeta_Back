package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsRepository;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.web.dto.PostsResponseDto;
import com.metaverse.station.back.web.dto.PostsSaveRequestDto;
import com.metaverse.station.back.web.dto.PostsSaveRequestResponseDto;
import com.metaverse.station.back.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UserService userService;

    @Transactional
    public PostsSaveRequestResponseDto save(PostsSaveRequestDto requestDto) {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        List<Images> images = requestDto.getImages();

        requestDto.setUser(user);

        Posts posts = requestDto.toEntity();

        if(images != null){
            images.forEach(posts::addImages);
        }

        postsRepository.save(posts);

        return new PostsSaveRequestResponseDto(posts);
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getImages(),requestDto.getLink());

        return id;
    }

    @Transactional
    public String updateView(Long id){
        return "게시글 id: "+id+"  view + " + postsRepository.updateView(id);
    }

//    public List<PostsResponseDto> findAll(Pageable pageable) {
//        Page<Posts> page= postsRepository.findAll(Sort.by("id"));
////        pageable;
//        List<PostsResponseDto> postsResponseDtos = new ArrayList<>();
//        if(page != null){
//            for(Posts posts : page){
//                postsResponseDtos.add(new PostsResponseDto(posts));
//            }
//        }
//        return postsResponseDtos;
//    }

    public Page<PostsResponseDto> findAll(Pageable pageable) {

        Page<Posts> page= postsRepository.findAll(pageable);

        return page.map(PostsResponseDto::new);
    }
}
