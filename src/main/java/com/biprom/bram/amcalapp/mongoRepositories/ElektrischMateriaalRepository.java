package com.biprom.bram.amcalapp.mongoRepositories;

import com.biprom.bram.amcalapp.data.entity.mongodbEntities.ElektrMateriaal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ElektrischMateriaalRepository extends MongoRepository<ElektrMateriaal, String> {
    List<ElektrMateriaal> findByArtikelCodeContains(String artikelCode);
}
