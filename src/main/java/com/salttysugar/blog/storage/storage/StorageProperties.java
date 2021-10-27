package com.salttysugar.blog.storage.storage;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource(value = "classpath:application.yml")
public class StorageProperties {
    @Value("${folder}")
    String folder;
}
