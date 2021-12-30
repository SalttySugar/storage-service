package com.salttysugar.blog.storage;

import com.salttysugar.blog.storage.api.dto.FileDTO;
import com.salttysugar.blog.storage.api.dto.UploadFileDTO;
import com.salttysugar.blog.storage.common.constant.API;
import com.salttysugar.blog.storage.model.StorableResourceImpl;
import com.salttysugar.blog.storage.services.StorageService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.net.URL;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureWebTestClient
class StorageApplicationTests extends BaseIntegrationTest {
    @Autowired
    StorageService service;

    @Autowired
    WebTestClient client;


    public static abstract class Helpers {
        public static Resource getResource(String path) {
            URL url = Thread.currentThread().getContextClassLoader().getResource(path);
            assert url != null;
            return new FileSystemResource(url.getPath());
        }
    }


    @Test
    void contextLoads() {
    }


    @Nested
    public class StorageApplicationCrudTests {
        @Test
        void shouldUploadNewFileToTheServer() {
            var bodyBuilder = new MultipartBodyBuilder();
            bodyBuilder.part("file", Helpers.getResource("test-image-1.jpg"));
            bodyBuilder.part("owner_id", "test_owner_id");
            client.post()
                    .uri(API.PATH)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.id").exists()
                    .jsonPath("$.name").value(is("test-image-1.jpg"))
                    .jsonPath("$.extension").value(is("jpg"))
                    .jsonPath("$.uploaded_on").exists();
        }


        @Test
        void shouldUpdateFileName() {

        }

        @Test
        void shouldFindFileById() {
            var file = service.store(UploadFileDTO.builder()
                    .file(new StorableResourceImpl(Helpers.getResource("test-image-1.jpg")))
                    .owner_id("test-owner-id")
                    .build())
                    .block();

            assert file != null;

            client.get()
                    .uri(API.PATH + "/" + file.getId() + "/meta")
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(FileDTO.class);
        }

        @Test
        void shouldRetrieveCollectionOfFiles() {
            throw new RuntimeException("not implemented");
        }

        @Test
        void shouldDeleteFileById() {
            throw new RuntimeException("not implemented");
        }
    }


    public class StorageApplicationFilterTest {

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

}
