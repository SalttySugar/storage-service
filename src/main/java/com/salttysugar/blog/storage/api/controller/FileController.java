package com.salttysugar.blog.storage.api.controller;

import com.salttysugar.blog.storage.api.dto.FileDTO;
import com.salttysugar.blog.storage.api.dto.UploadFileDTO;
import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.common.constant.API;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.nio.file.Path;

@RestController
@RequestMapping(API.V1.FILE.BASE_URL)
@Validated
@RequiredArgsConstructor
public class FileController {
    private final StorageService service;
    private final ApplicationConverter converter;

    @GetMapping
    public Flux<FileDTO> list() {
        return service.findAll()
                .map(converter.convert(FileDTO.class));
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
