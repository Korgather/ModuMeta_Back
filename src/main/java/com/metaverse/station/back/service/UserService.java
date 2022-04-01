package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Transactional
    public String setUserName(String userName) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = getUser(principal.getUsername());

        user.setUserName(userName);
        user.setUserNameModifiedYn("Y");

        return userName;
    }
}
