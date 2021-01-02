package com.biprom.bram.amcalapp.mongoRepositories;

import com.biprom.bram.amcalapp.data.entity.mongodbEntities.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends MongoRepository<Type, String> {

    List<Type> deleteAllByReferentie(String referentie);
    List<Type> findByTypeMatchesRegex(String type);
    List<Type> findAll();

}
