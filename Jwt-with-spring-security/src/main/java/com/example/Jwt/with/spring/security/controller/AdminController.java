package com.example.Jwt.with.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @GetMapping("/get")
    public ResponseEntity<String> get(Principal principal) {
        return ResponseEntity.ok("Welcome to page admin" + principal.getName());
    }
}
