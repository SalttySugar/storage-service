package com.salttysugar.blog.storage.file.utils.converter;

import com.salttysugar.blog.storage.file.domain.model.storable.Storable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@Component
public class FilePartToStorableConverter implements Converter<FilePart, Storable> {
    @Override
    public Storable convert(FilePart source) {
        return new Storable() {
            @Override
            public String getFileName() {
                return source.filename();
            }

            @Override
            public Mono<Void> moveTo(Path path) {
                return source.transferTo(path);
            }
        };
    }
}
