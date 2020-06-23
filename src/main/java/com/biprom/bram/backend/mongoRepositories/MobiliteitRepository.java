package com.biprom.bram.backend.mongoRepositories;


import com.biprom.bram.backend.data.entity.mongodbEntities.MobiliteitEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface MobiliteitRepository extends PagingAndSortingRepository<MobiliteitEntity, String> {
   List<MobiliteitEntity> findAllByDatumEqualsAndNaamTechniekerMatches(LocalDate datum, String naamTechnieker);
}
