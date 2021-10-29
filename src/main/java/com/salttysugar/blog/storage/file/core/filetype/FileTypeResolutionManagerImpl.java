package com.salttysugar.blog.storage.file.core.filetype;

import com.salttysugar.blog.storage.file.constant.FileType;
import com.salttysugar.blog.storage.file.core.filetype.resolver.FileTypeResolver;
import com.salttysugar.blog.storage.file.exception.UnsupportedFileTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FileTypeResolutionManagerImpl implements FileTypeResolutionManager {
    private final List<FileTypeResolver> resolvers;

    @Override
    public FileType resolve(String source) throws UnsupportedFileTypeException {
        return resolvers
                .stream()
                .map(resolver -> resolver.resolve(source))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new UnsupportedFileTypeException(source));

    }
}
