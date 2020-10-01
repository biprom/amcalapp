package com.biprom.bram.backend;

import com.biprom.bram.backend.data.entity.mongodbEntities.Personeel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersoneelRepository extends MongoRepository<Personeel, String> {

    List<Personeel> findAllByInlogNaamMatchesRegex(String msg);

}
