package com.salttysugar.blog.storage.file.domain.service;

import com.salttysugar.blog.storage.file.domain.model.file.ApplicationFile;
import com.salttysugar.blog.storage.file.domain.model.storable.Storable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FileService {
    Mono<ApplicationFile> getFileById(String id);

    Mono<ApplicationFile> getFileByName(String name);

    Mono<ApplicationFile> store(Storable file);

    Mono<ApplicationFile> deleteById(String id);

    Flux<ApplicationFile> list();

}
