package com.metaverse.station.back.web.gathertownApi;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class GatherTownService {

    private final RestTemplate restTemplate;

    public GatherTownService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Mono<GatherTownMapResponseDto> getMap(GatherTownGetMapRequestDto requestDto) {
        String apiUrl = "https://api.gather.town/api/getMap";

        WebClient client = WebClient.create(apiUrl);

        return client.get().uri(uriBuilder -> uriBuilder
                        .queryParam("spaceId", requestDto.getSpaceId().replace("/", "\\").replace("%20", " "))
                        .queryParam("mapId", requestDto.getMapId())
                        .queryParam("apiKey", requestDto.getApiKey())
                        .build())
                .retrieve()
                .bodyToMono(GatherTownMapResponseDto.class);
    }

    public String setMusic(GatherTownGetMapRequestDto requestDto) {

        String apiUrl = "https://api.gather.town/api/setMap";

        WebClient client = WebClient.create(apiUrl);

        GatherTownMapResponseDto gatherTownMap = getMap(requestDto).share().block();

        GatherTownSampleMusic gatherTownSampleMusic = new GatherTownSampleMusic();
        gatherTownSampleMusic.setX(10);
        gatherTownSampleMusic.setY(10);
        gatherTownSampleMusic.sound.setSrc("https://cdn.gather.town/storage.googleapis.com/gather-town.appspot.com/internal-dashboard/sounds/jAlXIofmjkQFHBM_oYl-F");
        gatherTownSampleMusic.sound.setLoop(false);
        gatherTownSampleMusic.sound.setVolume(0.5);
        gatherTownSampleMusic.sound.setMaxDistance(30);

        gatherTownMap.getObjects().add(gatherTownSampleMusic);

        requestDto.setMapContent(gatherTownMap);
        requestDto.setSpaceId(requestDto.getSpaceId().replace("/", "\\").replace("%20", " "));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GatherTownGetMapRequestDto> entity = new HttpEntity<>(requestDto, headers);
        return restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                String.class
        ).getBody();

//        return gatherTownMap;
    }


    public String setMusicWithWebclient(GatherTownGetMapRequestDto requestDto) {

        String apiUrl = "https://api.gather.town/api/setMap";

        WebClient client = WebClient.create(apiUrl);
        GatherTownMapResponseDto gatherTownMap = getMap(requestDto).share().block();

        GatherTownSampleMusic gatherTownSampleMusic = new GatherTownSampleMusic();
        gatherTownSampleMusic.setX(10);
        gatherTownSampleMusic.setY(10);
        gatherTownSampleMusic.sound.setSrc("https://cdn.gather.town/storage.googleapis.com/gather-town.appspot.com/internal-dashboard/sounds/jAlXIofmjkQFHBM_oYl-F");
        gatherTownSampleMusic.sound.setLoop(false);
        gatherTownSampleMusic.sound.setVolume(0.5);
        gatherTownSampleMusic.sound.setMaxDistance(30);

        gatherTownMap.getObjects().add(gatherTownSampleMusic);

        requestDto.setMapContent(gatherTownMap);
        requestDto.setSpaceId(requestDto.getSpaceId().replace("/", "\\").replace("%20", " "));


        return  client.
                post().
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                bodyValue(requestDto).
                retrieve().
                bodyToMono(String.class).toString();

    }

    public HttpEntity<Object> getMapRestemplate(GatherTownGetMapRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String apiUrl = "https://gather.town/api/getMap";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiUrl).
                queryParam("spaceId", "{spaceId}").
                queryParam("mapId", "{mapId}").
                queryParam("apiKey", "{apiKey}").
                encode().
                toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("spaceId", requestDto.getSpaceId().replace("/", "\\").replace("%20", " "));
        params.put("mapId", requestDto.getMapId());
        params.put("apiKey", requestDto.getApiKey());

        System.out.println(params.get("apiKey"));


        ResponseEntity<Object> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                Object.class,
                params
        );
        return response;
    }
}