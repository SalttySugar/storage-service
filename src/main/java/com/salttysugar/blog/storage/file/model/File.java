package com.salttysugar.blog.storage.file.model;

import com.salttysugar.blog.storage.file.constant.FileType;

import java.io.IOException;
import java.nio.file.Path;

public interface File {
    String getId();

    void setId();

    Byte[] getContent();

    void setContent(Byte content);

    Path getPath();

    void setPath(Path path);

    long getSize();

    void setSize(long size);

    FileType getType();

    void setType(FileType type);
}
