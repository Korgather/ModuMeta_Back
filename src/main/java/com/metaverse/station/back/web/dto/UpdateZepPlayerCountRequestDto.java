package com.metaverse.station.back.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateZepPlayerCountRequestDto {

    private String hashId;

    private int playerCount;

    @Builder
    public UpdateZepPlayerCountRequestDto(String hashId,int playerCount) {
        this.hashId = hashId;
        this.playerCount = playerCount;
    }
}
