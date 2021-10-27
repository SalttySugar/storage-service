package com.salttysugar.blog.storage.file.converter;

import com.salttysugar.blog.storage.file.constant.FileType;
import com.salttysugar.blog.storage.file.model.File;
import com.salttysugar.blog.storage.file.model.FileImpl;
import com.salttysugar.blog.storage.file.persistance.MongoFile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class MongoFileToFileConverter implements Converter<MongoFile, File> {

    @Override
    public File convert(MongoFile source) {
        return FileImpl
                .builder()
                .id(source.getId())
                .name(source.getName())
                .path(Path.of(source.getPath()))
                .type(FileType.valueOf(source.getType()))
                .build();
    }
}
