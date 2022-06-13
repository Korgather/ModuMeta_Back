package com.metaverse.station.back.domain.gameRoom;

import com.metaverse.station.back.web.dto.UpdateZepPlayerCountRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameRoomService {

    private final GameRoomRepository gameRoomRepository;

    @Transactional
    public void updatePlayerCountByUrl(UpdateZepPlayerCountRequestDto requestDto){
        int playerCount = requestDto.getPlayerCount();
        String link = "https://zep.us/play/" + requestDto.getHashId();
        GameRoom gameRoom = gameRoomRepository.findByUrl(link).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        gameRoom.setPlayerCount(playerCount);
    }

    @Transactional
    public int getPlayerCountByUrl(String hashId){
        String link = "https://zep.us/play/" + hashId;
        GameRoom gameRoom = gameRoomRepository.findByUrl(link).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return gameRoom.getPlayer_count();
    }
}
