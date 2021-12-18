package com.salttysugar.blog.storage.converters;

import com.salttysugar.blog.storage.model.Storable;
import com.salttysugar.blog.storage.model.impl.StorableFilePart;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;


public abstract class StorableConverters {
    @Component
    public static final class FilePartToToStorableConverter implements Converter<FilePart, Storable> {
        @Override
        public Storable convert(FilePart source) {
            return new StorableFilePart(source);
        }
    }
}
