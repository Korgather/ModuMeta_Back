package com.metaverse.station.back.web;

import com.metaverse.station.back.common.ApiResponse;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.service.UserService;
import com.metaverse.station.back.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PutMapping("/username")
    public String updateUserName(@RequestParam String userName) {

        return userService.setUserName(userName);
    }

    @GetMapping
    public UserResponseDto getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        return new UserResponseDto(user);
    }
}
