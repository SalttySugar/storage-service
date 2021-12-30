package com.salttysugar.blog.storage.services;

import com.salttysugar.blog.storage.api.dto.UploadFileDTO;
import com.salttysugar.blog.storage.model.ApplicationFile;
import com.salttysugar.blog.storage.model.Storable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StorageService {
    Mono<ApplicationFile> findById(String id);


    Mono<ApplicationFile> store(UploadFileDTO dto);

    Flux<ApplicationFile> findAll();


    Mono<ApplicationFile> update(String id, UploadFileDTO dto);

    Mono<Void> deleteById(String id);

}
