package com.salttysugar.blog.storage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestFileDTO {
    String name;
}
