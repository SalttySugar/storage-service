package com.salttysugar.blog.storage.file.converter;

import com.salttysugar.blog.storage.file.constant.FileType;
import com.salttysugar.blog.storage.file.model.ApplicationFile;
import com.salttysugar.blog.storage.file.model.ApplicationFileImpl;
import com.salttysugar.blog.storage.file.persistance.MongoFile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class MongoFileToFileConverter implements Converter<MongoFile, ApplicationFile> {

    @Override
    public ApplicationFile convert(MongoFile source) {
        return ApplicationFileImpl
                .builder()
                .id(source.getId())
                .name(source.getName())
                .path(Path.of(source.getPath()))
                .type(FileType.valueOf(source.getType()))
                .build();
    }
}
