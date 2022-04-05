package com.metaverse.station.back.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileUpdateRequestDto {

    private String username;
    private String bio;
    private String profileImageUrl;

}
