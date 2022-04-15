package com.metaverse.station.back.web.gathertownApi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class GatherTownSetMapRequestDto {

    private String apiKey;
    private String spaceId;
    private String mapId;
    private GatherTownMapResponseDto mapContent;

    private int x;
    private int y;
    private String id;
    private int maxDistance;
    private String src;
    private double volume;
    private Boolean loop;
}
