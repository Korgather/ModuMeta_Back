package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.notification.Notification;
import com.metaverse.station.back.domain.notification.NotificationRepository;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.web.dto.NotificationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;

    @Transactional
    public void save (NotificationSaveRequestDto requestDto) {

        notificationRepository.save(requestDto.toEntity());
    }

    @Transactional
    public void delete (Long id) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        Notification notification = notificationRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("이미 삭제된 알림이거나, 없는 알림입니다."));

        if(user == notification.getUser()){
            notificationRepository.delete(notification);

        }else{
            System.out.println("Nope");
        }
    }

    @Transactional
    public void deleteAll () {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        notificationRepository.deleteAllByUser(user);
    }
}
