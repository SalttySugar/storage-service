package com.salttysugar.blog.storage.storage.service;

import reactor.core.publisher.Mono;

import java.io.File;

public interface ReactiveStorageWriter<T> {
    Mono<File> write(T source);

    boolean canHandle(Object obj);
}
