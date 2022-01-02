package com.salttysugar.blog.storage.exceptions;

import org.springframework.http.HttpStatus;

public class FileNotFoundException extends HttpException {
    public FileNotFoundException(String id) {
        super(String.format("could not find file with id: %s" , id), HttpStatus.NOT_FOUND);
    }
}
