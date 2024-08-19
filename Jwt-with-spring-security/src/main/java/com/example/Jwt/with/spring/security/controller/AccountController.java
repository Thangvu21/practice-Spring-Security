package com.example.Jwt.with.spring.security.controller;

import com.example.Jwt.with.spring.security.dto.UserDto;
import com.example.Jwt.with.spring.security.response.BaseResponseDto;
import com.example.Jwt.with.spring.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.registerAccount(userDto));
    }

}
