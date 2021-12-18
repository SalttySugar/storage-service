package com.salttysugar.blog.storage.services.impl;

import com.salttysugar.blog.storage.api.dto.RequestFileDTO;
import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.configs.StorageConfig;
import com.salttysugar.blog.storage.exceptions.FileNotFoundException;
import com.salttysugar.blog.storage.model.ApplicationFile;
import com.salttysugar.blog.storage.model.Storable;
import com.salttysugar.blog.storage.persistance.MongoFile;
import com.salttysugar.blog.storage.persistance.MongoFileRepository;
import com.salttysugar.blog.storage.services.ApplicationFileCriteria;
import com.salttysugar.blog.storage.services.StorageService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StorageServiceImpl implements StorageService {
    private final MongoFileRepository repository;
    private final ApplicationConverter converter;
    private final StorageConfig config;

    @Override
    public Mono<ApplicationFile> findById(String id) {
        return repository.findById(id)
                .map(converter.convert(ApplicationFile.class))
                .switchIfEmpty(Mono.error(new FileNotFoundException(id)));
    }

    @Override
    public Mono<ApplicationFile> findOne(ApplicationFileCriteria criteria) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Mono<ApplicationFile> store(Storable storable) {
        String name = storable.getFileName();
        String extension = FilenameUtils.getExtension(name);
        Path path = config.getFolder().resolve(UUID.randomUUID().toString());
        return storable.moveTo(path)
                .then(Try.ofCallable(() -> Mono.just(MongoFile.builder()
                                .name(name)
                                .extension(extension)
                                .path(path.toString())
                                .build())
                        ).get()
                )
                .flatMap(repository::save)
                .map(converter.convert(ApplicationFile.class));
    }

    @Override
    public Flux<ApplicationFile> findAll() {
        return repository.findAll()
                .map(converter.convert(ApplicationFile.class));
    }

    @Override
    public Flux<ApplicationFile> findAll(ApplicationFileCriteria criteria) {
        throw new RuntimeException("not implemented");
    }


    @Override
    public Mono<ApplicationFile> update(String id, RequestFileDTO dto) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new FileNotFoundException(id)))
                .flatMap(repository::delete);
    }
}
