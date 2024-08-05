package com.example.Default.Security.Spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping("/hello")
    public String helloWorld(Authentication authentication) {
        log.info("Call API hello");
        return "Hello " +authentication.getName(); // Authentication authentication +authentication.getName();
    }
}
