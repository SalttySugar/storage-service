package com.salttysugar.blog.storage.service;

import com.salttysugar.blog.storage.model.Storable;
import reactor.core.publisher.Mono;

import java.io.File;

public interface StorageService {
    Mono<File> store(Storable storable);
}
