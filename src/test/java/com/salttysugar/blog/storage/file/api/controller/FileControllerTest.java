package com.salttysugar.blog.storage.file.api.controller;

import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.file.core.writer.Writer;
import com.salttysugar.blog.storage.file.domain.model.ApplicationFile;
import com.salttysugar.blog.storage.file.domain.service.ReactiveFileService;
import com.salttysugar.blog.storage.storage.config.StorageConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.io.File;

@SpringBootTest
@AutoConfigureWebTestClient
class FileControllerTest {
    @Autowired
    WebTestClient client;

    @Autowired
    ReactiveFileService service;

    @TempDir
    File file;


    @MockBean
    StorageConfig config;

    @BeforeEach
    void beforeAll() {
        Mockito.when(config.getFolder()).thenReturn(file.toPath());
    }

    @Test
    void it_should_be_able_to_upload_a_file() {
        var bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file", new FileSystemResource("/home/skoll/Desktop/model .jpg"));

        var result = client.post()
                .uri(API.V1.FILE.BASE_URL)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").exists()
                .consumeWith(System.out::println);
    }

    @Test
    void it_should_be_able_to_retrieve_uploaded_file(@Autowired Writer<Resource, Mono<ApplicationFile>> writer) {
        Resource source = new FileSystemResource("src/test/resources/static/test_image_1.jpg");
        writer.write(source)
                .flatMap(service::save)
                .doOnNext(file ->
                        client.get()
                                .uri(String.format("%s/%s", API.V1.FILE.BASE_URL, file.getId()))
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody()
                                .jsonPath("$.id").exists()
                                .consumeWith(System.out::println)
                )
                .block();

    }
}