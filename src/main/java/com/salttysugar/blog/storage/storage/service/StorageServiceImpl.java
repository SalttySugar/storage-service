package com.salttysugar.blog.storage.storage.service;

import com.salttysugar.blog.storage.file.domain.model.storable.Storable;
import com.salttysugar.blog.storage.storage.config.StorageConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final StorageConfig config;

    @Override
    public Mono<File> store(Storable storable) {
        var filename = storable.getFileName();
        var path = config.resolve((UUID.randomUUID() + "-" + filename).trim());
        return storable.moveTo(path)
                .then(Mono.just(path.toFile()));
    }
}
