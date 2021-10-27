package com.salttysugar.blog.storage.storage;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

public interface ReactiveStorageService {
    Mono<Path> store(FilePart part);
}
