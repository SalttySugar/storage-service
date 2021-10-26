package com.salttysugar.blog.storage.file;

import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.file.model.File;
import com.salttysugar.blog.storage.file.service.ReactiveFileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(API.V1.FILE.BASE_URL)
@RequiredArgsConstructor
public class FileController {
    private final ReactiveFileService service;

    @GetMapping
    private Flux<File> list() {
        return service.list();
    }

    @GetMapping("/{name}")
    @SneakyThrows
    private Mono<Byte[]> retrieve(@PathVariable String name) {
        return service.findById(name)
                .map(File::getContent);

    }


    @PostMapping
    private Mono<File> upload(MultipartFile file) {

    }
}
