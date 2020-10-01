package com.biprom.bram.backend;

import com.biprom.bram.backend.data.entity.mongodbEntities.Temp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TempRepository extends MongoRepository<Temp, String> {

}
