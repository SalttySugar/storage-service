package com.salttysugar.blog.storage.common;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Converter {
    private final ConversionService service;

    public Converter(@Qualifier("mvcConversionService") ConversionService service) {
        this.service = service;
    }

    public <T> Function<Object, T> convert(Class<T> type) {
        return t -> service.convert(t, type);
    }
}
