package com.salttysugar.blog.storage.model.impl;

import com.salttysugar.blog.storage.model.Storable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@RequiredArgsConstructor
public class StorableFilePart implements Storable {
    private final FilePart part;

    @Override
    public String getFileName() {
        return part.filename();
    }

    @Override
    public Mono<Void> moveTo(Path path) {
        return part.transferTo(path);
    }
}
