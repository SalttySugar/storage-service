package com.salttysugar.blog.storage.file.converter;

import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.file.api.dto.FileDTO;
import com.salttysugar.blog.storage.file.model.ApplicationFile;
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
                //TODO: re-think it later
                .url(String.format("%s/%s/meta", API.V1.FILE.BASE_URL, source.getId()))
                .build();
    }
}
