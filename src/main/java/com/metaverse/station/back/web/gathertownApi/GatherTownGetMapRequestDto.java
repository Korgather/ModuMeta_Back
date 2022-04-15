package com.metaverse.station.back.web.gathertownApi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class GatherTownGetMapRequestDto {

    private String apiKey;
    private String spaceId;
    private String mapId;
//    private String mapContent;
    private GatherTownMapResponseDto mapContent;
}
