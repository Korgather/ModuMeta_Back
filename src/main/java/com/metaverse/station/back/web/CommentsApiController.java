package com.metaverse.station.back.web;


import com.metaverse.station.back.service.CommentsService;
import com.metaverse.station.back.web.dto.CommentsSaveRequestDto;
import com.metaverse.station.back.web.dto.CommentsSaveRequestResponseDto;
import com.metaverse.station.back.web.dto.RepliesSaveRequestDto;
import com.metaverse.station.back.web.dto.RepliesSaveRequestResponseDto;
import lombok.RequiredArgsConstructor;
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

}
