package com.metaverse.station.back.domain.gameRoom;

import com.metaverse.station.back.web.dto.UpdateZepPlayerCountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GameRoomController {

    private final GameRoomService gameRoomService;

    @PostMapping("/api/v1/posts/zep/playercount")
    public void updateZepPlayerCount(@RequestBody UpdateZepPlayerCountRequestDto requestDto) {

        gameRoomService.updatePlayerCountByUrl(requestDto);
    }

    @GetMapping("/api/v1/posts/zep/playercount")
    public void updateZepPlayerCount2(@RequestParam String hashId, @RequestParam int playerCount) {
        UpdateZepPlayerCountRequestDto requestDto = UpdateZepPlayerCountRequestDto.builder().playerCount(playerCount).hashId(hashId).build();
        gameRoomService.updatePlayerCountByUrl(requestDto);
    }

    @GetMapping("/api/v1/posts/zep/mafiagame/playercount")
    public int getMafiaGamePlayerCount(@RequestParam String hashId) {

        return gameRoomService.getPlayerCountByUrl(hashId);
    }

    @GetMapping("/api/v1/posts/zep/game/playercount")
    public List<GameRoom> getGamePlayerCount(@RequestParam String category) {

        return gameRoomService.getPlayerCountByCategory(category);
    }
}
