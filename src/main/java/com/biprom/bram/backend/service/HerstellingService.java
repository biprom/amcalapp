package com.biprom.bram.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.biprom.bram.backend.Enums.Enums;
import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.MainTicket;
import com.biprom.bram.backend.MainTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biprom.bram.backend.data.OrderState;

@Service
public class HerstellingService {

	private final MainTicketRepository mainTicketRepository;

	private static Set<OrderState> notAvailableStates;

	List<MainTicket> mainTicketListTeHerstellen;
	List<DetailTicket> detailTicketListTeHerstellen = new ArrayList<>();
	List<DetailTicket> detailTicketListTeDemonteren = new ArrayList<>();
	List<DetailTicket> detailTicketListVoorTeBereiden = new ArrayList<>();
	List<DetailTicket> inoxEntityList = new ArrayList();

	static {
		notAvailableStates = new HashSet<>( Arrays.asList( OrderState.values() ) );
		notAvailableStates.remove( OrderState.DELIVERED );
		notAvailableStates.remove( OrderState.READY );
		notAvailableStates.remove( OrderState.CANCELLED );
	}

	@Autowired
	public HerstellingService(MainTicketRepository mainTicketRepository) {
		this.mainTicketRepository = mainTicketRepository;
	}

	public List<DetailTicket> getAllDetailsToHerstel() {

		detailTicketListTeHerstellen.clear();

		mainTicketListTeHerstellen = mainTicketRepository.findByDetails_bHerstellingBestekAndDetails_bTeDemonterenAndDetails_bPompHersteld( true, true, false );
		for (MainTicket mainTicket : mainTicketListTeHerstellen) {
			List<DetailTicket> detailTicketListFiltered1 = mainTicket.getDetails().stream()
					.filter( filter -> (getPompStatus( filter ).toString().equals( "ALLEONDERDELENBINNEN" ))
							&& (filter.getamNummer().contains( "AM" ))
							&& ((filter.getCheckListBestek().isHerschilderen() && filter.getCheckListBestek().isPompMWGespoten()
							|| ((filter.getCheckListBestek().isZandstralenEnHerschilderen() && filter.getCheckListBestek().isPompMWGezandstraald()))
							|| (!filter.getCheckListBestek().isZandstralenEnHerschilderen() && !filter.getCheckListBestek().isHerschilderen()))) ).collect( Collectors.toList() );

			detailTicketListFiltered1.stream().forEach( x -> x.setOpdrachtgever( mainTicket.getOpdrachtgever().getBedrijfsNaam() ) );

			detailTicketListTeHerstellen.addAll( detailTicketListFiltered1 );

		}
		return detailTicketListTeHerstellen;
	}

	private Object getPompStatus(DetailTicket detailTicket) {
		Enums.TicketStatus ticketStatus = null;

		try {

			ticketStatus = Enums.TicketStatus.NIETGESTART;
			if (detailTicket.isbTeDemonteren()) {
				ticketStatus = Enums.TicketStatus.TEDEMONTEREN;
			}
			if (detailTicket.isbTeDemonteren()) {
				ticketStatus = Enums.TicketStatus.GEDEMONTEERD;
			}
			if (detailTicket.isbBestekTeMaken()) {
				ticketStatus = Enums.TicketStatus.BESTEKTEMAKEN;
			}
			if (detailTicket.isbWachtOpLevernacier()) {
				ticketStatus = Enums.TicketStatus.WACHTOPLEVERANCIER;
			}
			if (detailTicket.isbWachtOpEline()) {
				ticketStatus = Enums.TicketStatus.WACHTOPELINE;
			}
			if (detailTicket.isbGoedgekeurdEline()) {
				ticketStatus = Enums.TicketStatus.GOEDGEKEURDELINE;
			}
			if (detailTicket.isbBestekVerstuurd()) {
				ticketStatus = Enums.TicketStatus.BESTEKVERSTUURD;
			}
			if (detailTicket.isbWachtOpAntwoord()) {
				ticketStatus = Enums.TicketStatus.WACHTOPANTWOORD;
			}
			if (detailTicket.isbRappel1()) {
				ticketStatus = Enums.TicketStatus.RAPPEL1;
			}
			if (detailTicket.isbRappel2()) {
				ticketStatus = Enums.TicketStatus.RAPPEL2;
			}
			if (detailTicket.isbRappel3()) {
				ticketStatus = Enums.TicketStatus.RAPPEL3;
			}
			if (detailTicket.isbAkkoordKlant()) {
				ticketStatus = Enums.TicketStatus.AKKOORDKLANT;
			}
			if (detailTicket.isbBestellingDoorgevoerd()) {
				ticketStatus = Enums.TicketStatus.BESTELLINGDOORGEVOERD;
			}
			if (detailTicket.isbWachtOpOnderdelen()) {
				ticketStatus = Enums.TicketStatus.WACHTOPONDERDELEN;
			}
			if (detailTicket.isbAlleOnderdelenBinnen()) {
				ticketStatus = Enums.TicketStatus.ALLEONDERDELENBINNEN;
			}
			if (detailTicket.isbPompHersteld()) {
				ticketStatus = Enums.TicketStatus.POMPHERSTELD;
			}
			if (detailTicket.isbPompGeleverd()) {
				ticketStatus = Enums.TicketStatus.POMPGELEVERD;
			}
			if (detailTicket.isbPompAfgehaald()) {
				ticketStatus = Enums.TicketStatus.POMPAFGEHAALD;
			}
			if (detailTicket.isbPompVerschroten()) {
				ticketStatus = Enums.TicketStatus.POMPVERSCHROTEN;
			}
			if (detailTicket.isbPompOnhersteldTerug()) {
				ticketStatus = Enums.TicketStatus.POMPONHERSTELDTERUG;
			}
			if (detailTicket.isOpdrachtAfgewerkt()) {
				ticketStatus = Enums.TicketStatus.KLAARVOORAFHALING;
			}
			if (detailTicket.isbHerstelBestekAfgewerkt()) {
				ticketStatus = Enums.TicketStatus.AFGEWERKT;
			}

		} catch (Exception e) {

		}
		return ticketStatus;
	}


