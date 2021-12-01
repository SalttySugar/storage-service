package com.salttysugar.blog.storage.persistance;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoFileRepository extends ReactiveMongoRepository<MongoFile, String> {
}
