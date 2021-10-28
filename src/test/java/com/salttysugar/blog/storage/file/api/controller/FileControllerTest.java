package com.salttysugar.blog.storage.file.api.controller;

import com.salttysugar.blog.storage.common.constant.API;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWebTestClient
class FileControllerTest {
    @Autowired
    WebTestClient client;

    @Test
    void it_should_be_able_to_upload_a_file() {
        var bodyBuilder = new MultipartBodyBuilder();
//        bodyBuilder.part("file", new MockMultipartFile("text-file.txt", "this is test file...".getBytes(StandardCharsets.UTF_8)).getResource());
        bodyBuilder.part("file", new MockMultipartFile("file" , "this is test file...".getBytes(StandardCharsets.UTF_8)).getResource());

        client.post()
                .uri(API.V1.FILE.BASE_URL)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}