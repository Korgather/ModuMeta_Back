package com.metaverse.station.back.web;

import com.metaverse.station.back.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikesApiController {

    private final LikesService likesService;

    @PostMapping("/api/v1/like/{postId}")
    public boolean addLike(@PathVariable Long postId) {

        return likesService.addLike(postId);

    }
}
