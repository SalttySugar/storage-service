package com.salttysugar.blog.storage.file.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FileType {
    JPEG("image/jpeg"),
    JPG("image/jpg"),
    PNG("image/jpg"),
    TXT("text/plaintext");

    @Getter
    private final String type;
}
