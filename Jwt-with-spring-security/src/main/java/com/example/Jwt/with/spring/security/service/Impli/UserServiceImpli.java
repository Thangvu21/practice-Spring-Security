package com.example.Jwt.with.spring.security.service.Impli;

import com.example.Jwt.with.spring.security.dto.UserDto;
import com.example.Jwt.with.spring.security.entity.Role;
import com.example.Jwt.with.spring.security.entity.User;
import com.example.Jwt.with.spring.security.exception.BaseException;
import com.example.Jwt.with.spring.security.repository.RoleRepository;
import com.example.Jwt.with.spring.security.repository.UserRepository;
import com.example.Jwt.with.spring.security.response.BaseResponseDto;
import com.example.Jwt.with.spring.security.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpli implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public BaseResponseDto registerAccount(UserDto userDto) {
        BaseResponseDto responseDto = new BaseResponseDto();

        // validated
        validateAccount(userDto);

        // convert to User
        User user = insertUser(userDto);

        // add DB
        try {
            userRepository.save(user);
            responseDto.setCode(String.valueOf(HttpStatus.OK));
            responseDto.setMessage("successfully register");
        } catch (Exception e) {
            responseDto.setCode(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE));
            responseDto.setMessage("service unavailable");
        }


        return responseDto;
    }

    private void validateAccount(UserDto userDto) {
        // validate null data
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST), "Data must not empty");
        }

        // validate duplicate username
        User user = userRepository.findUserByUsername(userDto.getUsername());
        if (ObjectUtils.isEmpty(user)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST), "User name has existed");
        }



        // validate role
        List<String> roles = roleRepository.findAll()
                .stream().map(Role::getName)
                .collect(Collectors.toUnmodifiableList());
        if (!roles.contains(userDto.getRole())) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST), "Invalid role");
        }


    }

    private User insertUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findRoleByName(userDto.getRole());
        user.setRoles(roles);
        roles.add(role);
        return user;
    }
}
