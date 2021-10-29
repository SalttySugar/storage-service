package com.salttysugar.blog.storage.file.core.filetype.resolver;

import com.salttysugar.blog.storage.file.constant.FileType;
import com.salttysugar.blog.storage.file.exception.UnknownFileTypeException;
import com.salttysugar.blog.storage.file.exception.UnsupportedFileTypeException;
import org.springframework.lang.Nullable;

public interface FileTypeResolver {
    @Nullable
    FileType resolve(String name);
}
