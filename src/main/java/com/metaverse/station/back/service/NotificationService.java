package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.notification.NotificationRepository;
import com.metaverse.station.back.web.dto.NotificationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Transactional
    public void save (NotificationSaveRequestDto requestDto) {

        notificationRepository.save(requestDto.toEntity());
    }
}
