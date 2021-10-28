package com.salttysugar.blog.storage.file;

import com.salttysugar.blog.storage.file.model.ApplicationFile;
import reactor.core.publisher.Mono;

public interface ApplicationReactiveFileWriter<I> {
    Mono<ApplicationFile> write(I source);
}

