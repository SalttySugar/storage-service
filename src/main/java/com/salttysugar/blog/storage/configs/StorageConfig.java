package com.salttysugar.blog.storage.configs;

import java.nio.file.Path;

public interface StorageConfig {
    Path getDirectory();

    default Path resolve(String filename) {
        return getDirectory().resolve(filename);
    }
}
