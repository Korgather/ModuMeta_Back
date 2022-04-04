package com.metaverse.station.back.web;


import com.metaverse.station.back.service.CommentsService;
import com.metaverse.station.back.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentsApiController {

    private final CommentsService commentsService;

    @PostMapping("/api/v1/posts/{id}/comments")
    public CommentsSaveRequestResponseDto save(@PathVariable Long id,@RequestBody CommentsSaveRequestDto requestDto) {

        return commentsService.save(id, requestDto);
    }

    @PostMapping("/api/v1/comments/{id}")
    public RepliesSaveRequestResponseDto saveReply(@PathVariable Long id, @RequestBody RepliesSaveRequestDto requestDto) {

        return commentsService.saveReply(id, requestDto);
    }

    @PutMapping("/api/v1/comments/{id}")
    public ContentDto updateComment(@PathVariable Long id, @RequestBody ContentDto requestDto) {

        return commentsService.updateComment(id, requestDto);
    }

    @PutMapping("/api/v1/replies/{id}")
    public ContentDto updateReply(@PathVariable Long id, @RequestBody ContentDto requestDto) {

        return commentsService.updateReply(id, requestDto);
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {

        return commentsService.deleteComment(id);
    }

    @DeleteMapping("/api/v1/replies/{id}")
    public ResponseEntity<String> deleteReply(@PathVariable Long id) {

        return commentsService.deleteReply(id);
    }


}
