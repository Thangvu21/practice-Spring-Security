package com.example.Jwt.with.spring.security.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/user")
@RestController
public class UserController {

    @GetMapping("/index")
    public ResponseEntity<String> get(Principal principal) {
        return ResponseEntity.ok("Welcome to page user" + principal.getName());
    }
}
