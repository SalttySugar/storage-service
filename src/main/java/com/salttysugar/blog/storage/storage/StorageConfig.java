package com.salttysugar.blog.storage.storage;

import java.nio.file.Path;

public interface StorageConfig {
    Path getFolder();
    default Path resolve(String filename) {
        return getFolder().resolve(filename);
    }
}
