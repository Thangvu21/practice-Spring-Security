package com.example.Default.Security.Spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/manage")
    public String manage() {
        log.info("USer LOGIN");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object o1 = authentication.getName();
        Object o2 = authentication.getAuthorities();
        Object o3 = authentication.getDetails();
        Object o4 = authentication.getCredentials();
        Object o5 = authentication.getPrincipal();

        return "Hello user :" + authentication.getName();
    }
}
