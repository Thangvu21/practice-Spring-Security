package com.spring.security.Spring.Security2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.Principal;

@RestControllerAdvice
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/get")
    public ResponseEntity<String> getAccount(Principal principal) {
        return ResponseEntity.ok("Hello Admin:" + principal.getName());
    }
}
