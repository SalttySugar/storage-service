package com.salttysugar.blog.storage.file.core.resolver.filetype;

import com.salttysugar.blog.storage.file.constant.FileType;
import com.salttysugar.blog.storage.file.core.resolver.filetype.exception.UnsupportedFileTypeException;

import java.nio.file.Path;

public interface FileTypeResolver {
    FileType resolve(Path file) throws UnsupportedFileTypeException;
}
