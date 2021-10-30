package com.salttysugar.blog.storage.file.core.resolver.filetype.exception;

public class UnknownFileTypeException extends RuntimeException {
    public UnknownFileTypeException() {
        super("could not determine file extension");
    }
}
