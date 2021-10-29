package com.salttysugar.blog.storage.file.core.filetype.exception;

public class UnsupportedFileTypeException extends RuntimeException {
    public UnsupportedFileTypeException(String extension) {
        super(String.format("unsupported file extension: %s", extension));
    }
}
