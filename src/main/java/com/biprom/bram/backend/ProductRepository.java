package com.biprom.bram.backend;


import com.biprom.bram.backend.data.entity.mongodbEntities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findAllByArtikelNummerMatchesRegex(String msg);
    List<Product> findByOmschrijvingArtikelFabrikantMatchesRegex(String msg);
    List<Product> findByOmschrijvingArtikelFabrikantContains(String msg);

    List<Product> findByArtikelNummerContainsIgnoreCase(String artikelNummer);
    List<Product> findByVageOmschrijvingContainsIgnoreCase(String vageOmschrijving);
    List<Product> findByOmschrijvingArtikelFabrikantContainsIgnoreCase(String fabrieksomschrijving);
    List<Product> findByEigenOmschrijvingContainsIgnoreCase(String fabrieksomschrijving);


}
