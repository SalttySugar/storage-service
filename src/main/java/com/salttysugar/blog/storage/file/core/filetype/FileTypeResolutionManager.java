package com.salttysugar.blog.storage.file.core.filetype;

import com.salttysugar.blog.storage.file.constant.FileType;
import com.salttysugar.blog.storage.file.core.filetype.exception.UnsupportedFileTypeException;

public interface FileTypeResolutionManager {
    FileType resolve(String source) throws UnsupportedFileTypeException;
}
