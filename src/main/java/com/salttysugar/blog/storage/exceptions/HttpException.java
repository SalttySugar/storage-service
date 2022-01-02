package com.salttysugar.blog.storage.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public HttpException(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }
}
