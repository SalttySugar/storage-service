package com.salttysugar.blog.storage.converter;

import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.api.dto.FileDTO;
import com.salttysugar.blog.storage.model.ApplicationFile;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FileToFileDTOConverter implements Converter<ApplicationFile, FileDTO> {

    @SneakyThrows
    @Override
    public FileDTO convert(ApplicationFile source) {
        return FileDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .size(source.getSize())
                .type(source.getType())
                .build();
    }
}
