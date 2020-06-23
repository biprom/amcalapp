package com.biprom.bram.backend.mongoRepositories;

import com.biprom.bram.backend.data.entity.mongodbEntities.ElektrMateriaal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ElektrischMateriaalRepository extends MongoRepository<ElektrMateriaal, String> {
    List<ElektrMateriaal> findByArtikelCodeContains(String artikelCode);
}
