package com.metaverse.station.back.domain.gameRoom;

import com.metaverse.station.back.domain.gameRoom.omok.OmokUser;
import com.metaverse.station.back.domain.gameRoom.omok.OmokUserRepository;
import com.metaverse.station.back.domain.gameRoom.omok.OmokUserUpdateRequestDto;
import com.metaverse.station.back.domain.images.Images;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.service.UserService;
import com.metaverse.station.back.web.dto.UpdateZepPlayerCountRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameRoomService {

    private final GameRoomRepository gameRoomRepository;
    private final OmokUserRepository omokUserRepository;
    private final UserService userService;

    @Transactional
    public void updatePlayerCountByUrl(UpdateZepPlayerCountRequestDto requestDto){
        int playerCount = requestDto.getPlayerCount();
        String link = "https://zep.us/play/" + requestDto.getHashId();

        if(gameRoomRepository.findByUrl(link).isPresent()){
            GameRoom gameRoom = gameRoomRepository.findByUrl(link).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. url=" + link));
            gameRoom.setPlayerCount(playerCount);
        }else{
            GameRoom gameRoom = GameRoom.builder().url(link).player_count(playerCount).build();
            gameRoomRepository.save(gameRoom);
        }
        
    }

    @Transactional
    public int getPlayerCountByUrl(String hashId){
        String link = "https://zep.us/play/" + hashId;
        GameRoom gameRoom = gameRoomRepository.findByUrl(link).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return gameRoom.getPlayer_count();
    }

    @Transactional
    public String registerUser(String gameName){
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());

        String strJson = "";

        if(Category.of(gameName) == Category.NOT_REGISTERED){
            return "등록되지 않은 게임입니다.";
        }
        boolean registered = false;

        switch (gameName){
            case "mafia":

                break;
            case "omok":
                OmokUser omokUser;
                if(omokUserRepository.findById(user.getUserSeq()).isPresent())
                {
                    omokUser = omokUserRepository.getById(user.getUserSeq());
                    registered = true;

                }
                else{
                    omokUser = OmokUser.builder().nickname(user.getUsername()).win(0).lose(0).user(user).build();
                    omokUserRepository.save(omokUser);
                }

                strJson = "{" +
                        "\"id\": "+"\""+omokUser.getUserId()+"\""+"," +
                        "\"nickname\": "+"\""+omokUser.getNickname()+"\""+"," +
                        "\"win\": "+"\""+omokUser.getWin()+"\""+"," +
                        "\"lose\": "+"\""+omokUser.getLose()+"\""+"" +
                        "}";

                System.out.println(strJson);
                break;
        }

        return strJson;
    }

    @Transactional
    public String updateUser(String gameName, OmokUserUpdateRequestDto requestDto){
//        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userService.getUser(principal.getUsername());

        if(Category.of(gameName) == Category.NOT_REGISTERED){
            return "등록되지 않은 게임입니다.";
        }
        switch (gameName){
            case "mafia":

                break;
            case "omok":
                OmokUser omokUser;
                if(omokUserRepository.findById(requestDto.getId()).isPresent())
                {
                    omokUser = omokUserRepository.getById(requestDto.getId());
                    omokUser.update(requestDto.getNickname(),requestDto.getWin(),requestDto.getLose());
                }
                else{
                    return "존재하지 않는 유저입니다.";
                }

                break;
        }

        return "업데이트 성공";
    }
}
