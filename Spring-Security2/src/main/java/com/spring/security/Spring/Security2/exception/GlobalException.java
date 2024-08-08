package com.spring.security.Spring.Security2.exception;

import com.spring.security.Spring.Security2.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<BaseResponse> handleBaseException(BaseException e) {
        BaseResponse response = BaseResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.ok(response);
    }
}
