package com.salttysugar.blog.storage.storage.service;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Path;

public interface ReactiveStorageService {
    Mono<File> store(Object source);
}
