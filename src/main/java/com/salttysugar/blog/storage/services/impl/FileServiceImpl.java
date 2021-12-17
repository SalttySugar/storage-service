package com.salttysugar.blog.storage.services.impl;

import com.salttysugar.blog.storage.api.dto.RequestFileDTO;
import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.model.ApplicationFile;
import com.salttysugar.blog.storage.model.FileType;
import com.salttysugar.blog.storage.model.Storable;
import com.salttysugar.blog.storage.persistance.MongoFile;
import com.salttysugar.blog.storage.persistance.MongoFileRepository;
import com.salttysugar.blog.storage.services.FileService;
import com.salttysugar.blog.storage.services.StorageService;
import com.salttysugar.blog.storage.utils.resolver.filetype.FileTypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final MongoFileRepository repository;
    private final ApplicationConverter converter;
    private final FileTypeResolver resolver;
    private final StorageService service;

    @Override
    public Mono<ApplicationFile> getById(String id) {
        return repository.findAll()
                .filter(file -> file.getId().equals(id) || file.getName().equals(id))
                .next()
                .map(converter.convert(ApplicationFile.class));
    }

    @Override
    public Mono<ApplicationFile> store(Storable storable) {
        return service.store(storable)
                .map(file -> {
                    FileType fileType = resolver.resolve(file.toPath());
                    return MongoFile.builder()
                            .name(storable.getFileName())
                            .path(file.toPath().toString())
                            .size(file.getTotalSpace())
                            .type(fileType.toString())
                            .build();
                })
                .flatMap(repository::save)
                .map(converter.convert(ApplicationFile.class));
    }

    @Override
    public Flux<ApplicationFile> findAll() {
        return repository.findAll()
                .map(converter.convert(ApplicationFile.class));
    }

    @Override
    public Mono<ApplicationFile> update(String id, Storable storable) {
        return service.store(storable)
                .map(file -> {
                    FileType fileType = resolver.resolve(file.toPath());
                    return MongoFile.builder()
                            .id(id)
                            .name(storable.getFileName())
                            .path(file.toPath().toString())
                            .size(file.getTotalSpace())
                            .type(fileType.toString())
                            .build();
                })
                .flatMap(repository::save)
                .map(converter.convert(ApplicationFile.class));
    }

    @Override
    public Mono<ApplicationFile> update(String id, RequestFileDTO dto) {
        return getById(id)
                .doOnNext(applicationFile -> applicationFile.setName(dto.getName()))
                .map(converter.convert(MongoFile.class))
                .flatMap(repository::save)
                .map(converter.convert(ApplicationFile.class));
    }


    @Override
    public Mono<Void> deleteById(String id) {
        return getById(id)
                .doOnNext(file -> {
                    try {
                        service.delete(file.getPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .map(ApplicationFile::getId)
                .flatMap(repository::deleteById);
    }
}
