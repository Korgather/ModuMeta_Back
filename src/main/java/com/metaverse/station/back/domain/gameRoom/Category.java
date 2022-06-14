package com.metaverse.station.back.domain.gameRoom;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Category {
    MAFIA("mafia", "마피아게임"),
    OMOK("omok", "오목"),
    NOT_REGISTERED("NOT_REGiSTERED","등록되지 않은 게임");

    private final String code;
    private final String displayName;

    public static Category of(String code) {
        return Arrays.stream(Category.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElse(NOT_REGISTERED);
    }
}
