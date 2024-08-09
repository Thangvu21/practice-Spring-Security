package com.spring.security.Spring.Security2.controller;

import com.spring.security.Spring.Security2.dto.UserDto;
import com.spring.security.Spring.Security2.response.BaseResponse;
import com.spring.security.Spring.Security2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/register-account")
    public ResponseEntity<BaseResponse> registerAccount(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.registerAccount(userDto));
    }
}
