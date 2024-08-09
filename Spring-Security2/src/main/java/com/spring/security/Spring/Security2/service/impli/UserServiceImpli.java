package com.spring.security.Spring.Security2.service.impli;

import com.spring.security.Spring.Security2.dto.UserDto;
import com.spring.security.Spring.Security2.entity.Role;
import com.spring.security.Spring.Security2.entity.User;
import com.spring.security.Spring.Security2.exception.BaseException;
import com.spring.security.Spring.Security2.repository.RoleRepository;
import com.spring.security.Spring.Security2.repository.UserRepository;
import com.spring.security.Spring.Security2.response.BaseResponse;
import com.spring.security.Spring.Security2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.net.http.HttpRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpli implements UserService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public BaseResponse registerAccount(UserDto userDto) {
        // dung de thong bao successfully
        BaseResponse response = new BaseResponse();

        // validate data for clients
        validate(userDto);

        //
        User user = insertUser(userDto);

        // ko may db sap
        try {
            userRepository.save(user);
            response.setCode(String.valueOf(HttpStatus.CREATED.value()));
            response.setMessage("register account successfully");

        } catch (Exception e) {
            response.setCode(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
            response.setMessage("service unavailable");
        }

        return response;
    }

    private User insertUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByName(userDto.getRole()));

        user.setRoles(roles);
        user.setIsEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);

        return user;
    }

    private void validate(UserDto userDto) {

        // validate null
        if (ObjectUtils.isEmpty(userDto)) {
            throw new BaseException(
                    String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "request data not found");
        }

        // validate properties
        try {
            if(!ObjectUtils.isEmpty(userDto.checkProperties())) {
                throw new BaseException(
                        String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        "request data not found");
            }
        } catch (IllegalAccessException e) {
            throw new BaseException(
                    String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()),
                    "service unavailable");
        }

        // all roles and validate role
        List<String> roles = roleRepository.findAll().stream()
                .map(Role::getName)
                .toList();
        if (!roles.contains(userDto.getRole())) {
            throw new BaseException(
                    String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "invalid role");
        }

        // validate db user
        User user = userRepository.findByUsername(userDto.getUsername());
        if (!ObjectUtils.isEmpty(user)) {
            throw new BaseException(
                    String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "user had existed");
        }

    }
}
