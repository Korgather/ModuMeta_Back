package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.comments.Comments;
import com.metaverse.station.back.domain.comments.CommentsRepository;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsRepository;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.web.dto.CommentsSaveRequestDto;
import com.metaverse.station.back.web.dto.CommentsSaveRequestResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentsService {

    private final UserService userService;
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;

    public CommentsSaveRequestResponseDto save(Long id,CommentsSaveRequestDto requestDto) {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패 : 해당 게시글이 존재하지 않습니다."));

        requestDto.setUser(user);
        requestDto.setPost(posts);

        Comments comments = requestDto.toEntity();
        commentsRepository.save(comments);

        return new CommentsSaveRequestResponseDto(comments);


    }
}
