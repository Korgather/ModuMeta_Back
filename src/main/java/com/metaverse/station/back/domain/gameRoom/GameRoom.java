package com.metaverse.station.back.domain.gameRoom;

import lombok.Builder;
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

    @Builder
    public GameRoom(Long id,String url, int player_count) {
        this.id = id;
        this.url = url;
        this.player_count = player_count;
    }
}
