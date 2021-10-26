package com.salttysugar.blog.storage.file.service;

import com.salttysugar.blog.storage.common.Converter;
import com.salttysugar.blog.storage.file.model.File;
import com.salttysugar.blog.storage.file.persistance.MongoFile;
import com.salttysugar.blog.storage.file.persistance.MongoFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveMongoFileServiceImpl implements ReactiveFileService {
    private final MongoFileRepository r;
    private final Converter converter;

    @Override
    public Flux<File> list() {
        return r.findAll()
                .map(converter.convert(File.class));
    }

    @Override
    public Mono<File> findById(String id) {
        return r.findById(id)
                .map(converter.convert(File.class));
    }

    @Override
    public Mono<File> store(File entity) {
        return Mono.just(entity)
                .map(converter.convert(MongoFile.class))
                .flatMap(r::save)
                .map(converter.convert(File.class));
    }

    @Override
    public Mono<File> update(File entity) {
        return Mono.just(entity)
                .map(converter.convert(MongoFile.class))
                .flatMap(r::save)
                .map(converter.convert(File.class));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return r.deleteById(id);
    }
}
