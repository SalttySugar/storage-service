package com.salttysugar.blog.storage.file.api.controller;

import com.salttysugar.blog.storage.common.ApplicationConverter;
import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.file.core.writer.Writer;
import com.salttysugar.blog.storage.file.api.dto.FileDTO;
import com.salttysugar.blog.storage.file.model.ApplicationFile;
import com.salttysugar.blog.storage.file.service.ReactiveFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(API.V1.FILE.BASE_URL)
@RequiredArgsConstructor
public class FileController {
    private final ReactiveFileService service;
    private final ApplicationConverter converter;
    private final Writer<FilePart, Mono<ApplicationFile>> writer;

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
    public Mono<Resource> retrieve(@PathVariable String id) {
        return service.getFileById(id)
                .map(ApplicationFile::getPath)
                .map(FileSystemResource::new);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<FileDTO> upload(@RequestPart("file") Mono<FilePart> part) {
         return part
                 .flatMap(writer::write)
                 .flatMap(service::save)
                 .map(converter.convert(FileDTO.class));
    }

}
