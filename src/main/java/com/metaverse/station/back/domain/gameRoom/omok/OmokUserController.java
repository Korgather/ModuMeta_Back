package com.metaverse.station.back.domain.gameRoom.omok;

import com.metaverse.station.back.domain.gameRoom.GameRoomService;
import com.metaverse.station.back.web.dto.UpdateZepPlayerCountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OmokUserController {

    private final GameRoomService gameRoomService;

    @PostMapping("/api/v1/game/{gameName}/user")
    public String registerGameUser(@PathVariable(value = "gameName") String gameName) {

        return gameRoomService.registerUser(gameName);
    }

}
