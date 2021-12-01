package com.salttysugar.blog.storage.converter;

import com.salttysugar.blog.storage.model.ApplicationFile;
import com.salttysugar.blog.storage.persistance.MongoFile;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FileToMongoFileConverter implements Converter<ApplicationFile, MongoFile> {
    @SneakyThrows
    @Override
    public MongoFile convert(ApplicationFile source) {
        return MongoFile.builder()
                .name(source.getName())
                .path(source.getPath().toString())
                .type(source.getType().toString())
                .size(source.getSize())
                .build();
    }
}
