package com.metaverse.station.back.web;

import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.service.PostsService;
import com.metaverse.station.back.utils.S3Uploader;
import com.metaverse.station.back.web.dto.PostsResponseDto;
import com.metaverse.station.back.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final S3Uploader s3Uploader;

    @PostMapping("/api/v1/posts")
    public Posts save(@RequestBody PostsSaveRequestDto requestDto) {

        return postsService.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @PostMapping("/api/v1/upload")
    @ResponseBody
    public List<String> upload(@RequestParam("data") List<MultipartFile> multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "static");
    }

}
