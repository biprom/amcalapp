package com.biprom.bram.amcalapp.mongoRepositories;



import com.biprom.bram.amcalapp.data.entity.mongodbEntities.FittingPrijsLijst;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FittingPrijsRepository extends MongoRepository<FittingPrijsLijst, String> {
}
