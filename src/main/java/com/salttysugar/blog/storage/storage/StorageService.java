package com.salttysugar.blog.storage.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {
    Path store(MultipartFile file) throws IOException;
    default Path store(String name, byte[] data) {
        return Path.of("hello");
    }
}
