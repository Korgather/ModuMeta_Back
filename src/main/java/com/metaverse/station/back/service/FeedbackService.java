package com.metaverse.station.back.service;


import com.metaverse.station.back.domain.feedback.Feedback;
import com.metaverse.station.back.domain.feedback.FeedbackRepository;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.web.dto.FeedbackSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserService userService;

    public void save(FeedbackSaveRequestDto requestDto){

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        requestDto.setUser(user);

        Feedback feedback = requestDto.toEntity();

        feedbackRepository.save(feedback);
    }
}
