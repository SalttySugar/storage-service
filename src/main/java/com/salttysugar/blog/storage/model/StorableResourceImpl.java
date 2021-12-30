package com.salttysugar.blog.storage.model;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
public class StorableResourceImpl implements Storable {
    private final Resource resource;

    @Override
    public String getFileName() {
       return resource.getFilename();
    }

    @Override
    public Mono<Void> moveTo(Path targetPath) {
        Try.ofCallable(resource::getFile)
                .map(File::getPath)
                .map(Path::of)
                .flatMap(filePath -> Try.ofCallable(() -> Files.copy(filePath, targetPath)))
                .get();

        return Mono.empty();
    }
}
