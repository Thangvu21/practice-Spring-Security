package com.example.Default.Security.Spring.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
@Slf4j
public class AuthController {

    @GetMapping("/hello")
    public String hello() {
        log.info("Call API /hello from index class for permit all");
        return "hello";
    }

}
