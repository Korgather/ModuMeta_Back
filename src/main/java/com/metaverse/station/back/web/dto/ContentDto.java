package com.metaverse.station.back.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ContentDto {

    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
