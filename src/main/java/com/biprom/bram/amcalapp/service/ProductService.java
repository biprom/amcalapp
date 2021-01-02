package com.biprom.bram.amcalapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biprom.bram.amcalapp.mongoRepositories.OldProductRepository;
import com.biprom.bram.amcalapp.data.entity.Product;

@Service
public class ProductService extends CrudService<Product> {

	private final OldProductRepository oldProductRepository;

	@Autowired
	public ProductService(OldProductRepository oldProductRepository) {
		this.oldProductRepository = oldProductRepository;
	}

	@Override
	public Page<Product> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return getRepository().findAll(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByNameLikeIgnoreCase(repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	@Override
	protected OldProductRepository getRepository() {
		return oldProductRepository;
	}

}
