package com.example.Jwt.with.spring.security.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseException extends RuntimeException{

    private String code;

    private String message;
}
