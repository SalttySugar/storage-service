package com.salttysugar.blog.storage.configs.impl;

import com.salttysugar.blog.storage.configs.StorageConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.nio.file.Path;

@Configuration
@PropertySource(value = "classpath:application.yml")
public class StorageConfigPropertiesImpl implements StorageConfig {
    @Value("${folder}")
    String folder;

    @Override
    public Path getFolder() {
        return Path.of(folder);
    }
}
