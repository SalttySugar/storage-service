package com.salttysugar.blog.storage.api.dto;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Api("create/update file")
public final class RequestFileDTO {
    String name;
}
