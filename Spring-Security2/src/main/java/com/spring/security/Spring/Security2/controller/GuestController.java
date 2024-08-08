package com.spring.security.Spring.Security2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @GetMapping("/hello")
    public ResponseEntity<String> getAccount() {
        return ResponseEntity.ok("Welcome guest ");
    }
}
