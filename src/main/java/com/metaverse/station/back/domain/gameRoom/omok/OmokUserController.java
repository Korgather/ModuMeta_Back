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
    public String updateGameUser(@PathVariable(value = "gameName") String gameName,
                                 @RequestParam("id") Long id,
                                 @RequestParam("win") int win,
                                 @RequestParam("lose") int lose,
                                 @RequestParam("nickname") String nickname) {


        OmokUserUpdateRequestDto requestDto = new OmokUserUpdateRequestDto(id,nickname,win,lose);
        return gameRoomService.updateUser(gameName,requestDto);
    }

}
