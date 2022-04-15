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
    public Mono<GatherTownMapResponseDto> getMap(@RequestBody GatherTownGetMapRequestDto requestDto) {

        return gatherTownService.getMap(requestDto);
    }

    @PostMapping("/api/v1/gathertown/setmap")
    public String setMap(@RequestBody GatherTownGetMapRequestDto requestDto) {

        return gatherTownService.setMusic(requestDto);
//        return gatherTownService.setMusicWithWebclient(requestDto);
    }


}