	public List<DetailTicket> getAllDetailsToDemontage() {
		detailTicketListTeDemonteren.clear();
		List<MainTicket> mainTicketListTeDemonternen = mainTicketRepository.findByDetails_bHerstellingBestekAndDetails_bTeDemonterenAndDetails_bPompHersteld( true, false, false );
		for (MainTicket mainTicket : mainTicketListTeDemonternen) {
			List<DetailTicket> detailTicketListFiltered = mainTicket.getDetails().stream().filter( filter -> (getPompStatus( filter ).toString().equals( "NIETGESTART" )) && (filter.getamNummer().contains( "AM" )) ).collect( Collectors.toList() );
			detailTicketListFiltered.stream().forEach( x -> x.setOpdrachtgever( mainTicket.getOpdrachtgever().getBedrijfsNaam() ) );
			detailTicketListTeDemonteren.addAll( detailTicketListFiltered );
		}

		return detailTicketListTeDemonteren;

		//aantalTeDemonterenBuitenTijd = (int)detailTicketListTeDemonteren.stream().filter( filter -> filter.getDetailAanmaakDatum().isBefore( LocalDateTime.now().minusDays( 3 ) ) ).count();
		//aantalTeDemonterenBinnenTijd = detailTicketListTeDemonteren.size() - aantalTeDemonterenBuitenTijd;
	}

	public List<DetailTicket> getAllDetailsToVoorbereiding() {
		detailTicketListVoorTeBereiden.clear();
		List<MainTicket> mainTicketList = mainTicketRepository.findByDetails_bHerstelBestekAfgewerktAndDetails_bHerstellingBestek( false, true );
		for (MainTicket mainTicket : mainTicketList) {
			for (DetailTicket detailTicket : mainTicket.getDetails()) {
				if (detailTicket.isbHerstellingBestek() && detailTicket.isbAkkoordKlant() && !detailTicket.isbHerstelBestekAfgewerkt() && !detailTicket.isOpdrachtAfgewerkt()) {
					try {
						if ((detailTicket.getCheckListBestek().isHerschilderen() && (detailTicket.getCheckListBestek().isPompMWGespoten()) && (!detailTicket.isbAlleOnderdelenBinnen())) ||
								(detailTicket.getCheckListBestek().isZandstralenEnHerschilderen() && (detailTicket.getCheckListBestek().isPompMWGezandstraald()) && (!detailTicket.isbAlleOnderdelenBinnen())) ||
								(detailTicket.getCheckListBestek().isHerschilderen() && (!detailTicket.getCheckListBestek().isPompMWGespoten())) ||
								(detailTicket.getCheckListBestek().isZandstralenEnHerschilderen() && (!detailTicket.getCheckListBestek().isPompMWGezandstraald())) ||
								(!detailTicket.isbAlleOnderdelenBinnen() && (!detailTicket.getCheckListBestek().isHerschilderen()) && (!detailTicket.getCheckListBestek().isZandstralenEnHerschilderen()))) {
							detailTicketListVoorTeBereiden.stream().forEach( x -> x.setOpdrachtgever( mainTicket.getOpdrachtgever().getBedrijfsNaam() ) );
							detailTicketListVoorTeBereiden.add( detailTicket );
						}
					} catch (Exception e) {

					}
				}
			}
		}
		return detailTicketListVoorTeBereiden;
	}

	public List<DetailTicket> getAllDetailsToInox() {

		inoxEntityList.clear();
		List<MainTicket> mainTicketList = mainTicketRepository.findByDetails_OpdrachtAfgewerktAndDetails_bProject( false, true );
		for (MainTicket mainTicket : mainTicketList) {
			for(DetailTicket detailTicket : mainTicket.getDetails()){
				if((detailTicket.getamNummer().startsWith("IN"))&&(detailTicket.isOpdrachtAfgewerkt() == false)){
					detailTicket.setOpdrachtgever(mainTicket.getOpdrachtgever().getBedrijfsNaam());
					detailTicket.setInterneOpmerkingen(detailTicket.getInterneOpmerkingen()+ " " + mainTicket.getVraagKlant());
					inoxEntityList.add(detailTicket);
				}
			}
		}
		return inoxEntityList;

	}
}
