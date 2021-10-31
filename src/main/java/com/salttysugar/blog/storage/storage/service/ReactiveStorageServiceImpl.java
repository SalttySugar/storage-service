package com.salttysugar.blog.storage.storage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.Writer;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReactiveStorageServiceImpl implements ReactiveStorageService {
    private final Flux<ReactiveStorageWriter> writers;


    @Override
    public Mono<File> store(Object source) {
        return  writers
                .filter(w -> w.canHandle(source))
                .next()
                .flatMap(w -> w.write(source));

    }

    public <T> Mono<File> storeT(T source) {
        return  writers
                .filter(w -> w.canHandle(source))
                .next()
                .map(w -> (ReactiveStorageWriter<T>) w)
                .flatMap(w -> w.write(source));

    }
}
