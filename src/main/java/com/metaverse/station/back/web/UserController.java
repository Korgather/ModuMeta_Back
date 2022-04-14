package com.metaverse.station.back.web;

import com.metaverse.station.back.common.ApiResponse;
import com.metaverse.station.back.domain.user.User;
import com.metaverse.station.back.service.UserService;
import com.metaverse.station.back.utils.S3Uploader;
import com.metaverse.station.back.web.dto.UserNameDto;
import com.metaverse.station.back.web.dto.UserProfileUpdateRequestDto;
import com.metaverse.station.back.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final S3Uploader s3Uploader;


    @PutMapping("/username")
    public String updateUserName(@Valid @RequestBody UserNameDto userNameDto) {

        return userService.setUserName(userNameDto.getUsername());
    }

    @PutMapping("/profile")
    public UserProfileUpdateRequestDto updateUserProfile(@Valid @RequestBody UserProfileUpdateRequestDto requestDto) {

        return userService.updateUserProfile(requestDto);
    }

    @GetMapping
    public UserResponseDto getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        return new UserResponseDto(user);
    }


    @PostMapping("/profileimage")
    @ResponseBody
    public List<String> upload(@RequestParam("data") List<MultipartFile> multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "profileImage");
    }
}
