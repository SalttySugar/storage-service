package com.salttysugar.blog.storage.services;

import com.salttysugar.blog.storage.api.dto.RequestFileDTO;
import com.salttysugar.blog.storage.model.ApplicationFile;
import com.salttysugar.blog.storage.model.Storable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StorageService {
    Mono<ApplicationFile> findById(String id);

    Mono<ApplicationFile> findOne(ApplicationFileCriteria criteria);

    Mono<ApplicationFile> store(Storable storable);

    Flux<ApplicationFile> findAll();

    Flux<ApplicationFile> findAll(ApplicationFileCriteria criteria);

    Mono<ApplicationFile> update(String id, RequestFileDTO dto);

    Mono<Void> deleteById(String id);

}
