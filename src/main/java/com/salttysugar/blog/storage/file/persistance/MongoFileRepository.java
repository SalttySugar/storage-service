package com.salttysugar.blog.storage.file.persistance;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoFileRepository extends ReactiveMongoRepository<MongoFile, String> {
}
