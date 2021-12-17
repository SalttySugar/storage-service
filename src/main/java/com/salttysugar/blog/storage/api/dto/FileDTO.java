package com.salttysugar.blog.storage.api.dto;

import com.salttysugar.blog.storage.model.FileType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("File")
public final class FileDTO {
    String id;
    String name;
    FileType type;
    long size;
}
