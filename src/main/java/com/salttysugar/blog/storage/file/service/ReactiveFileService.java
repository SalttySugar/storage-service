package com.salttysugar.blog.storage.file.service;

import com.salttysugar.blog.storage.file.model.File;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveFileService {
    Mono<File> getFileById(String id);
    Mono<File> getFileByName(String name);
    Mono<File> save(FilePart file);
    Mono<File> deleteById(String id);
    Flux<File> list();

}
