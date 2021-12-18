package com.salttysugar.blog.storage.persistance;

import com.salttysugar.blog.storage.model.ApplicationFile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoFileRepository extends ReactiveMongoRepository<ApplicationFile, String> {
}
