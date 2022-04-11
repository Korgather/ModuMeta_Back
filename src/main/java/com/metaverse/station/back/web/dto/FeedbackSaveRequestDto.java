package com.metaverse.station.back.web.dto;

import com.metaverse.station.back.domain.feedback.Feedback;
import com.metaverse.station.back.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedbackSaveRequestDto {

    private String content;
    private User user;

    public void setUser(User user){

        this.user = user;
    }

    public Feedback toEntity() {

        return Feedback.builder().content(content).user(user).build();
    }
}
