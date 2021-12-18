package com.salttysugar.blog.storage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplicationFile {
    String id;
    String name;
    String path;
    String extension;
    Date uploadedOn;
    String ownerId;
}
