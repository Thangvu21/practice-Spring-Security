package com.example.Jwt.with.spring.security.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class GuestController {
    @GetMapping("index")
    public String index() {
        return "Hello World";
    }
}
