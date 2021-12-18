package com.salttysugar.blog.storage.model.impl;

import com.salttysugar.blog.storage.model.ApplicationFile;
import lombok.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationFileImpl implements ApplicationFile {
    String id;
    String name;
    String extension;
    Path path;

    @Override
    public Path getPath() {
        return this.path;
    }

    @Override
    public byte[] getContent() throws IOException {
        return Files.readAllBytes(path);
    }

    @Override
    public void setContent(byte[] content) throws IOException {
        Files.write(path, content);
    }

}
