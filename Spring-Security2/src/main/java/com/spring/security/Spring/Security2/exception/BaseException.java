package com.spring.security.Spring.Security2.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {

    private String code;

    private String message;
}
