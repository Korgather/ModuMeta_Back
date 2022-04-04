package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.comments.Comments;
import com.metaverse.station.back.domain.comments.CommentsRepository;
import com.metaverse.station.back.domain.comments.Replies;
import com.metaverse.station.back.domain.comments.RepliesRepository;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsRepository;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentsService {

    private final UserService userService;
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;
    private final RepliesRepository repliesRepository;

    @Transactional
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

    @Transactional
    public RepliesSaveRequestResponseDto saveReply(Long id, RepliesSaveRequestDto requestDto) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());

        Comments comments = commentsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("댓글 쓰기 실패: 해당 댓글이 존재하지 않습니다."));

        requestDto.setUser(user);
        requestDto.setComments(comments);

        Replies replies = requestDto.toEntity();

        repliesRepository.save(replies);

        return new RepliesSaveRequestResponseDto(replies);

    }

    @Transactional
    public ResponseEntity<String> deleteComment(Long id) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());
        Comments comments = commentsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("댓글 삭제 실패: 해당 댓글이 존재하지 않습니다."));

        if(comments.getUser() == user){
            commentsRepository.delete(comments);
            return ResponseEntity.ok().body("Success");
        }
        else {
            return ResponseEntity.badRequest().body("Fail");
        }

    }

    @Transactional
    public ResponseEntity<String> deleteReply(Long id) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());
        Replies replies = repliesRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("댓글 삭제 실패: 해당 댓글이 존재하지 않습니다."));

        if(replies.getUser() == user){
            repliesRepository.delete(replies);
            return ResponseEntity.ok().body("Success");
        }
        else {
            return ResponseEntity.badRequest().body("Fail");
        }

    }

    @Transactional
    public ContentDto updateComment(Long id, ContentDto requestDto) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());
        Comments comment = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        if(comment.getUser() == user) {
            comment.update(requestDto.getContent());
            return requestDto;
        }else {
            return null;
        }
    }

    @Transactional
    public ContentDto updateReply(Long id, ContentDto requestDto) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());
        Replies reply = repliesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if (reply.getUser() == user) {
            reply.update(requestDto.getContent());
            return requestDto;
        }else {
            return null;
        }
    }
}
