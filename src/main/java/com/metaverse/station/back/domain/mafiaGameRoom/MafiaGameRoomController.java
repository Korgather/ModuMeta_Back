package com.metaverse.station.back.domain.mafiaGameRoom;

import com.metaverse.station.back.web.dto.UpdateZepPlayerCountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MafiaGameRoomController {

    private final MafiaGameRoomService mafiaGameRoomService;

    @PostMapping("/api/v1/posts/zep/playercount")
    public void updateZepPlayerCount(@RequestBody UpdateZepPlayerCountRequestDto requestDto) {

        mafiaGameRoomService.updatePlayerCountByUrl(requestDto);
    }

    @GetMapping("/api/v1/posts/zep/playercount")
    public void updateZepPlayerCount2(@RequestParam String hashId, @RequestParam int playerCount) {
        UpdateZepPlayerCountRequestDto requestDto = UpdateZepPlayerCountRequestDto.builder().playerCount(playerCount).hashId(hashId).build();
        mafiaGameRoomService.updatePlayerCountByUrl(requestDto);
    }
}
