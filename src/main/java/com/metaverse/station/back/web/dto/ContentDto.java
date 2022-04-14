package com.metaverse.station.back.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ContentDto {

    @NotBlank(message = "공백을 입력 할 수 없습니다.")
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
