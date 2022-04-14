package com.metaverse.station.back.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserNameDto {

    @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9._-]{2,10}$", message = "닉네임은 2글자 이상 10글자 이하, '-', '_'를 제외한 특수문자 및 공백을 포함 할 수 없습니다.")
    private String username;
}
