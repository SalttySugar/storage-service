package com.salttysugar.blog.storage.file.core.writer;

public interface Writer<I, O> {
    O write(I source);
}

