package com.example.Jwt.with.spring.security.service.security;

import com.example.Jwt.with.spring.security.entity.User;
import com.example.Jwt.with.spring.security.exception.BaseException;
import com.example.Jwt.with.spring.security.repository.UserRepository;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;

import java.util.stream.Collectors;

public class UserDetailServiceCustom implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = getUserDetails(username);
        if (ObjectUtils.isEmpty(userDetails)) {
            throw new BaseException(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE),
                    "Invalid username");
        }

        return userDetails;
    }

    private UserDetailsCustom getUserDetails(String username) {
        User user = userRepository.findUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST),
                    "Invalid username");
        }
        return new UserDetailsCustom(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(
                        r -> new SimpleGrantedAuthority(r.getName())
                ).collect(Collectors.toUnmodifiableList())
        );
    }
}
