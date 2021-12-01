package com.salttysugar.blog.storage.service;

import com.salttysugar.blog.storage.api.dto.RequestFileDTO;
import com.salttysugar.blog.storage.model.ApplicationFile;
import com.salttysugar.blog.storage.model.Storable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FileService {
    Mono<ApplicationFile> getById(String id);
    Mono<ApplicationFile> store(Storable storable);
    Flux<ApplicationFile> findAll();
    Mono<ApplicationFile> update(String id, Storable storable);
    Mono<ApplicationFile> update(String id, RequestFileDTO dto);
    Mono<Void> deleteById(String id);

}
