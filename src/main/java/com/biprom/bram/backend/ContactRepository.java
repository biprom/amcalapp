package com.biprom.bram.backend;

import com.biprom.bram.backend.data.entity.mongodbEntities.Contacten;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends MongoRepository<Contacten, String> {

}
