package com.salttysugar.blog.storage.converters;

import com.salttysugar.blog.storage.model.ApplicationFile;
import com.salttysugar.blog.storage.persistance.MongoFile;
import io.vavr.control.Try;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public abstract class MongoFileConverters {
    @Component
    public static final class ApplicationFileToMongoFileConverter implements Converter<ApplicationFile, MongoFile> {
        @Override
        public  MongoFile convert(ApplicationFile source) {
            return Try.ofCallable(() -> MongoFile.builder()
                    .name(source.getName())
                    .path(source.getPath().toString())
                    .size(source.getSize())
                    .build()
            ).get();
        }
    }

}
