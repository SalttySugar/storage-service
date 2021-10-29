package com.salttysugar.blog.storage.file.core.filetype.resolver;

import com.salttysugar.blog.storage.file.constant.FileType;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class GenericFileTypeResolver implements FileTypeResolver {
    protected final FileType matchedType;
    protected final Collection<String> matchers;

    protected boolean test(String ext) {
        return matchers.stream().anyMatch(s -> s.equals(ext));
    }

    @Override
    public FileType resolve(String extension) {
        return test(extension) ? matchedType : null;
    }
}
