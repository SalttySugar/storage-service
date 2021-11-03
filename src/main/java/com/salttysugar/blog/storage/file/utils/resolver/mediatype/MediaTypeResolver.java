package com.salttysugar.blog.storage.file.utils.resolver.mediatype;

import com.salttysugar.blog.storage.file.domain.constant.FileType;
import org.springframework.http.MediaType;

import java.util.Optional;

public interface MediaTypeResolver {
    Optional<MediaType> resolve(FileType type);
}
