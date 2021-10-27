package com.salttysugar.blog.storage.file.service;

import com.salttysugar.blog.storage.common.Converter;
import com.salttysugar.blog.storage.file.constant.FileType;
import com.salttysugar.blog.storage.file.model.File;
import com.salttysugar.blog.storage.file.model.FileImpl;
import com.salttysugar.blog.storage.file.persistance.MongoFile;
import com.salttysugar.blog.storage.file.persistance.MongoFileRepository;
import com.salttysugar.blog.storage.storage.ReactiveStorageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveFileServiceImpl implements ReactiveFileService {
    private final MongoFileRepository repository;
    private final ReactiveStorageService storage;
    private final Converter converter;


    @Override
    public Mono<File> getFileById(String id) {
        return null;
    }

    @Override
    public Mono<File> getFileByName(String name) {
        return null;
    }

    @Override
    @SneakyThrows
    public Mono<File> save(FilePart file) {
        return storage.store(file)
                .map(path -> FileImpl.builder()
                        .name(file.filename())
                        .type(FileType.JPEG)
                        .path(path)
                        .build())
                .map(converter.convert(MongoFile.class))
                .flatMap(repository::save)
                .map(converter.convert(File.class));
    }

    @Override
    public Mono<File> deleteById(String id) {
        return null;
    }

    @Override
    public Flux<File> list() {
        return repository.findAll()
                .map(converter.convert(File.class));
    }
}
