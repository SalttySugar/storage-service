package com.salttysugar.blog.storage.file.utils.resolver.mediatype.impl;

import com.salttysugar.blog.storage.file.domain.constant.FileType;
import com.salttysugar.blog.storage.file.utils.resolver.mediatype.MediaTypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class MediaTypeResolverImpl implements MediaTypeResolver {
    private final Collection<MediaTypeMatcher> matchers;

    @Override
    public Optional<MediaType> resolve(FileType type) {
        return matchers.stream()
                .map(matcher -> matcher.test(type))
                .filter(Objects::nonNull)
                .findFirst();
    }

    public static interface MediaTypeMatcher {
        MediaType test(FileType type);
    }

    @Component
    public static final class MediaTypeMatchers {
        @Bean
        MediaTypeMatcher textMediaTypeMatcher() {
            return type -> type.equals(FileType.JPEG) ? MediaType.TEXT_PLAIN : null;
        }

        @Bean
        MediaTypeMatcher jpgMediaTypeMatcher() {
            return type -> type.equals(FileType.JPG) ? MediaType.IMAGE_JPEG : null;
        }

        @Bean
        MediaTypeMatcher jpegMediaTypeMatcher() {
            return type -> type.equals(FileType.JPEG) ? MediaType.IMAGE_JPEG : null;
        }

        @Bean
        MediaTypeMatcher pngMediaTypeMatcher() {
            return type -> type.equals(FileType.PNG) ? MediaType.IMAGE_PNG : null;
        }

    }
}
