package com.salttysugar.blog.storage.file.core.filetype;

import com.salttysugar.blog.storage.file.constant.FileType;
import com.salttysugar.blog.storage.file.core.filetype.resolver.FileTypeResolver;
import com.salttysugar.blog.storage.file.core.filetype.exception.UnsupportedFileTypeException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FileTypeResolutionManagerImpl implements FileTypeResolutionManager {
    private final List<FileTypeResolver> resolvers;

    @Override
    public FileType resolve(String source) throws UnsupportedFileTypeException {
        String extension = FileNameUtils.getExtension(source);
        return resolvers
                .stream()
                .map(resolver -> resolver.resolve(extension))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new UnsupportedFileTypeException(extension));

    }
}
