package com.biprom.bram.amcalapp.ui.components;

import java.util.List;

import com.biprom.bram.amcalapp.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.amcalapp.service.HerstellingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;

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

	public List<DetailTicket>getAllDetailsToInox(){
		return herstellingService.getAllDetailsToInox();
	}
}
