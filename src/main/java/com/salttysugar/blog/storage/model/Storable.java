package com.salttysugar.blog.storage.model;

import reactor.core.publisher.Mono;

import java.nio.file.Path;

public interface Storable {
    String getFileName();
    Mono<Void> moveTo(Path path);
}
