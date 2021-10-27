package com.salttysugar.blog.storage.file.model;

import com.salttysugar.blog.storage.file.constant.FileType;

import java.io.IOException;
import java.nio.file.Path;

public interface File {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    byte[] getContent() throws IOException;

    void setContent(byte[] content) throws IOException;

    Path getPath();

    void setPath(Path path);

    long getSize() throws IOException;


    FileType getType();

    void setType(FileType type);
}
