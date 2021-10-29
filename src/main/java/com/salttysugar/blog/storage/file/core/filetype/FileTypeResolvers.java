package com.salttysugar.blog.storage.file.core.filetype;

import com.salttysugar.blog.storage.file.constant.FileType;
import com.salttysugar.blog.storage.file.core.filetype.resolver.FileTypeResolver;
import com.salttysugar.blog.storage.file.core.filetype.resolver.GenericFileTypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FileTypeResolvers {
    @Bean
    FileTypeResolver textFileTypeResolver() {
        return new GenericFileTypeResolver(FileType.TXT, List.of(
                "txt",
                "text/plain"
        ));
    }


    @Bean
    FileTypeResolver pngFileTypeResolver() {
        return new GenericFileTypeResolver(FileType.PNG, List.of(
                "png",
                "image/png"
        ));
    }

    @Bean
    FileTypeResolver jpegFileTypeResolver() {
        return new GenericFileTypeResolver(FileType.JPEG, List.of(
                "jpeg",
                "image/jpeg"
        ));
    }

    @Bean
    FileTypeResolver jpgFileTypeResolver() {
        return new GenericFileTypeResolver(FileType.JPG, List.of(
                "jpg",
                "image/jpg"
        ));
    }

}
