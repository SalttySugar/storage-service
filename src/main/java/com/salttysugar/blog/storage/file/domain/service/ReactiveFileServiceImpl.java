package com.salttysugar.blog.storage.file.domain.service;

import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.file.core.writer.Writer;
import com.salttysugar.blog.storage.file.domain.model.ApplicationFile;
import com.salttysugar.blog.storage.file.domain.model.ApplicationFileImpl;
import com.salttysugar.blog.storage.file.persistance.MongoFile;
import com.salttysugar.blog.storage.file.persistance.MongoFileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ReactiveFileServiceImpl implements ReactiveFileService {
    private final MongoFileRepository repository;
    private final ApplicationConverter converter;
    private final Collection<Writer<?, Mono<ApplicationFile>>> writers;

    @Override
    public Mono<ApplicationFile> getFileById(String id) {
        return repository.findById(id)
                .map(converter.convert(ApplicationFile.class));
    }

    @Override
    public Mono<ApplicationFile> getFileByName(String name) {
        throw new NotImplementedException();
    }

    @Override
    public Mono<ApplicationFile> save(ApplicationFile file) {
        return Mono.just(file)
                .map(converter.convert(MongoFile.class))
                .flatMap(repository::save)
                .map(converter.convert(ApplicationFile.class));
    }


    @Override
    @SuppressWarnings("unchecked")
    public Mono<ApplicationFile> store(Object source) {
        return writers.stream()
                .filter(writer -> writer.canHandle(source))
                .findFirst()
                .map(writer -> ( Writer<Object, Mono<ApplicationFile>>) writer)
                .map(writer -> writer.write(source))
                .map(writer -> Mono.just((ApplicationFile) ApplicationFileImpl.builder().build()))
                .orElseThrow(() -> new RuntimeException("could not find writer to write file"));
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
