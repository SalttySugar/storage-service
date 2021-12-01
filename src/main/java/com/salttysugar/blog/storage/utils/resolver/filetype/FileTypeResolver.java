package com.salttysugar.blog.storage.utils.resolver.filetype;

import com.salttysugar.blog.storage.model.FileType;
import com.salttysugar.blog.storage.utils.resolver.filetype.exception.UnsupportedFileTypeException;

import java.nio.file.Path;

public interface FileTypeResolver {
    FileType resolve(Path file) throws UnsupportedFileTypeException;
}
