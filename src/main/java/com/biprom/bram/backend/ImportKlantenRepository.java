package com.biprom.bram.backend;

import com.biprom.bram.backend.data.entity.mongodbEntities.ImportKlanten;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportKlantenRepository extends MongoRepository<ImportKlanten, String> {



}
