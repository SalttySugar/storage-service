package com.salttysugar.blog.storage.converter;

import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.api.dto.ResponseFileDTO;
import com.salttysugar.blog.storage.model.ApplicationFile;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FileToFileDTOConverter implements Converter<ApplicationFile, ResponseFileDTO> {

    @SneakyThrows
    @Override
    public ResponseFileDTO convert(ApplicationFile source) {
        return ResponseFileDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .size(source.getSize())
                //TODO: re-think it later
                .url(String.format("%s/%s", API.V1.FILE.BASE_URL, source.getId()))
                .build();
    }
}
