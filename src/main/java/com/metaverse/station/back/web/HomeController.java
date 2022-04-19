package com.metaverse.station.back.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {

        return "ModuMeta Rest Api Server";
    }

    @PostMapping("/")
    public String home2() {

        return "ModuMeta Rest Api Server";
    }
}
