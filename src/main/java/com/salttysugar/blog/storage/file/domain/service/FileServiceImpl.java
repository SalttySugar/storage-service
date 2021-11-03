package com.salttysugar.blog.storage.file.domain.service;

import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.file.utils.resolver.filetype.FileTypeResolver;
import com.salttysugar.blog.storage.file.domain.constant.FileType;
import com.salttysugar.blog.storage.file.domain.model.file.ApplicationFile;
import com.salttysugar.blog.storage.file.domain.model.storable.Storable;
import com.salttysugar.blog.storage.storage.service.StorageService;
import com.salttysugar.blog.storage.file.persistance.MongoFile;
import com.salttysugar.blog.storage.file.persistance.MongoFileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final MongoFileRepository repository;
    private final ApplicationConverter converter;
    private final StorageService storage;
    private final FileTypeResolver fileTypeResolver;

    @Override
    public Mono<ApplicationFile> getFileById(String id) {
        return repository.findById(id)
                .map(converter.convert(ApplicationFile.class));
    }

    @Override
    public Mono<ApplicationFile> getFileByName(String name) {
        throw new NotImplementedException();
    }

    public Mono<ApplicationFile> store(Storable storable) {
        String filename = storable.getFileName();
        FileType fileType =  fileTypeResolver.resolve(Path.of(filename));

        return Mono.just(storable)
                .flatMap(storage::store)
                .map(file -> MongoFile.builder()
                        .size(file.getTotalSpace())
                        .path(file.getPath())
                        .name(filename)
                        .type(fileType.toString())
                        .build())
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
