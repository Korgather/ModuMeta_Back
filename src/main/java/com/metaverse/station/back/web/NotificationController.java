package com.metaverse.station.back.web;

import com.metaverse.station.back.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;



    @DeleteMapping("/api/v1/notification/{id}")
    public void deleteNotification(@PathVariable Long id){

        notificationService.delete(id);
    }

    @DeleteMapping("/api/v1/notification/all")
    public void deleteAll(){

        notificationService.deleteAll();
    }
}
