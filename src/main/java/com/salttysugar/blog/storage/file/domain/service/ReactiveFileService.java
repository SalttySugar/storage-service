package com.salttysugar.blog.storage.file.domain.service;

import com.salttysugar.blog.storage.file.domain.model.ApplicationFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveFileService {
    Mono<ApplicationFile> getFileById(String id);

    Mono<ApplicationFile> getFileByName(String name);

    Mono<ApplicationFile> save(ApplicationFile file);

     Mono<ApplicationFile> store(Object source);

    Mono<ApplicationFile> deleteById(String id);

    Flux<ApplicationFile> list();

}
