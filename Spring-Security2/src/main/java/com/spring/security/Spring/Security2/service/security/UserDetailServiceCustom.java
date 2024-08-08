package com.spring.security.Spring.Security2.service.security;

import com.spring.security.Spring.Security2.entity.Role;
import com.spring.security.Spring.Security2.entity.User;
import com.spring.security.Spring.Security2.exception.BaseException;
import com.spring.security.Spring.Security2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;

import java.util.stream.Collectors;

public class UserDetailServiceCustom implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // for login ??
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetailsCustom userDetails = getUserDetailsCustom(username);

        if (ObjectUtils.isEmpty(userDetails)) {
            throw new UsernameNotFoundException("User not found");
        }

        return userDetails;
    }

    private UserDetailsCustom getUserDetailsCustom(String username) {
        User user = userRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(user)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "User not found");
        }

        return new UserDetailsCustom(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toList()),
                user.getIsEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked()
        );
    }
}
