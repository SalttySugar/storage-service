package com.salttysugar.blog.storage.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReactiveStorageServiceImpl implements ReactiveStorageService {
    private final StorageConfig properties;

    @Override
    public Mono<Path> store(FilePart part) {
        return part.content()
                .map(dataBuffer -> {
                    Path path = Path.of(String.format("%s/%s", properties.getFolder(), UUID.randomUUID()));
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    try {
                        Files.write(path, bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return path;
                }).next();

    }
}
