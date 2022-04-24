package com.metaverse.station.back;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.posts.PostsRepository;
import com.metaverse.station.back.web.gathertownApi.PlayerCountDto;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Stream;


//@SpringBootTest(
//        properties = {
//                "spring.config.location=classpath:application-test-test.properties"
//        }
//
//)
public class RequestToGraphql {

//    @MockBean
//    private ClientRegistrationRepository clientRegistrationRepository;
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @Autowired
//    WebClient.Builder webClientBuilder;
//
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void postToGraphql2() throws JsonProcessingException, JSONException {
//        String URL = "https://gt-space-data.herokuapp.com/graphql";
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//
//
//        headers.add("content-type", "application/json"); // just modified graphql into json
//
//        long startTime = System.currentTimeMillis();
//
//        List<Posts> list = postsRepository.findPostsByCategoryStringContaining("GATHERTOWN");
//        String query = "query gameData($apikey:String!,$spaceid:String!,$spacename:String!){gameData(spaceData:{apiKey: $apikey, spaceIdNum: $spaceid, spaceName: $spacename}){playerCount,}}";
//        String opertationName = "gameData";
////        String spaceid = "At7RgsOoNxKkCFCY";
////        String spacename = "il%20giardino%20di%20cassyboboh";
////        String variables = "{\"apikey\": \"QUNCVEQGILsqeXR5\",\"spaceid\": \""+spaceid+"\",\"spacename\" : \""+spacename+"\"}";
//
//
//        list.forEach(posts -> {
//            String link = posts.getLink();
//            if(link.contains("gather.town")){
//                String spaceid = link.substring(link.indexOf("app/")+4,link.lastIndexOf("/"));
//                String spacename = link.substring(link.lastIndexOf("/")+1, link.length());
//                String variables = "{\"apikey\": \"QUNCVEQGILsqeXR5\",\"spaceid\": \""+spaceid+"\",\"spacename\" : \""+spacename+"\"}";
//
//                try {
//                    ResponseEntity<PlayerCountDto> response =
//                            restTemplate.postForEntity(URL, new HttpEntity<>(createJsonQueries(query,opertationName,variables), headers), PlayerCountDto.class);
//                    int playerCount = Objects.requireNonNull(response.getBody()).getData().getGameData().playerCount;
//                    posts.setPlayerCount(playerCount);
//                    System.out.println(playerCount);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
////                System.out.println("spaceid: "+ spaceid);
////                System.out.println("spacename : " +spacename);
//
//            }
//            System.out.println(posts.getLink());
//        });
//
//        long stopTime = System.currentTimeMillis();
//        long elapsedTime = stopTime - startTime;
//
//        System.out.println(elapsedTime);
//
////        ResponseEntity<PlayerCountDto> response = restTemplate.postForEntity(URL, new HttpEntity<>(createJsonQueries(query,opertationName,variables), headers), PlayerCountDto.class);
////        System.out.println("The response================="+response);
////        System.out.println(Objects.requireNonNull(response.getBody()).getData().getGameData().playerCount);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void postToGraphql3() throws JsonProcessingException, JSONException, InterruptedException {
//        String URL = "https://gt-space-data.herokuapp.com/graphql";
//
//        WebClient webClient = webClientBuilder.baseUrl(URL).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//
////        WebClient webClient = WebClient.builder().baseUrl(URL).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//
//        long startTime = System.currentTimeMillis();
//
//        List<Posts> list = postsRepository.findPostsByCategoryStringContaining("GATHERTOWN");
//        String query = "query gameData($apikey:String!,$spaceid:String!,$spacename:String!){gameData(spaceData:{apiKey: $apikey, spaceIdNum: $spaceid, spaceName: $spacename}){playerCount,}}";
//        String opertationName = "gameData";
//
//        List<Mono<PlayerCountDto>> playerCountDtoList = new ArrayList<>();
//
//        list.forEach(posts -> {
//            String link = posts.getLink();
//            String graphqlQuery = null;
//            if(link.contains("gather.town")){
//                String spaceid = link.substring(link.indexOf("app/")+4,link.lastIndexOf("/"));
//                String spacename = link.substring(link.lastIndexOf("/")+1, link.length());
//                String variables = "{\"apikey\": \"QUNCVEQGILsqeXR5\",\"spaceid\": \""+spaceid+"\",\"spacename\" : \""+spacename+"\"}";
//
//                try {
//                    graphqlQuery = createJsonQueries(query,opertationName,variables);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//            }
//            //blocking call
////            System.out.println(webClient.post().bodyValue(graphqlQuery).retrieve().bodyToMono(PlayerCountDto.class).block().getData().getGameData().playerCount);
////            playerCountDtoList.add(webClient.post().bodyValue(graphqlQuery).retrieve().bodyToMono(PlayerCountDto.class));
////            webClient.post().bodyValue(graphqlQuery)
////                    .retrieve()
////                    .bodyToMono(PlayerCountDto.class)
////                    .doOnSuccess(
////                        playerCountDto -> {
////                            System.out.println(playerCountDto.getData().getGameData().playerCount);
////                            posts.setPlayerCount(playerCountDto.getData().getGameData().playerCount);
////                            System.out.println(posts.getLink());
////                        }
////            ).subscribe();
//
//            Mono<PlayerCountDto> playerCountDtoMono = webClient.post().bodyValue(graphqlQuery)
//                    .retrieve()
//                    .bodyToMono(PlayerCountDto.class);
//
//            playerCountDtoMono.doOnSuccess(
//                    playerCountDto -> {
//                        System.out.println(playerCountDto.getData().gameData.getPlayerCount());
//                    }
//            ).log().subscribe();
//        });
//        String variables = "{\"apikey\": \"QUNCVEQGILsqeXR5\",\"spaceid\": \""+"cDRgrckO2OfoZSJc"+"\",\"spacename\" : \""+"onethepl"+"\"}";
//
//        wait(10000);
//
////        webClient.post().bodyValue(createJsonQueries(query,opertationName,variables)).retrieve().bodyToMono(PlayerCountDto.class).doOnSuccess(
////                playerCountDto -> {
////                    System.out.println("aa" +playerCountDto.getData().getGameData().playerCount);
////                }
////        ).block();
//
////        playerCountDtoList.forEach(
////                mono->{
////                    mono.block();
////                    mono.doOnSuccess(
////                            playerCountDto -> {
////                                System.out.println(playerCountDto.getData().gameData.getPlayerCount());
////                            }
////                    ).subscribe();
////                }
////        );
//
//
//        long stopTime = System.currentTimeMillis();
//        long elapsedTime = stopTime - startTime;
//
//        System.out.println(elapsedTime);
//
////        ResponseEntity<PlayerCountDto> response = restTemplate.postForEntity(URL, new HttpEntity<>(createJsonQueries(query,opertationName,variables), headers), PlayerCountDto.class);
////        System.out.println("The response================="+response);
////        System.out.println(Objects.requireNonNull(response.getBody()).getData().getGameData().playerCount);
//    }
//
//    String createJsonQueries(String graphql, String operationName, String variables) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode wrapper = objectMapper.createObjectNode();
//        wrapper.put("query", graphql);
//        wrapper.put("operationName", operationName);
//
//        JsonNode jsonNode = objectMapper.readTree(variables);
//
//        wrapper.set("variables", jsonNode);
//        String queries = objectMapper.writeValueAsString(wrapper);
//
//        return queries;
//    }

}
