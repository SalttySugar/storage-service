package com.salttysugar.blog.storage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFileDTO {
    String id;
    String name;
    long size;
    String url;
}
