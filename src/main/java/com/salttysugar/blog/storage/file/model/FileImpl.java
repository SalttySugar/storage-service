package com.salttysugar.blog.storage.file.model;

import com.salttysugar.blog.storage.file.constant.FileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileImpl implements File {
    String id;
    String name;
    FileType type;
    Path path;


    @Override
    public byte[] getContent() throws IOException {
        return Files.readAllBytes(path);
    }

    @Override
    public void setContent(byte[] content) throws IOException {
        Files.write(path, content);
    }

    @Override
    public long getSize() throws IOException {
        return Files.size(path);
    }
}
