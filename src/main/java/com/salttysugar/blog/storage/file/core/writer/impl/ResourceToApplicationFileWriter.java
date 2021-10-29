package com.salttysugar.blog.storage.file.core.writer.impl;

import com.salttysugar.blog.storage.file.model.ApplicationFile;
import com.salttysugar.blog.storage.file.core.writer.Writer;
import com.salttysugar.blog.storage.storage.StorageConfig;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.File;

import static com.salttysugar.blog.storage.common.FunctionalUtils.toMonoTry;

@Component
@RequiredArgsConstructor
public class ResourceToApplicationFileWriter implements Writer<Resource, Mono<ApplicationFile>> {
    private final StorageConfig config;
    private final Writer<File, Mono<ApplicationFile>> fileToMonoApplicationFileWriter;

    @SneakyThrows

    @Override
    public Mono<ApplicationFile> write(Resource source) {
        return Mono.just(source)
                .flatMap(resource -> toMonoTry(resource::getFile))
                .map(t -> t.map(fileToMonoApplicationFileWriter::write))
                .map(Try::toEither)
                .flatMap(either -> either.fold(
                        Mono::error,
                        Mono::from
                ));
    }
}
