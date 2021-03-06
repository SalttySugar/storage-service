package com.salttysugar.blog.storage.services.impl;

import com.salttysugar.blog.storage.api.dto.UploadFileDTO;
import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.configs.StorageConfig;
import com.salttysugar.blog.storage.exceptions.FileNotFoundException;
import com.salttysugar.blog.storage.model.ApplicationFile;
import com.salttysugar.blog.storage.model.Storable;
import com.salttysugar.blog.storage.persistance.MongoFileRepository;
import com.salttysugar.blog.storage.services.ApplicationFileCriteria;
import com.salttysugar.blog.storage.services.StorageService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StorageServiceImpl implements StorageService {
    private final MongoFileRepository repository;
    private final ApplicationConverter converter;
    private final StorageConfig config;

    private final ReactiveMongoTemplate template;

    @Override
    public Mono<ApplicationFile> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new FileNotFoundException(id)));
    }


    @Override
    public Mono<ApplicationFile> store(UploadFileDTO dto) {
        Storable storable = dto.getFile();
        String name = Optional.ofNullable(dto.getName())
                .orElse(storable.getFileName());


        String extension = FilenameUtils.getExtension(storable.getFileName());
        Path path = config.getDirectory().resolve(UUID.randomUUID().toString());
        return storable.moveTo(path)
                .then(Try.ofCallable(() -> Mono.just(ApplicationFile.builder()
                                .name(name)
                                .extension(extension)
                                .path(path.toString())
                                .ownerId(dto.getOwner_id())
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
    public Mono<ApplicationFile> update(String id, UploadFileDTO dto) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Mono<Long> count() {
        return repository.count();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new FileNotFoundException(id)))
                .flatMap(repository::delete);
    }

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }
}
