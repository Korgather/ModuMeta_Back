package com.metaverse.station.back.domain.mafiaGameRoom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MafiaGameRoomRepository extends JpaRepository<MafiaGameRoom, Long> {

    Optional<MafiaGameRoom> findByUrl(String url);

}

