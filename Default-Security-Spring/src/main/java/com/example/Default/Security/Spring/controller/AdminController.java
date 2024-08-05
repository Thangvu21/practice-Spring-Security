package com.example.Default.Security.Spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @GetMapping("/manage")
    public String manage() {
        log.info("ADMIN LOGIN");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object o1 = authentication.getName();
        Object o2 = authentication.getAuthorities();
        Object o3 = authentication.getDetails();
        Object o4 = authentication.getCredentials();
        Object o5 = authentication.getPrincipal();

        return "Welcome to page admin :" + authentication.getName();
    }


}
