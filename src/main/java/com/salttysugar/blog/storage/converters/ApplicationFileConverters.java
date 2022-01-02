package com.salttysugar.blog.storage.converters;

import com.salttysugar.blog.storage.api.dto.FileDTO;
import com.salttysugar.blog.storage.model.ApplicationFile;
import io.vavr.control.Try;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public abstract class ApplicationFileConverters {

    @Component
    public static final class  ApplicationFileToFileDTOConverter implements Converter<ApplicationFile, FileDTO> {
        @Override
        public FileDTO convert(ApplicationFile source) {
            return Try.ofCallable(() -> FileDTO.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .ownerId(source.getOwnerId())
                    .uploadedOn(source.getUploadedOn())
                    .extension(source.getExtension())
                    .build()
            ).get();
        }
    }

}
