package com.salttysugar.blog.storage.api.controller;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureWebTestClient
class FileControllerTest {
//    @Autowired
//    WebTestClient client;
//
//    @Autowired
//    FileService service;
//
//    @TempDir
//    File file;
//
//
//    @MockBean
//    StorageConfig config;
//
//    @BeforeEach
//    void beforeAll() {
//        Mockito.when(config.getFolder()).thenReturn(file.toPath());
//    }
//
//    @Test
//    void it_should_be_able_to_upload_a_file() {
//        var bodyBuilder = new MultipartBodyBuilder();
//        bodyBuilder.part("file", new FileSystemResource("src/test/resources/static/test_image_1.jpg"));
//
//
//        client.post()
//                .uri(API.V1.FILE.BASE_URL)
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$.id").exists()
//                .consumeWith(System.out::println);
//    }
//
//    @Test
//    void it_should_be_able_to_retrieve_uploaded_file() {
//        Resource source = new FileSystemResource("src/test/resources/static/test_image_1.jpg");
//    }
}