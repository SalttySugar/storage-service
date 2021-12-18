package com.salttysugar.blog.storage.converters;

import com.salttysugar.blog.storage.api.dto.FileDTO;
import com.salttysugar.blog.storage.model.ApplicationFile;
import com.salttysugar.blog.storage.model.impl.ApplicationFileImpl;
import com.salttysugar.blog.storage.persistance.MongoFile;
import io.vavr.control.Try;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

public abstract class ApplicationFileConverters {

    @Component
    public static final class MongoFileToFileConverter implements Converter<MongoFile, ApplicationFile> {
        @Override
        public ApplicationFile convert(MongoFile source) {
            return ApplicationFileImpl
                    .builder()
                    .id(source.getId())
                    .name(source.getName())
                    .path(Path.of(source.getPath()))
                    .build();
        }
    }


    @Component
    public static final class  ApplicationFileToFileDTOConverter implements Converter<ApplicationFile, FileDTO> {
        @Override
        public FileDTO convert(ApplicationFile source) {
            return Try.ofCallable(() -> FileDTO.builder()
                    .id(source.getId())
                    .size(source.getSize())
                    .name(source.getName())
                    .extension(source.getExtension())
                    .build()
            ).get();
        }
    }

}
