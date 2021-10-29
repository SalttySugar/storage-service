package com.salttysugar.blog.storage.file.core.writer.impl;

import com.salttysugar.blog.storage.common.FunctionalUtils;
import com.salttysugar.blog.storage.file.core.filetype.FileTypeResolutionManager;
import com.salttysugar.blog.storage.file.core.writer.Writer;
import com.salttysugar.blog.storage.file.model.ApplicationFile;
import com.salttysugar.blog.storage.file.model.ApplicationFileImpl;
import com.salttysugar.blog.storage.storage.StorageConfig;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

import static com.salttysugar.blog.storage.common.FunctionalUtils.toMonoTry;

@Component
@RequiredArgsConstructor
public class FileToApplicationFileWriter implements Writer<File, Mono<ApplicationFile>> {
    private final StorageConfig config;
    private final FileTypeResolutionManager fileTypeResolver;

    @Override
    public Mono<ApplicationFile> write(File source) {
        return Mono.just(config.resolve(UUID.randomUUID() + source.getName()))
                .flatMap(path -> toMonoTry(() -> Files.copy(source.toPath(), path)))
                .map(Try::toEither)
                .flatMap(FunctionalUtils::monoFromEither)
                .map(path -> ApplicationFileImpl.builder()
                        .type(fileTypeResolver.resolve(path.getFileName().toString()))
                        .name(path.getFileName().toString())
                        .path(path)
                        .build()
                );


    }
}
