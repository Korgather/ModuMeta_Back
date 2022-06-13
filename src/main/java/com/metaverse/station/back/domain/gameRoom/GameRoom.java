package com.metaverse.station.back.domain.gameRoom;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "mafia_game_room")
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private int player_count;

    public void setPlayerCount(int playerCount){
        this.player_count = playerCount;
    }
}
