package com.salttysugar.blog.storage.file.converter;

import com.salttysugar.blog.storage.file.model.File;
import com.salttysugar.blog.storage.file.persistance.MongoFile;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FileToMongoFileConverter implements Converter<File, MongoFile> {
    @SneakyThrows
    @Override
    public MongoFile convert(File source) {
        return MongoFile.builder()
                .name(source.getName())
                .path(source.getPath().toString())
                .type(source.getType().toString())
                .size(source.getSize())
                .build();
    }
}