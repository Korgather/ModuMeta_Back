package com.metaverse.station.back.web;


import com.metaverse.station.back.service.CommentsService;
import com.metaverse.station.back.web.dto.CommentsSaveRequestDto;
import com.metaverse.station.back.web.dto.CommentsSaveRequestResponseDto;
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

}
