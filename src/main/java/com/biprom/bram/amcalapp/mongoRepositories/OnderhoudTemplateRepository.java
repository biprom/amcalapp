package com.biprom.bram.amcalapp.mongoRepositories;

import com.biprom.bram.amcalapp.data.entity.mongodbEntities.OnderhoudTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnderhoudTemplateRepository  extends MongoRepository<OnderhoudTemplate, String> {

    List<OnderhoudTemplate> findByOnderhoudContractNummerContains(String contractNummer);

}
