package com.salttysugar.blog.storage.file.core.resolver.mediatype;

import com.salttysugar.blog.storage.file.constant.FileType;
import org.springframework.http.MediaType;

import java.util.Optional;

public interface MediaTypeResolver {
    Optional<MediaType> resolve(FileType type);
}
