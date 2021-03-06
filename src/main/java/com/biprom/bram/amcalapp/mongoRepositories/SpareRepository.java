package com.biprom.bram.amcalapp.mongoRepositories;

import com.biprom.bram.amcalapp.data.entity.mongodbEntities.Spare;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SpareRepository extends PagingAndSortingRepository<Spare, String> {
    List<Spare> findByArtikelNummerMatchesRegex(String msg);
    List<Spare> findByOmschrijvingArtikelFabrikantMatchesRegex(String msg);
    List<Spare> findBySpareGroup(String msg);
    List<Spare> findBySpareBOM(String msg);
    List<Spare> findBySpareNummer(String msg);
    List<Spare> findBySpareOmschrijvingFabrikant(String msg);
}
