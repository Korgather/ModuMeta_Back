package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.images.ImagesRepository;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsCategory;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ImagesRepository imagesRepository;

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

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        List<Images> images = requestDto.getImages();

        if(images != null){
            images.forEach(posts::addImages);
        }

        if(posts.getUser() == user) {
            posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getImages(),requestDto.getLink());
        }

        return id;
    }

    @Transactional
    public String updateView(Long id){
        return "게시글 id: "+id+"  view + " + postsRepository.updateView(id);
    }

    public Page<PostsResponseDto> findAll(Pageable pageable) {

        Page<Posts> page= postsRepository.findAll(pageable);

        return page.map(PostsResponseDto::new);
    }

    @Transactional
    public ResponseEntity<String> deletePost(Long id) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        if(posts.getUser() == user){
            postsRepository.delete(posts);
            return ResponseEntity.ok().body("Success");
        }
        else {
            return ResponseEntity.badRequest().body("Fail");
        }

    }

    public Page<PostsResponseDto> findByLikeUserId(Long id, Pageable pageable){

        Page<Posts> page = postsRepository.findPostsByLikesUserUserSeq(id,pageable);

        return page.map(PostsResponseDto::new);
    }

    public Page<PostsResponseDto> findByUserId(Long id, Pageable pageable){

        Page<Posts> page = postsRepository.findPostsByUserUserSeq(id,pageable);

        return page.map(PostsResponseDto::new);
    }

    public Page<PostsResponseDto> findByContentOrTitle(String text, Pageable pageable) {

        Page<Posts> page = postsRepository.findPostsByContentContainingIgnoreCaseOrTitleContainingIgnoreCase(text,text, pageable);


        return page.map(PostsResponseDto::new);
    }

    public Page<PostsResponseDto> findByCategory(PostsCategory postsCategory, Pageable pageable) {

        Page<Posts> page = postsRepository.findPostsByPostsCategory(postsCategory, pageable);

        return page.map(PostsResponseDto::new);
    }
}
