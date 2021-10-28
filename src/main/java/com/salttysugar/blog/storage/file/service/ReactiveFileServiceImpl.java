package com.salttysugar.blog.storage.file.service;

import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.file.model.ApplicationFile;
import com.salttysugar.blog.storage.file.persistance.MongoFile;
import com.salttysugar.blog.storage.file.persistance.MongoFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveFileServiceImpl implements ReactiveFileService {
    private final MongoFileRepository repository;
    private final ApplicationConverter converter;


    @Override
    public Mono<ApplicationFile> getFileById(String id) {
        return null;
    }

    @Override
    public Mono<ApplicationFile> getFileByName(String name) {
        return null;
    }

    @Override
    public Mono<ApplicationFile> save(ApplicationFile file) {
        return Mono.just(file)
                .map(converter.convert(MongoFile.class))
                .flatMap(repository::save)
                .map(converter.convert(ApplicationFile.class));
    }


    @Override
    public Mono<ApplicationFile> deleteById(String id) {
        return null;
    }

    @Override
    public Flux<ApplicationFile> list() {
        return repository.findAll()
                .map(converter.convert(ApplicationFile.class));
    }
}
