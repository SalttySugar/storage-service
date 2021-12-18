package com.salttysugar.blog.storage.model;

import java.io.IOException;
import java.nio.file.Path;

public interface ApplicationFile {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    byte[] getContent() throws IOException;

    void setContent(byte[] content) throws IOException;

    Path getPath();

    void setPath(Path path);


    String getExtension();

    void setExtension(String extension);

}
