package com.salttysugar.blog.storage.api.controller;

import com.salttysugar.blog.storage.api.dto.RequestFileDTO;
import com.salttysugar.blog.storage.api.dto.ResponseFileDTO;
import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.model.impl.StorableFilePart;
import com.salttysugar.blog.storage.service.FileService;
import com.salttysugar.blog.storage.utils.resolver.mediatype.MediaTypeResolver;
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
    public Flux<ResponseFileDTO> list() {
        return service.findAll()
                .map(converter.convert(ResponseFileDTO.class));
    }

    @GetMapping("/{id}/meta")
    public Mono<ResponseFileDTO> retrieveMeta(@PathVariable String id) {
        return service.getById(id)
                .map(converter.convert(ResponseFileDTO.class));
    }

    @PutMapping("/{id}/meta")
    public Mono<ResponseFileDTO> updateFileMeta(@RequestBody RequestFileDTO dto, @PathVariable String id) {
        return service.update(id, dto)
                .map(converter.convert(ResponseFileDTO.class));

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Resource>> retrieve(@PathVariable String id) {
        return service.getById(id)
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
    public Mono<ResponseFileDTO> upload(@RequestPart("file") Mono<FilePart> part) {
        return part
                .map(StorableFilePart::new)
                .flatMap(service::store)
                .map(converter.convert(ResponseFileDTO.class));
    }


    @DeleteMapping("/{id}")
    Mono<Void> delete(@PathVariable String id) {
        return service.deleteById(id);
    }

}
