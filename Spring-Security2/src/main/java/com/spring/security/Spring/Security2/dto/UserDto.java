package com.spring.security.Spring.Security2.dto;

import com.spring.security.Spring.Security2.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "username must not be empty")
    private String username;

    @NotBlank(message = "password must not be empty")
    private String password;

    @NotBlank(message = "role must not be empty")
    private String role;

    public String checkProperties() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if (f.get(this) == null) {
                String[] arr = f.getName().split("\\.");
                String temp = arr[arr.length - 1];

                if (temp.equalsIgnoreCase("username")
                        || temp.equalsIgnoreCase("password")
                            || temp.equalsIgnoreCase("role")) {
                    return temp;
                }
            }
        }
        return null;
    }
}
