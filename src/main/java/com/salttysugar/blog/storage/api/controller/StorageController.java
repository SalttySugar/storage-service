package com.salttysugar.blog.storage.api.controller;

import com.salttysugar.blog.storage.api.dto.FileDTO;
import com.salttysugar.blog.storage.api.dto.UploadFileDTO;
import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.common.constant.Headers;
import com.salttysugar.blog.storage.services.StorageService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping(API.PATH)
@Validated
@RequiredArgsConstructor
public class StorageController {
    private final StorageService service;
    private final ApplicationConverter converter;

    @GetMapping
    public Mono<ResponseEntity<List<FileDTO>>> list(
            @RequestParam(required = false, defaultValue = "10") Long limit,
            @RequestParam(required = false, defaultValue = "0") Long offset
    ) {
        return Mono.fromCallable(ResponseEntity::ok)
                .flatMap(response -> service.count()
                        .map(total -> response.header(Headers.TOTAL_COUNT, String.valueOf(total)))
                )
                .flatMap(response -> service.findAll()
                        .skip(offset)
                        .take(limit)
                        .map(converter.convert(FileDTO.class))
                        .collectList()
                        .map(response::body)
                );
    }

    @GetMapping("/{id}/meta")
    public Mono<FileDTO> retrieveMeta(@PathVariable String id) {
        return service.findById(id)
                .map(converter.convert(FileDTO.class));
    }

    @PutMapping("/{id}/meta")
    public Mono<FileDTO> updateFileMeta(@RequestBody UploadFileDTO dto, @PathVariable String id) {
        return service.update(id, dto)
                .map(converter.convert(FileDTO.class));

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Resource>> retrieve(@PathVariable String id) {
        return service.findById(id)
                .map(applicationFile -> {
                    var resource = Try.ofCallable(() -> new FileSystemResource(Path.of(applicationFile.getPath())))
                            .get();

                    var contentType = MediaTypeFactory.getMediaType(applicationFile.getName())
                            .orElse(MediaType.APPLICATION_OCTET_STREAM);

                    return ResponseEntity.ok()
                            .contentType(contentType)
                            .body(resource);
                });
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<FileDTO> upload(@Valid @ModelAttribute UploadFileDTO dto) {
        return service.store(dto)
                .map(converter.convert(FileDTO.class));
    }


    @DeleteMapping("/{id}")
    Mono<Void> delete(@PathVariable String id) {
        return service.deleteById(id);
    }

}
