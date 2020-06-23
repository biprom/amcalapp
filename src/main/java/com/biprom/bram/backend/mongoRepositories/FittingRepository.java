package com.biprom.bram.backend.mongoRepositories;


import com.biprom.bram.backend.data.entity.mongodbEntities.Fittings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FittingRepository extends MongoRepository<Fittings, String> {
}
