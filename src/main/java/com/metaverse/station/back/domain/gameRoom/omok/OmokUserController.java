package com.metaverse.station.back.domain.gameRoom.omok;

import com.metaverse.station.back.domain.gameRoom.GameRoomService;
import com.metaverse.station.back.web.dto.UpdateZepPlayerCountRequestDto;
import com.metaverse.station.back.web.dto.UserProfileUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class OmokUserController {

    private final GameRoomService gameRoomService;

    @PostMapping("/api/v1/game/{gameName}/user")
    public String registerGameUser(@PathVariable(value = "gameName") String gameName) {

        return gameRoomService.registerUser(gameName);
    }

    @PostMapping("/api/v1/game/{gameName}/user/info")
    public String updateGameUser(@PathVariable(value = "gameName") String gameName,@Valid @RequestBody OmokUserUpdateRequestDto requestDto) {

        return gameRoomService.updateUser(gameName,requestDto);
    }

}
