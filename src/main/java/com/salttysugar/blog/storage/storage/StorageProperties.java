package com.salttysugar.blog.storage.storage;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource(value = "classpath:application.properties")
public class StorageProperties {
    String folder;
}
