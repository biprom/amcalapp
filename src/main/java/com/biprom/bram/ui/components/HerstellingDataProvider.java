package com.biprom.bram.ui.components;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.MainTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import com.biprom.bram.backend.data.entity.Order;
import com.biprom.bram.backend.service.HerstellingService;

@SpringComponent
@PrototypeScope
public class HerstellingDataProvider {

	private final HerstellingService herstellingService;

	@Autowired
	public HerstellingDataProvider(HerstellingService herstellingService) {
		this.herstellingService = herstellingService;
	}

	public List<DetailTicket>getAllDetailsToHerstel(){
		return herstellingService.getAllDetailsToHerstel();
	}

	public List<DetailTicket>getAllDetailsToDemontage(){
		return herstellingService.getAllDetailsToDemontage();
	}

	public List<DetailTicket>getAllDetailsToVoorbereiding(){
		return herstellingService.getAllDetailsToVoorbereiding();
	}
}
