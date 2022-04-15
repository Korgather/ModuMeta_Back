package com.metaverse.station.back.web.gathertownApi;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class GatherTownApiController {

    private final GatherTownService gatherTownService;

    @PostMapping("/api/v1/gathertown/getmap")
    public GatherTownMapResponseDto getMap(@RequestBody GatherTownGetMapRequestDto requestDto) {

        return gatherTownService.getMap(requestDto);
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
