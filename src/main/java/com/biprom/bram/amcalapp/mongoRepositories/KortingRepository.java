package com.biprom.bram.amcalapp.mongoRepositories;

import com.biprom.bram.amcalapp.data.entity.mongodbEntities.Korting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KortingRepository extends MongoRepository <Korting, String> {
    Korting findByKorting(String kortingsCode);
}
