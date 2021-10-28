package com.salttysugar.blog.storage.file.service;

import com.salttysugar.blog.storage.file.model.ApplicationFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveFileService {
    Mono<ApplicationFile> getFileById(String id);

    Mono<ApplicationFile> getFileByName(String name);

    Mono<ApplicationFile> save(ApplicationFile file);

    Mono<ApplicationFile> deleteById(String id);

    Flux<ApplicationFile> list();

}
