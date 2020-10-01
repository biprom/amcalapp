package com.biprom.bram.backend;



import com.biprom.bram.backend.data.entity.mongodbEntities.FittingPrijsLijst;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FittingPrijsRepository extends MongoRepository<FittingPrijsLijst, String> {
}
