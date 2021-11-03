package com.salttysugar.blog.storage.file.api.controller;

import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.file.api.dto.FileDTO;
import com.salttysugar.blog.storage.file.domain.model.storable.Storable;
import com.salttysugar.blog.storage.file.domain.service.FileService;
import com.salttysugar.blog.storage.file.utils.resolver.mediatype.MediaTypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(API.V1.FILE.BASE_URL)
@RequiredArgsConstructor
public class FileController {
    private final FileService service;
    private final ApplicationConverter converter;
    private final MediaTypeResolver mediaTypeResolver;

    @GetMapping
    public Flux<FileDTO> list() {
        return service.list()
                .map(converter.convert(FileDTO.class));
    }

    @GetMapping("/{id}/meta")
    public Mono<FileDTO> retrieveMeta(@PathVariable String id) {
        return service.getFileById(id)
                .map(converter.convert(FileDTO.class));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Resource>> retrieve(@PathVariable String id) {
        return service.getFileById(id)
                .map(applicationFile -> {
                    var path = applicationFile.getPath();
                    var fileType = applicationFile.getType();
                    var resource = new FileSystemResource(path);
                    var contentType = mediaTypeResolver.resolve(fileType)
                            .orElseThrow(() -> new RuntimeException("Could not retrieve file type"));

                    return ResponseEntity.ok()
                            .contentType(contentType)
                            .body(resource);

                });
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<FileDTO> upload(@RequestPart("file") Mono<FilePart> part) {
        return part
                .map(converter.convert(Storable.class))
                .flatMap(service::store)
                .map(converter.convert(FileDTO.class));
    }

}
