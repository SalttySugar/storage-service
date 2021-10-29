package com.salttysugar.blog.storage.common;

import io.vavr.CheckedFunction0;
import io.vavr.control.Either;
import io.vavr.control.Try;
import reactor.core.publisher.Mono;

import javax.inject.Provider;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public abstract class FunctionalUtils {
    public static <T> Mono<Try<T>> toMonoTry(CheckedFunction0<T> fn) {
        return Mono.just(Try.of(fn));
    }

    public static <T> Mono<T> monoFromEither(Either<Throwable, T> either) {
        return either.fold(
                Mono::error,
                Mono::just
        );
    }

    public static <T> Mono<T> monoFromTry(Try<T> _try) {
        return monoFromEither(_try.toEither());
    }


    public static <T> Provider<T> always(T something) {
        return () -> something;
    }

    public static <T, A> Function<A, T> idenity(T something) {
        return (a) -> something;
    }

    public static <T> UnaryOperator<T> peek(Consumer<T> c) {
        return x -> {
            c.accept(x);
            return x;
        };
    }
}
