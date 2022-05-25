package com.metaverse.station.back.domain.mafiaGameRoom;

import com.metaverse.station.back.domain.posts.Posts;
import com.metaverse.station.back.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class MafiaGameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private int player_count;

    public void setPlayerCount(int playerCount){
        this.player_count = playerCount;
    }
}
