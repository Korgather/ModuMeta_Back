package com.metaverse.station.back.domain.gameRoom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {

    Optional<GameRoom> findByUrl(String url);

}

