package com.salttysugar.blog.storage.common;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ApplicationConverterImpl implements ApplicationConverter {
    private final ConversionService service;

    public ApplicationConverterImpl(@Qualifier("webFluxConversionService") ConversionService service) {
        this.service = service;
    }

    @Override
    public <T> Function<Object, T> convert(Class<T> type) {
        return t -> service.convert(t, type);
    }
}
