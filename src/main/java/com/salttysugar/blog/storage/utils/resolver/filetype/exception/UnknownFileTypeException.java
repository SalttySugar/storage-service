package com.salttysugar.blog.storage.utils.resolver.filetype.exception;

public class UnknownFileTypeException extends RuntimeException {
    public UnknownFileTypeException() {
        super("could not determine file extension");
    }
}
