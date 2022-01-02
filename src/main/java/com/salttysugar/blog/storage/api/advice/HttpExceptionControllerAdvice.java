package com.salttysugar.blog.storage.api.advice;

import com.salttysugar.blog.storage.exceptions.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpExceptionControllerAdvice {
    @ExceptionHandler(value = HttpException.class)
    protected ResponseEntity<Object> handleException(HttpException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}

