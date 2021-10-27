package com.salttysugar.blog.storage.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    String id;
    String name;
    long size;
    String url;
}
