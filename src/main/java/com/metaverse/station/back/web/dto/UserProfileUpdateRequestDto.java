package com.metaverse.station.back.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;


import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserProfileUpdateRequestDto {


//    @Length(min = 2, max = 10, message = "닉네임은 2글자 이상 10글자 이하로 설정해주세요.")
//    @Pattern(regexp = "^[^~!@#$%^&*()+|<>?:{}]*?$",message = "닉네임에 특수문자를 포함 할 수 없습니다.")
    @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9._-]{2,10}$", message = "닉네임은 2글자 이상 10글자 이하, '-', '_'를 제외한 특수문자 및 공백을 포함 할 수 없습니다.")

    private String username;

    private String bio;
    @URL(message = "프로필 이미지를 선택해주세요.")
    private String profileImageUrl;

}
