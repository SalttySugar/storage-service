package com.salttysugar.blog.storage.storage.service;

import com.salttysugar.blog.storage.file.domain.model.storable.Storable;
import reactor.core.publisher.Mono;

import java.io.File;

public interface StorageService {
    Mono<File> store(Storable storable);
}
