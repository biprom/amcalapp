package com.biprom.bram.amcalapp.mongoRepositories;


import com.biprom.bram.amcalapp.data.entity.mongodbEntities.MobiliteitEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface MobiliteitRepository extends PagingAndSortingRepository<MobiliteitEntity, String> {
   List<MobiliteitEntity> findAllByDatumEqualsAndNaamTechniekerMatches(LocalDate datum, String naamTechnieker);
}
