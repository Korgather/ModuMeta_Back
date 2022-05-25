package com.metaverse.station.back.web;

import com.metaverse.station.back.service.PostsService;
import com.metaverse.station.back.utils.S3Uploader;
import com.metaverse.station.back.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final S3Uploader s3Uploader;


    @GetMapping("/api/v1/posts")
    public Page<PostsResponseDto> findAllByCategory(@RequestParam(required = false) String keyword,
                                                    @RequestParam(required = false) String category,
                                                    @PageableDefault(size = 8,sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        if(category.isEmpty()) category = "METAVERSE";

        if (keyword.isEmpty()) {
            return postsService.findAllByCategory(category, pageable);
        } else if (category.equals("_")) {
            return postsService.findAll(pageable);
        } else {
            return postsService.findByContentTitleCategory(keyword, category, pageable);
        }
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {

        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts/view/{id}")
    public String viewCountUp(@PathVariable Long id) {

        return postsService.updateView(id);
    }

    @GetMapping("/api/v1/posts/likepost/{id}")
    public Page<PostsResponseDto> getLikePost(@PathVariable Long id,
                                              @RequestParam(required = false) String category,
                                              @PageableDefault(size = 6,sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        if(category.isEmpty()) category = "METAVERSE";

        return postsService.findByLikeUserId(id,category,pageable);
    }

    @GetMapping("/api/v1/posts/userid/{id}")
    public Page<PostsResponseDto> getUserPost(@PathVariable Long id,
                                              @RequestParam(required = false) String category,
                                              @PageableDefault(size = 6,sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        if(category == null) category = "METAVERSE";

        return postsService.findByUserId(id, category, pageable);
    }

    @PostMapping("/api/v1/posts")
    public PostsSaveRequestResponseDto save(@Valid @RequestBody PostsSaveRequestDto requestDto) {

        return postsService.save(requestDto);
    }

    @PostMapping("/api/v1/upload")
    @ResponseBody
    public List<String> upload(@RequestParam("data") List<MultipartFile> multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "static");
    }


    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        return postsService.deletePost(id);
    }
}
