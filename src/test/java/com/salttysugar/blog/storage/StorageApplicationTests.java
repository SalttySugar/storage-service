package com.salttysugar.blog.storage;

import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.services.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.hamcrest.core.IsEqual.equalTo;

@SpringBootTest
@AutoConfigureWebTestClient
class StorageApplicationTests extends BaseIntegrationTest {
    @Autowired
    StorageService service;

    @Autowired
    WebTestClient client;

    @Test
    void contextLoads() {
    }


    @Test
    void shouldUploadNewFileToTheServer() {
        var bodyBuilder = new MultipartBodyBuilder();

        bodyBuilder.part("file", new ClassPathResource("/test/resources/static/test_image_1.jpg"));
        bodyBuilder.part("owner_id", "test_owner_id");

        client.post()
                .uri(API.PATH)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.name").value(equalTo("test_image_1.jpg"))
                .jsonPath("$.extension").value(equalTo("jpg"))
                .jsonPath("$.uploaded_on").exists();
    }


    @Test
    void shouldDeleteFileById() {
        throw new RuntimeException("not implemented");
    }


    @Test
    void shouldUpdateFileName() {
        throw new RuntimeException("not implemented");
    }

    @Test
    void shouldFindFileById() {
        throw new RuntimeException("not implemented");
    }

    @Test
    void shouldRetrieveCollectionOfFiles() {
        throw new RuntimeException("not implemented");
    }

    @Test
    void shouldRetrieveCollectionOfFilesThatMatchSpecificExtension() {
        throw new RuntimeException("not implemented");
    }

    @Test
    void shouldRetrieveCollectionOfFilesThatMatchSpecificExtensions() {
        throw new RuntimeException("not implemented");
    }


    @Test
    void shouldRetrieveCollectionOfFilesThatMatchSpecificIds() {
        throw new RuntimeException("not implemented");
    }


    @Test
    void shouldRetrieveCollectionOfFilesUploadedBySpecificUser() {
        throw new RuntimeException("not implemented");
    }

}
