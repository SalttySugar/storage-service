package com.salttysugar.blog.storage.file.core.writer.impl;

import com.salttysugar.blog.storage.file.core.resolver.filetype.FileTypeResolver;
import com.salttysugar.blog.storage.file.core.writer.Writer;
import com.salttysugar.blog.storage.file.domain.model.ApplicationFile;
import com.salttysugar.blog.storage.file.domain.model.ApplicationFileImpl;
import com.salttysugar.blog.storage.storage.StorageConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReactiveFilePartWriter implements Writer<FilePart, Mono<ApplicationFile>> {
    private final StorageConfig config;
    private final FileTypeResolver fileTypeResolver;

    @Override
    public Mono<ApplicationFile> write(FilePart source) {
        Path path = config.getFolder().resolve(UUID.randomUUID() + "-" + source.filename());
        return source.transferTo(path)
                .then(Mono.just(ApplicationFileImpl.builder()
                        .name(source.name())
                        .type(fileTypeResolver.resolve(path))
                        .path(path)
                        .build())
                );


    }

    @Override
    public boolean canHandle(Object source) {
        return source instanceof FilePart;
    }
}
