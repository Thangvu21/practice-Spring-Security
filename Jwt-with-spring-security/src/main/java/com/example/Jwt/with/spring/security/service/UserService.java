package com.example.Jwt.with.spring.security.service;

import com.example.Jwt.with.spring.security.dto.UserDto;
import com.example.Jwt.with.spring.security.response.BaseResponseDto;

public interface UserService {

    BaseResponseDto registerAccount(UserDto userDto);
}
