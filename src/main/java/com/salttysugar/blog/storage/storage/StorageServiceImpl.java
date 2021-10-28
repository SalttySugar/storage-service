package com.salttysugar.blog.storage.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final StorageConfig properties;

    @Override
    public Path store(MultipartFile file) throws IOException {
        Path path = Path.of(String.format("%s/%s.%s", properties.getFolder(), UUID.randomUUID(), file.getContentType()));
        Files.write(path, file.getBytes());
        return path;
    }
}
