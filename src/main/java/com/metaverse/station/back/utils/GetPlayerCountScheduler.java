package com.metaverse.station.back.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsRepository;
import com.metaverse.station.back.service.PostsService;
import com.metaverse.station.back.web.exception.CustomException;
import com.metaverse.station.back.web.gathertownApi.PlayerCountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Exceptions;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetPlayerCountScheduler {

    private final WebClient.Builder webClientBuild;
    private final PostsRepository postsRepository;
    private final PostsService postsService;


    @Scheduled(initialDelay = 1000, fixedDelay = 30000)
//    @Transactional
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
            if(link.contains("gather.town/app/")){
                String spaceid = link.substring(link.indexOf("app/")+4,link.lastIndexOf("/"));
                String spacename = null;
                if(link.contains("?spawn"))
                {
                    spacename = link.substring(link.lastIndexOf("/")+1,link.lastIndexOf("?spawn"));
                }
                else {
                    spacename = link.substring(link.lastIndexOf("/") + 1, link.length());
                }
                String variables = "{\"apikey\": \"2nPMLfFH8AwVAr9e\",\"spaceid\": \""+spaceid+"\",\"spacename\" : \""+spacename+"\"}";

                try {
                    graphqlQuery = createJsonQueries(query,opertationName,variables);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                Mono<PlayerCountDto> playerCountDtoMono;
                try {

                    playerCountDtoMono = webClient.post().bodyValue(graphqlQuery)
                            .retrieve()
//                        .onStatus(httpStatus -> httpStatus.value() == HttpStatus.SERVICE_UNAVAILABLE.value(),
//                                clientResponse -> Mono.error(new WebClientServiceException("서버가 닫혀있습니다",clientResponse.statusCode().value())))
                            .bodyToMono(PlayerCountDto.class);
                    playerCountDtoMono.doOnSuccess(
                            playerCountDto -> {
                                postsService.updatePlayerCount(posts,playerCountDto.getData().gameData.getPlayerCount());
//                            System.out.println(playerCountDto.getData().gameData.getPlayerCount());
//                        posts.setPlayerCount(playerCountDto.getData().gameData.getPlayerCount());
                            }
                    ).subscribe();

                } catch (Exception e) {

                    log.error("서버가 닫혀있습니다 : " + e.getLocalizedMessage());
                }



            }
        });
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
