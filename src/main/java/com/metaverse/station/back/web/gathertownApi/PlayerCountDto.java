package com.metaverse.station.back.web.gathertownApi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PlayerCountDto {

    private Data data;

    @Getter
    @Setter
    public static class Data{
        public GameData gameData;
        @Getter
        @Setter
        public static class GameData
        {
            public int playerCount;
        }
    }




}
