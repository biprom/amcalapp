package com.biprom.bram.backend;

import com.biprom.bram.backend.data.entity.mongodbEntities.MainTicket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface MainTicketRepository extends MongoRepository<MainTicket, String> {

        Long removeByticketNummer(String ticketNummer);

        MainTicket findByTicketNummer(String ticketNummer);

        List<MainTicket> findByAanvraagDatumTicketBefore(LocalDateTime time);
        List<MainTicket> findByEindKlantBedrijfsNaamContainsIgnoreCase(String eindkl);
        List<MainTicket> findByOpdrachtgeverBedrijfsNaamContainsIgnoreCase(String eindkl);
        List<MainTicket> findByDetails_AmNummerContains(String amNummer);
        List<MainTicket> findByDetails_BInterventie(String amNummer);
        List<MainTicket> findByDetails_BNieuweOfferte(String amNummer);
        List<MainTicket> findByDetails_BInterventieAndDetails_BLockedAndDetails_OpdrachtAfgewerkt(boolean interventie, boolean locked, boolean opdrachtAfgewerkt);
        List<MainTicket> findByDetails_BOnderhoudAndDetails_BLockedAndDetails_OpdrachtAfgewerkt(boolean onderhoud, boolean locked, boolean opdrachtAfgewerkt);


        //Techniekers
        List<MainTicket> findByDetails_BInterventieAndDetails_InplantingsDatum(Boolean interventie, LocalDate now);
        List<MainTicket> findByDetails_BInterventieAndDetails_InplantingsDatumAfter(boolean interventie, LocalDateTime now);
        List<MainTicket> findByDetails_InplantingsDatumBetween(LocalDate start, LocalDate stop);
        List<MainTicket> findByDetails_DetailAanmaakDatum(LocalDateTime moment);




        List<MainTicket> findByDetails_OnholdAndDetailsBInterventieAndDetails_OpdrachtAfgewerkt(boolean onhold, boolean interventie, boolean opdrachtAfgewerkt);
        List<MainTicket> findByDetails_OnholdAndDetailsBHerstellingBestek(boolean onhold, boolean herstellingBestek);


        List<MainTicket> findByDetails_OpdrachtAfgewerktAndDetailsBInterventie(boolean afgewerkt, boolean interventie);
        List<MainTicket> findByDetails_OpdrachtAfgewerktAndDetails_bProject(boolean afgewerkt, boolean project);
        List<MainTicket> findByDetails_bHerstelBestekAfgewerktAndDetails_bHerstellingBestek(boolean bestekAfgewerkt, boolean herstellingBestek);

        //Zoekopdracht voor urenregistratie
        List<MainTicket> findByDetails_CheckListBestek_GedemonteerdDoorList_InlogNaam(String inlognaam);

        //zoekopdracht voor view "afhalingen"
        List<MainTicket> findByDetails_BHerstellingBestekAndDetails_bHerstelBestekAfgewerktAndDetails_BPompAfgehaaldAndDetails_BAfhaling(boolean herstelBestek, boolean opdrachtAfgewerkt, boolean pompAfgehaald, boolean afhaling);

        List<MainTicket> findByDetailsBIngeplandAndDetailsBInterventieAndDetails_OpdrachtAfgewerkt(boolean ingepland, boolean interventie, boolean opdrachtAfgewerkt);
        List<MainTicket> findByDetailsBIngeplandAndDetailsBInterventieAndDetails_OpdrachtAfgewerktAndDetailsOnhold(boolean ingepland, boolean interventie, boolean afgewerkt, boolean onhold);
        List<MainTicket> findByDetails_VerderInTePlannenAndDetailsBHerstellingBestek(boolean verderInTePlannen, boolean herstellingBestek);

        List<MainTicket> findByDetails_OpdrachtAfgewerktAndDetailsBInterventieAndDetails_Onhold(boolean opdrachtAfgewerkt, boolean interventie, boolean onhold);
        List<MainTicket> findByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_Onhold(boolean opdrachtAfgewerkt, boolean herstel, boolean onhold);

        List<MainTicket> findByDetails_BInterventie(boolean interventie);
        List<MainTicket> findByDetails_BHerstellingBestek(boolean herstellingBestek);

        List<MainTicket> findByDetails_bHerstellingBestekAndDetails_bTeDemonterenAndDetails_bPompHersteld(boolean herstellingBestek, boolean gedemonteerd, boolean pompHersteld);
        List<MainTicket> findByDetails_BHerstellingBestekAndDetails_bAkkoordKlantAndDetails_bPompHersteldAndDetails_bAlleOnderdelenBinnen(boolean herstellingBestek, boolean bestellingGoedgekeurd, boolean pompHersteld, boolean alleOnderdelenBinnen);

        //grafiek Techniekers mainview
        Integer countByDetails_BHerstellingBestekAndDetails_bTeDemonterenAndDetails_bPompHersteldAndDetails_detailAanmaakDatumBefore(boolean herstellingBestek, boolean gedemonteerd, boolean pompHersteld, LocalDateTime datTimeNow);
        Integer countByDetails_BHerstellingBestekAndDetails_bTeDemonterenAndDetails_bPompHersteldAndDetails_detailAanmaakDatumAfter(boolean herstellingBestek, boolean gedemonteerd, boolean pompHersteld, LocalDateTime datTimeNow);
        Integer countByDetails_BHerstellingBestekAndDetails_bTeDemonterenAndDetails_bPompHersteldAndDetails_CheckListBestekUitersteHerstelDatumBefore(boolean herstellingBestek, boolean gedemonteerd, boolean pompHersteld, LocalDateTime datTimeNow);
        Integer countByDetails_BHerstellingBestekAndDetails_bTeDemonterenAndDetails_bPompHersteldAndDetails_CheckListBestekUitersteHerstelDatumAfter(boolean herstellingBestek, boolean gedemonteerd, boolean pompHersteld, LocalDateTime datTimeNow);

        List<MainTicket> findByDetails_BProject(boolean project);
        List<MainTicket> findByDetails_BNieuwePomp(boolean nieuwePomp);
        List<MainTicket> findByDetails_BNieuweOfferte(boolean nieuweOfferte);
        List<MainTicket> findByDetails_BNieuweOfferteAndAfgewerkt(boolean nieuweOfferte, boolean afgewerkt);
        List<MainTicket> findByDetails_BHerstelBestekAfgewerktAndDetails_BGoedgekeurdElineAndDetails_BWachtOpEline(boolean herstelBestekAfgewerkt, boolean goedgekeurdEline, boolean wachtOpEline);
        List<MainTicket> findByDetails_BOnderhoud(boolean onderhoud);
        List<MainTicket> findByDetails_BOnderhoudAndDetails_InplantingsDatumAfter(boolean onderhoud, LocalDateTime date);
        List<MainTicket> findByDetails_Benodigdheden_BProductBesteldAndDetails_Benodigdheden_BProductGeleverdAndDetails_OpdrachtAfgewerkt(boolean besteld, boolean geleverd, boolean afgwerkt);
        List<MainTicket> findByDetails_Benodigdheden_BProductBesteldAndDetails_OpdrachtAfgewerkt(boolean besteld, boolean afgewerkt);

        List<MainTicket> findByDetails_InplantingsDatumBetween(Date dateStart, Date dateEnd);

        //grafiek Interventies
        Integer countByDetailsBInterventieAndDetails_OpdrachtAfgewerkt(boolean interventie, boolean afgewerkt);
        Integer countByDetailsOnholdAndDetailsBInterventieAndDetails_OpdrachtAfgewerkt(boolean onhold, boolean interventie, boolean opdrachtAfgewerkt);
        Integer countByDetailsBVoorlopigIngeplandAndDetailsBInterventieAndDetails_OpdrachtAfgewerkt(boolean voorlopigIngepland, boolean interventie, boolean opdrachtAfgewerkt);
        Integer countByDetailsBIngeplandAndDetailsBInterventieAndDetails_OpdrachtAfgewerktAndDetailsOnhold(boolean ingepland, boolean interventie, boolean afgewerkt, boolean onhold);
        Integer countByDetailsBKlaarVoorInplanningAndDetailsBInterventieAndDetails_OpdrachtAfgewerkt(boolean klaarVoorInplanning, boolean interventie, boolean opdrachtAfgewerkt);
        Integer countByDetailsBIngeplandAndDetailsBInterventieAndDetails_OpdrachtAfgewerkt(boolean ingepland, boolean interventie, boolean opdrachtAfgewerkt);

        //grafiek herstellingen

        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bTeDemonterenAndDetails_bNieuwePomp(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean gedemonteerd, boolean nieuwePomp);
        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bBestekVerstuurdAndDetails_bWachtOpEline(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean bestekVerstuurd, boolean wachtOpEline);
        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bTeDemonterenAndDetails_bBestekVerstuurd(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean gedemonteerd, boolean bestekVerstuurd);
        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bAkkoordKlant(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean akkoordKlant);
        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bAlleOnderdelenBinnenAndDetails_bPompHersteld(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean alleOnderdelenBinnen, boolean pompHersteld);
        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bBestekVerstuurdAndDetails_bAkkoordKlant(boolean bestekAfgewerkt, boolean hestelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompVerschroten, boolean pompOnhersteldTerug, boolean bestekVerstuurd, boolean akkoordKlant);

        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bTeDemonterenAndDetails_detailAanmaakDatumBefore(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean gedemonteerd, LocalDateTime dateTime);
        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bWachtOpElineAndDetails_detailAanmaakDatumBefore(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean wachtOpEline, LocalDateTime dateTime);
        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bTeDemonterenAndDetails_bBestekVerstuurdAndDetails_detailAanmaakDatumBefore(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean gedemonteerd, boolean bestekVerstuurd, LocalDateTime dateTime);
        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bAkkoordKlantAndDetails_detailAanmaakDatumBefore(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean akkoordKlant, LocalDateTime dateTime);
        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bAlleOnderdelenBinnenAndDetails_bPompHersteldAndDetails_DtAkkoordKlant(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean alleOnderdelenBinnen, boolean pompHersteld, LocalDateTime dateTime);
        Integer countByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bBestekVerstuurdAndDetails_bAkkoordKlantAndDetails_detailAanmaakDatumBefore(boolean bestekAfgewerkt, boolean hestelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompVerschroten, boolean pompOnhersteldTerug, boolean bestekVerstuurd, boolean akkoordKlant, LocalDateTime dateTime);


        List<MainTicket> findByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bTeDemonteren(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean gedemonteerd);
        List<MainTicket> findByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bBestekVerstuurdAndDetails_bWachtOpEline(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean bestekVerstuurd, boolean wachtOpEline);
        List<MainTicket> findByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bGoedgekeurdEline(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean goedgekeurdEline);
        List<MainTicket> findByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bTeDemonterenAndDetails_bBestekVerstuurd(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean gedemonteerd, boolean bestekVerstuurd);
        List<MainTicket> findByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bAkkoordKlant(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean akkoordKlant);
        List<MainTicket> findByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bAlleOnderdelenBinnenAndDetails_bPompHersteld(boolean bestekAfgewerkt, boolean herstelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompTeVerschroten, boolean pompOnhersteldTerug, boolean alleOnderdelenBinnen, boolean pompHersteld);
        List<MainTicket> findByDetails_bHerstelBestekAfgewerktAndDetailsBHerstellingBestekAndDetails_bPompGeleverdAndDetails_bPompAfgehaaldAndDetails_bPompVerschrotenAndDetails_bPompOnhersteldTerugAndDetails_bBestekVerstuurdAndDetails_bAkkoordKlant(boolean bestekAfgewerkt, boolean hestelling, boolean pompGeleverd, boolean pompAfgehaald, boolean pompVerschroten, boolean pompOnhersteldTerug, boolean bestekVerstuurd, boolean akkoordKlant);

        //Facturatie
        List<MainTicket> findByDetails_Gefactureerd(boolean bGefactureerd);

        //urenregistratie
        List<MainTicket> findByDetails_WerkUren_StartDatumTijdBetween(LocalDate start, LocalDate stop);
        List<MainTicket> findByDetails_WerkUren_StartDatumTijdBetween(LocalDateTime start, LocalDateTime stop);
        List<MainTicket> findByDetails_WerkUren_StartDatumTijd(LocalDateTime start);
        List<MainTicket> findByDetails_WerkUren_StartDatumTijd(LocalDate date);

        //dashboard eline
         Integer countByDetailsBHerstellingBestekAndMerkAndAanvraagDatumTicketBetween(boolean herstelBestek, String merk, LocalDateTime start, LocalDateTime end);
        Integer countByDetailsBHerstellingBestekAndAanvraagDatumTicketBetween(boolean herstelBestek, LocalDateTime start, LocalDateTime end);


}