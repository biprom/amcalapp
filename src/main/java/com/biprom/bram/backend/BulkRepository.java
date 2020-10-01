package com.biprom.bram.backend;


import com.biprom.bram.backend.data.entity.mongodbEntities.Bulk;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BulkRepository extends PagingAndSortingRepository<Bulk, String> {
    List<Bulk> findByArtikelNummerMatchesRegex(String msg);
    List<Bulk> findByOmschrijvingArtikelFabrikantMatchesRegex(String msg);
    List<Bulk> findByBulkGroep(String msg);
    List<Bulk> findByBulkBOM(String msg);
    List<Bulk> findByBulkNummer(String msg);
    List<Bulk> findByBulkOmschrijvingFabrikant(String msg);



}
