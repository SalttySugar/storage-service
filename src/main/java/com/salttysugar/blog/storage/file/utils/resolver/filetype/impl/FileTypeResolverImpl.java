package com.salttysugar.blog.storage.file.utils.resolver.filetype.impl;

import com.salttysugar.blog.storage.file.domain.constant.FileType;
import com.salttysugar.blog.storage.file.utils.resolver.filetype.FileTypeResolver;
import com.salttysugar.blog.storage.file.utils.resolver.filetype.exception.UnsupportedFileTypeException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public final class FileTypeResolverImpl implements FileTypeResolver {
    public interface Matcher {
        FileType match(String ext);
    }

    private final Collection<Matcher> matchers;

    @Override
    public FileType resolve(Path file) throws UnsupportedFileTypeException {
        String extension = FileNameUtils.getExtension(file.getFileName().toString());
        return matchers.stream()
                .map(matcher -> matcher.match(extension))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new UnsupportedFileTypeException(extension));
    }


    @Component
    public static final class FileTypeMatchers {
        @Bean
        public Matcher textFileTypeMatcher() {
            return ext -> List.of(
                    "txt",
                    "text/plain"
            ).contains(ext) ? FileType.TXT : null;
        }

        @Bean
        public Matcher pngFileTypeMatcher() {
            return ext -> List.of(
                    "png",
                    "image/png"
            ).contains(ext) ? FileType.PNG : null;
        }


        @Bean
        public Matcher jpegFileTypeMatcher() {
            return ext -> List.of(
                    "jpg",
                    "jpeg",
                    "image/jpg",
                    "image/jpeg"
            ).contains(ext) ? FileType.JPEG : null;
        }

    }
}
