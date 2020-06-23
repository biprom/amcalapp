package com.biprom.bram.backend.mongoRepositories;

import com.biprom.bram.backend.data.entity.mongodbEntities.Klanten;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KlantenRepository extends MongoRepository<Klanten, String> {

	public List<Klanten> findByBedrijfsNaamContainsIgnoreCase(String bedrijfsNaam);
	public List<Klanten> findByBtwNummerContainsIgnoreCase(String btwNummer);
	public List<Klanten> findByAanvragers_VoorNaamContainsIgnoreCase(String voornaam);
	public List<Klanten> findByAliasContainsIgnoreCase(String alias);

}
