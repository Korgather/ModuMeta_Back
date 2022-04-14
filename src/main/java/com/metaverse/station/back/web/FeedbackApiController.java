package com.metaverse.station.back.web;

import com.metaverse.station.back.service.FeedbackService;
import com.metaverse.station.back.web.dto.FeedbackSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeedbackApiController {
    private final FeedbackService feedbackService;

    @PostMapping("/api/v1/feedback")
    public void saveFeedback(@RequestBody FeedbackSaveRequestDto requestDto) {

        feedbackService.save(requestDto);
    }

}
