package com.metaverse.station.back.domain.notification;

import com.metaverse.station.back.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    void deleteAllByUser(User user);
}
