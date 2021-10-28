package com.salttysugar.blog.storage.common;

import java.util.function.Function;

public interface ApplicationConverter {
    <T> Function<Object, T> convert(Class<T> type);
}
