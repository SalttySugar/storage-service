package com.salttysugar.blog.storage.file.persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MongoFile {
    String id;
    String name;
    String path;
    long size;
    String type;
}
