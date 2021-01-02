package com.biprom.bram.amcalapp.mongoRepositories;

import com.biprom.bram.amcalapp.data.entity.mongodbEntities.Temp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TempRepository extends MongoRepository<Temp, String> {

}
