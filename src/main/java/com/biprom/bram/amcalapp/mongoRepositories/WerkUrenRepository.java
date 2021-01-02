package com.biprom.bram.amcalapp.mongoRepositories;


import com.biprom.bram.amcalapp.data.entity.mongodbEntities.WerkUren;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WerkUrenRepository extends MongoRepository<WerkUren, String> {

    List<WerkUren> findByStartDatumTijdBetween(LocalDate start, LocalDate stop);
    List<WerkUren> findByStartDatumTijd(LocalDate date);
    List<WerkUren> findByInlogNaamTechnieker(String inlogNaamTechnieker);
    List<WerkUren> findByInlogNaamTechniekerAndStartDatumTijdBetween(String naam, LocalDate start, LocalDate stop);
    List<WerkUren> findByOmschrijvingContains(String amNummer);

}
