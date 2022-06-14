package com.metaverse.station.back.domain.gameRoom.omok;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class OmokUserUpdateRequestDto {

    private Long id;

    @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9._-]{2,10}$", message = "닉네임은 2글자 이상 10글자 이하, '-', '_'를 제외한 특수문자 및 공백을 포함 할 수 없습니다.")
    private String nickname;

    private int win;

    private int lose;

    public OmokUserUpdateRequestDto(Long id, String nickname, int win, int lose){
        this.id = id;
        this.nickname = nickname;
        this.win = win;
        this.lose = lose;
    }
}
