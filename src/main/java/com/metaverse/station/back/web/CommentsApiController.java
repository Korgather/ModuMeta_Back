package com.metaverse.station.back.web;

import com.metaverse.station.back.service.PostsService;
import com.metaverse.station.back.utils.S3Uploader;
import com.metaverse.station.back.web.dto.PostsResponseDto;
import com.metaverse.station.back.web.dto.PostsSaveRequestDto;
import com.metaverse.station.back.web.dto.PostsSaveRequestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentsApiController {


    @PostMapping("/api/v1/comments")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {

        return null;
    }

}
