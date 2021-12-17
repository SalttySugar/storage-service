package com.salttysugar.blog.storage.utils.resolver;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public final class Functional {
    public static <T> Function<T, T> peek(Consumer<T> c) {
        return x -> {
            c.accept(x);
            return x;
        };
    }
}
