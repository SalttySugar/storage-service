package com.salttysugar.blog.storage.file.exception;

public class UnsupportedFileTypeException extends RuntimeException {
    public UnsupportedFileTypeException(String extension) {
        super(String.format("unsupported file extension: %s", extension));
    }
}
