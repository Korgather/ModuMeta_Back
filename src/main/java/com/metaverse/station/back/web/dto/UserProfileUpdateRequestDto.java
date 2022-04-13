package com.metaverse.station.back.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserProfileUpdateRequestDto {

    @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9._-]{2,10}\\$", message = "닉네임은 2자이상, 10글자 미만, 공백을 포함 할 수 없습니다.")
    private String username;
    private String bio;
    @URL(message = "프로필 이미지를 선택해주세요.")
    private String profileImageUrl;

}
