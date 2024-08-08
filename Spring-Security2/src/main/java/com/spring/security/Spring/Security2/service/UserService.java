package com.spring.security.Spring.Security2.service;

import com.spring.security.Spring.Security2.dto.UserDto;
import com.spring.security.Spring.Security2.entity.User;
import com.spring.security.Spring.Security2.response.BaseResponse;


public interface UserService {

    BaseResponse registerAccount(UserDto userDto);
}
