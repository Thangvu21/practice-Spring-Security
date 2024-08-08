package com.spring.security.Spring.Security2.dto;

import com.spring.security.Spring.Security2.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;

    private String password;

    private String role;

    public String checkProperties() throws IllegalAccessException {
        for (Field f : getClass().getFields()) {
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
