package com.example.Jwt.with.spring.security.exception;


import com.example.Jwt.with.spring.security.response.BaseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BaseException.class})
    ResponseEntity<BaseResponseDto> handleBaseException(BaseException e) {
        BaseResponseDto responseDto = BaseResponseDto.builder()
                .code(e.getMessage())
                .message(e.getMessage())
                .build();
        return ResponseEntity.ok(responseDto);
    }
}
