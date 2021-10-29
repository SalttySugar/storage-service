package com.salttysugar.blog.storage.file.core.filetype.resolver;

import com.salttysugar.blog.storage.file.constant.FileType;
import org.springframework.lang.Nullable;

public interface FileTypeResolver {
    @Nullable
    FileType resolve(String name);
}
