package com.salttysugar.blog.storage.services;

import com.salttysugar.blog.storage.api.dto.UploadFileDTO;
import com.salttysugar.blog.storage.model.ApplicationFile;
import com.salttysugar.blog.storage.model.Storable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StorageService {
    Mono<ApplicationFile> findById(String id);

    Mono<ApplicationFile> findOne(ApplicationFileCriteria criteria);

    Mono<ApplicationFile> store(UploadFileDTO dto);

    Flux<ApplicationFile> findAll();

    Flux<ApplicationFile> findAll(ApplicationFileCriteria criteria);

    Mono<ApplicationFile> update(String id, UploadFileDTO dto);

    Mono<Void> deleteById(String id);

}
