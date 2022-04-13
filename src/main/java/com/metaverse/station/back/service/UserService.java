package com.metaverse.station.back.service;

import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.domain.user.UserRepository;
import com.metaverse.station.back.web.dto.UserProfileUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
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

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Transactional
    public String setUserName(String userName) throws AccessDeniedException {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = getUser(principal.getUsername());

        user.setUserName(userName);
        user.setUserNameModifiedYn("Y");

        return userName;
    }

    @Transactional
    public UserProfileUpdateRequestDto updateUserProfile(UserProfileUpdateRequestDto requestDto) {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = getUser(principal.getUsername());

        user.update(requestDto.getUsername(),requestDto.getBio(),requestDto.getProfileImageUrl());

        return requestDto;

    }
}
