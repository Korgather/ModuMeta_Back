package com.metaverse.station.back.web.gathertownApi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GatherTownApiController {

    private final GatherTownService gatherTownService;

    @PostMapping("/api/v1/gathertown/getmap")
    public GatherTownMapResponseDto getMap(@RequestBody GatherTownGetMapRequestDto requestDto) {

        return gatherTownService.getMap(requestDto.getApiKey(),requestDto.getSpaceId(),requestDto.getMapId());
    }
    @PostMapping("/api/v1/gathertown/setmap")
    public String setMap(@RequestBody GatherTownSetMapRequestDto requestDto) {

        return gatherTownService.setMap(requestDto);
//        return gatherTownService.setMusicWithWebclient(requestDto);
    }

    @PostMapping("/api/v1/gathertown/setmap/music")
    public String setMusic(@RequestBody GatherTownSetMapRequestDto requestDto) {

        return gatherTownService.setMusic(requestDto);
//        return gatherTownService.setMusicWithWebclient(requestDto);
    }
}
