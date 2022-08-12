package com.metaverse.station.back.domain.gameRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {

    Optional<GameRoom> findByUrl(String url);
    List<GameRoom> findAllByCategory(String category);

}

