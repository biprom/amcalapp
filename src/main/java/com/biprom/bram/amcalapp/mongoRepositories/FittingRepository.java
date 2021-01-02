package com.biprom.bram.amcalapp.mongoRepositories;


import com.biprom.bram.amcalapp.data.entity.mongodbEntities.Fittings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FittingRepository extends MongoRepository<Fittings, String> {
}
