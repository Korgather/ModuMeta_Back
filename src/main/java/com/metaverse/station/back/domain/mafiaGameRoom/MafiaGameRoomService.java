package com.metaverse.station.back.domain.mafiaGameRoom;

import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.web.dto.UpdateZepPlayerCountRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MafiaGameRoomService {

    private final MafiaGameRoomRepository mafiaGameRoomRepository;

    @Transactional
    public void updatePlayerCountByUrl(UpdateZepPlayerCountRequestDto requestDto){
        int playerCount = requestDto.getPlayerCount();
        String link = "https://zep.us/play/" + requestDto.getHashId();
        MafiaGameRoom mafiaGameRoom = mafiaGameRoomRepository.findByUrl(link).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        mafiaGameRoom.setPlayerCount(playerCount);
    }

    @Transactional
    public int getPlayerCountByUrl(String hashId){
        String link = "https://zep.us/play/" + hashId;
        MafiaGameRoom mafiaGameRoom = mafiaGameRoomRepository.findByUrl(link).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return mafiaGameRoom.getPlayer_count();
    }
}
