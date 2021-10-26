package com.salttysugar.blog.storage.common;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ReactiveEntityService<T, ID> {
    Flux<T> list();

    Mono<T> findById(ID id);

    Mono<T> store(T entity);

    Mono<T> update(T entity);

    Mono<Void> deleteById(ID id);
}
