package com.metaverse.station.back.domain.gameRoom.omok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OmokUserRepository extends JpaRepository<OmokUser, Long> {

}
