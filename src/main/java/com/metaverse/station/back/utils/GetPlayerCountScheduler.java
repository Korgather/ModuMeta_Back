package com.metaverse.station.back.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsRepository;
import com.metaverse.station.back.web.gathertownApi.PlayerCountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetPlayerCountScheduler {

    private final WebClient.Builder webClientBuild;
    private final PostsRepository postsRepository;

    @Scheduled(initialDelay = 10000, fixedDelay = 30000)
    @Transactional
    public void requestScheduler() {

        String URL = "https://gt-space-data.herokuapp.com/graphql";

        WebClient webClient = webClientBuild.baseUrl(URL).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

//        long startTime = System.currentTimeMillis();

        List<Posts> list = postsRepository.findPostsByCategoryStringContaining("GATHERTOWN");
        String query = "query gameData($apikey:String!,$spaceid:String!,$spacename:String!){gameData(spaceData:{apiKey: $apikey, spaceIdNum: $spaceid, spaceName: $spacename}){playerCount,}}";
        String opertationName = "gameData";

        list.forEach(posts -> {
            String link = posts.getLink();
            String graphqlQuery = null;
            if(link.contains("gather.town")){
                String spaceid = link.substring(link.indexOf("app/")+4,link.lastIndexOf("/"));
                String spacename = link.substring(link.lastIndexOf("/")+1, link.length());
                String variables = "{\"apikey\": \"QUNCVEQGILsqeXR5\",\"spaceid\": \""+spaceid+"\",\"spacename\" : \""+spacename+"\"}";

                try {
                    graphqlQuery = createJsonQueries(query,opertationName,variables);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            Mono<PlayerCountDto> playerCountDtoMono = webClient.post().bodyValue(graphqlQuery)
                    .retrieve()
                    .bodyToMono(PlayerCountDto.class);

            playerCountDtoMono.doOnSuccess(
                    playerCountDto -> {
//                        System.out.println(playerCountDto.getData().gameData.getPlayerCount());
                        posts.setPlayerCount(playerCountDto.getData().gameData.getPlayerCount());
                    }
            ).subscribe();
        });


//        long stopTime = System.currentTimeMillis();
//        long elapsedTime = stopTime - startTime;
//        System.out.println(elapsedTime);

    }

    String createJsonQueries(String graphql, String operationName, String variables) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode wrapper = objectMapper.createObjectNode();
        wrapper.put("query", graphql);
        wrapper.put("operationName", operationName);

        JsonNode jsonNode = objectMapper.readTree(variables);

        wrapper.set("variables", jsonNode);
        String queries = objectMapper.writeValueAsString(wrapper);

        return queries;
    }
}
