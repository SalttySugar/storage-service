package com.salttysugar.blog.storage.file.exception;

public class UnknownFileTypeException extends RuntimeException {
    public UnknownFileTypeException() {
        super("could not determine file extension");
    }
}
