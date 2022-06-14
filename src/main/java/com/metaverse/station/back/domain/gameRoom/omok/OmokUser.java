package com.metaverse.station.back.domain.gameRoom.omok;

import com.metaverse.station.back.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "omok_user")
//@IdClass(OmokUserId.class)
public class OmokUser implements Serializable{

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @Id
    @Column(name = "user_id")
    private Long userId;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String nickname;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int win;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int lose;

    @Builder
    public OmokUser(
            User user,
            String nickname,
            int win,
            int lose
    ) {
        this.user = user;
        this.nickname = nickname;
        this.win = win;
        this.lose = lose;
    }

    public void update(String nickname, int win, int lose) {
        if(nickname != null){
            this.nickname = nickname;
        }
        if (win != 0) {
            this.win = win;
        }
        if(lose != 0){
            this.lose = lose;
        }
    }

}
