package com.salttysugar.blog.storage.service;

import com.salttysugar.blog.storage.model.Storable;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface StorageService {
    Mono<File> store(Storable storable);
    default Mono<Void> delete(Path path) throws IOException {
        Files.delete(path);
        return Mono.empty();
    }
}
