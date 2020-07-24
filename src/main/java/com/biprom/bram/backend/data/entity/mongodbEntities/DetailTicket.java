package com.biprom.bram.backend.data.entity.mongodbEntities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class DetailTicket implements Comparable, Cloneable {

        @org.springframework.data.annotation.Id
        @JsonIgnore
        private String Id;


        private String omschrijvingVraagKlacht= "";

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonIgnore
        private LocalDateTime detailAanmaakDatum = LocalDateTime.now();

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonIgnore
        private LocalDateTime productieDatumPomp = LocalDateTime.now();

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private LocalDateTime inplantingsDatum = LocalDateTime.now();

        private String stringInplantingsDatum;

        private Personeel personeel;

         private String aftekenaar;
         private String emailAftekenaar;

        private boolean bLocked;

        private boolean bTeDemonteren;
        private boolean bDirectTeHerstellen;
        private boolean bBestekTeMaken;
        private boolean bGoedgekeurdEline;
        private boolean bWachtOpLevernacier;
        private boolean bWachtOpEline;
        private boolean bWachtOpAntwoord;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonIgnore
        private LocalDateTime dtWachtOpAntwoord  = LocalDateTime.now();
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonIgnore
        private LocalDateTime dtStatusWeiziging = LocalDateTime.now();
         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

        private boolean bBestekVerstuurd;
        private boolean bRappel1;
        private boolean bRappel2;
        private boolean bRappel3;
        private boolean bAkkoordKlant;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonIgnore
        private LocalDateTime dtAkkoordKlant = LocalDateTime.now();
        private boolean bAndereOptieBesteld;
        private boolean bGeenBestelling;
        private boolean bLevering;
        private boolean bAfhaling;
        private boolean bBestellingDoorgevoerd;
        private boolean bAlleOnderdelenBinnen;
        private boolean bWachtOpOnderdelen;
        private boolean bPompHersteld ;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonIgnore
        private LocalDateTime dtPompHersteld = LocalDateTime.now();

        @JsonIgnore
        private String geschatAantalWerkuren;
        private boolean bPompGeleverd;
        private boolean bPompAfgehaald;
        private boolean bPompVerschroten;
        private boolean bPompIsVerschroot;
        private boolean bPompOnhersteldTerug;
        private boolean bHerstelBestekAfgewerkt;

        private boolean bPlaatsMaterialenOpWerkbon = true;
        private String pomp;
        private String factuurNummer;
        private String annulatieReden = " ";
        private boolean annulatie;

        private String herinnering;
        private String aantalTechniekers;
        private boolean onhold;
        private String externReferentieNummer;
        private String linkAgendag;

        private ArrayList<Product> benodigdheden;

        private List<String> pictureList;
        private List<String> pdfList;
        private Personeel toegewezenPersoneel;
        private Set<Personeel> toegewezenTechniekers;
        private ArrayList<WerkUren> werkUren;
        List<StringUren> stringUrenList;

        private boolean bGarantie;
        private String stringGarantie;
        private boolean bVervolgzaamheden;
        private String stringVervolgzaamheden;
        private boolean bOfferteOpTeMaken;
        private String stringOfferteOpTeMaken;
        private String internCommentaarTechnieker;
        private String commentaarEindklant;
        @JsonIgnore
        private String eigenCommentaar;

        @JsonIgnore
        private CheckListBestek checkListBestek;


        private String vaststellingTechnieker = " ";
        private String interneOpmerkingen;

        private String amNummer = "";
        //private String AmNummer = "";
        private String commentaarOpties;


        private boolean bInterventie;
        private boolean bHerstellingBestek;
        private boolean bProject;
        private boolean bNieuwePomp;
        private boolean bNieuweOfferte;
        private boolean bOnderhoud;
        @JsonIgnore
        private boolean bKlantenBezoek;

        @JsonIgnore
        private boolean contractGoedgekeurd;


        private boolean bNtIngepland;
        private boolean bKlaarVoorInplanning;
        private boolean bIngepland;
        private boolean bVoorlopigIngepland;

        private boolean opdrachtAfgewerkt;
        private boolean tussentijdseFacturatieMogelijk;
        private boolean doorgegevenFacturatie;
        private boolean verderInTePlannen;
        private boolean gefactureerd;
        private String factuurBedrag;

        private String opdrachtgever;
        private String interventieAdres;

        boolean onderhoudUitgevoerd;
        LocalDate dateUitgevoerd;
        LocalDate dateUitTeVoeren;
        Personeel technieker;
        String linkExternProgramma;


        @JsonIgnore
        private LocalDateTime dtOfferteEindDatum = LocalDateTime.now();

        @JsonIgnore
        private LocalDateTime dtAttentieVolgendeStatus = LocalDateTime.now();

        @JsonIgnore
        private boolean checkIn;
        @JsonIgnore
        private boolean geenCheckIn;
        @JsonIgnore
        private boolean werfregistratie;
        @JsonIgnore
        private boolean geenWerfRegistratie;
        @JsonIgnore
        private LocalDate deadLine;
        @JsonIgnore
        private String title;
        @JsonIgnore
        private String laatsteStatus = "";




    public boolean isbBestekTeMaken() {
        return bBestekTeMaken;
    }

    public void setbBestekTeMaken(boolean bBestekTeMaken) {
        this.bBestekTeMaken = bBestekTeMaken;
    }

    public String getExternReferentieNummer() {
        return externReferentieNummer;
    }

    public void setExternReferentieNummer(String externReferentieNummer) {
        this.externReferentieNummer = externReferentieNummer;
    }
    public boolean isbNtIngepland() {
        return bNtIngepland;
    }

    public void setbNtIngepland(boolean bNtIngepland) {
        this.bNtIngepland = bNtIngepland;
    }

    public boolean isbKlaarVoorInplanning() {
        return bKlaarVoorInplanning;
    }

    public void setbKlaarVoorInplanning(boolean bKlaarVoorInplanning) {
        this.bKlaarVoorInplanning = bKlaarVoorInplanning;
    }

    public boolean isbIngepland() {
        return bIngepland;
    }

    public void setbIngepland(boolean bIngepland) {
        this.bIngepland = bIngepland;
    }


    public void setToegewezenTechniekers(Set<Personeel> toegewezenTechniekers) {
        this.toegewezenTechniekers = toegewezenTechniekers;
    }

    public LocalDateTime getInplantingsDatum() {
        return inplantingsDatum;
    }

    public void setInplantingsDatum(LocalDateTime inplantingsDatum) {
        this.inplantingsDatum = inplantingsDatum;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getOmschrijvingVraagKlacht() {
        return omschrijvingVraagKlacht;
    }

    public void setOmschrijvingVraagKlacht(String omschrijvingVraagKlacht) {
        this.omschrijvingVraagKlacht = omschrijvingVraagKlacht;
    }

    public LocalDateTime getDetailAanmaakDatum() {
        return detailAanmaakDatum;
    }

    public void setDetailAanmaakDatum(LocalDateTime detailAanmaakDatum) {
        this.detailAanmaakDatum = detailAanmaakDatum;
    }

    public LocalDateTime getProductieDatumPomp() {
        return productieDatumPomp;
    }

    public void setProductieDatumPomp(LocalDateTime productieDatumPomp) {
        this.productieDatumPomp = productieDatumPomp;
    }

    public String getPomp() {
        return pomp;
    }

    public void setPomp(String pomp) {
        this.pomp = pomp;
    }

    public ArrayList<Product> getBenodigdheden() {
        return benodigdheden;
    }

    public void setBenodigdheden(ArrayList<Product> benodigdheden) {
        this.benodigdheden = benodigdheden;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }


    public String getVaststellingTechnieker() {
        return vaststellingTechnieker;
    }

    public void setVaststellingTechnieker(String vaststellingTechnieker) {
        this.vaststellingTechnieker = vaststellingTechnieker;
    }

    public String getInterneOpmerkingen() {
        return interneOpmerkingen;
    }

    public void setInterneOpmerkingen(String interneOpmerkingen) {
        this.interneOpmerkingen = interneOpmerkingen;
    }

    @JsonIgnore
    public ArrayList<WerkUren> getWerkUren() {
        return werkUren;
    }

    public void setWerkUren(ArrayList<WerkUren> werkUren) {
        this.werkUren = werkUren;
    }

    public boolean isbInterventie() {
        return bInterventie;
    }

    public void setbInterventie(boolean bInterventie) {
        this.bInterventie = bInterventie;
    }

    public boolean isbHerstellingBestek() {
        return bHerstellingBestek;
    }

    public void setbHerstellingBestek(boolean bHerstellingBestek) {
        this.bHerstellingBestek = bHerstellingBestek;
    }

    public boolean isbProject() {
        return bProject;
    }

    public void setbProject(boolean bProject) {
        this.bProject = bProject;
    }

    public boolean isbNieuwePomp() {
        return bNieuwePomp;
    }

    public void setbNieuwePomp(boolean bNieuwePomp) {
        this.bNieuwePomp = bNieuwePomp;
    }

    public boolean isOpdrachtAfgewerkt() {
        return opdrachtAfgewerkt;
    }

    public void setOpdrachtAfgewerkt(boolean opdrachtAfgewerkt) {
        this.opdrachtAfgewerkt = opdrachtAfgewerkt;
    }

    public boolean isTussentijdseFacturatieMogelijk() {
        return tussentijdseFacturatieMogelijk;
    }

    public void setTussentijdseFacturatieMogelijk(boolean tussentijdseFacturatieMogelijk) {
        this.tussentijdseFacturatieMogelijk = tussentijdseFacturatieMogelijk;
    }

    public boolean isVerderInTePlannen() {
        return verderInTePlannen;
    }

    public void setVerderInTePlannen(boolean verderInTePlannen) {
        this.verderInTePlannen = verderInTePlannen;
    }

    public List<String> getPdfList() {
        return pdfList;
    }

    public void setPdfList(List<String> pdfList) {
        this.pdfList = pdfList;
    }

    public boolean isbOnderhoud() {
        return bOnderhoud;
    }

    public void setbOnderhoud(boolean bOnderhoud) {
        this.bOnderhoud = bOnderhoud;
    }

    public CheckListBestek getCheckListBestek() {
        return checkListBestek;
    }

    public void setCheckListBestek(CheckListBestek checkListBestek) {
        this.checkListBestek = checkListBestek;
    }

    public String getHerinnering() {
        return herinnering;
    }

    public void setHerinnering(String herinnering) {
        this.herinnering = herinnering;
    }

    public Personeel getToegewezenPersoneel() {
        return toegewezenPersoneel;
    }

    public void setToegewezenPersoneel(Personeel toegewezenPersoneel) {
        this.toegewezenPersoneel = toegewezenPersoneel;
    }

    public boolean isOnhold() {
        return onhold;
    }

    public void setOnhold(boolean onhold) {
        this.onhold = onhold;
    }

//    public String getAmNummer() {
//        return AmNummer;
//    }
//
//    public void setAmNummer(String AmNummer) {
//        this.AmNummer = AmNummer;
//    }

    public String getamNummer() {
        return amNummer;
    }

    public void setamNummer(String amNummer) {
        this.amNummer = amNummer;
    }

    public String getLinkAgendag() {
        return linkAgendag;
    }

    public void setLinkAgendag(String linkAgendag) {
        this.linkAgendag = linkAgendag;
    }

    public String getFactuurNummer() {
        return factuurNummer;
    }

    public void setFactuurNummer(String factuurNummer) {
        this.factuurNummer = factuurNummer;
    }

    public String getAnnulatieReden() {
        return annulatieReden;
    }

    public void setAnnulatieReden(String annulatieReden) {
        this.annulatieReden = annulatieReden;
    }

    public boolean isAnnulatie() {
        return annulatie;
    }

    public void setAnnulatie(boolean annulatie) {
        this.annulatie = annulatie;
    }

    public boolean isbTeDemonteren() {
        return bTeDemonteren;
    }

    public void setbTeDemonteren(boolean bTeDemonteren) {
        this.bTeDemonteren = bTeDemonteren;
    }

    public boolean isbGoedgekeurdEline() {
        return bGoedgekeurdEline;
    }

    public void setbGoedgekeurdEline(boolean bGoedgekeurdEline) {
        this.bGoedgekeurdEline = bGoedgekeurdEline;
    }

    public boolean isbWachtOpAntwoord() {
        return bWachtOpAntwoord;
    }

    public void setbWachtOpAntwoord(boolean bWachtOpAntwoord) {
        this.bWachtOpAntwoord = bWachtOpAntwoord;
    }

    public LocalDateTime getDtWachtOpAntwoord() {
        return dtWachtOpAntwoord;
    }

    public void setDtWachtOpAntwoord(LocalDateTime dtWachtOpAntwoord) {
        this.dtWachtOpAntwoord = dtWachtOpAntwoord;
    }

    public boolean isbRappel1() {
        return bRappel1;
    }

    public void setbRappel1(boolean bRappel1) {
        this.bRappel1 = bRappel1;
    }

    public boolean isbRappel2() {
        return bRappel2;
    }

    public void setbRappel2(boolean bRappel2) {
        this.bRappel2 = bRappel2;
    }

    public boolean isbRappel3() {
        return bRappel3;
    }

    public void setbRappel3(boolean bRappel3) {
        this.bRappel3 = bRappel3;
    }

    public boolean isbLevering() {
        return bLevering;
    }

    public void setbLevering(boolean bLevering) {
        this.bLevering = bLevering;
    }

    public boolean isbAfhaling() {
        return bAfhaling;
    }

    public void setbAfhaling(boolean bAfhaling) {
        this.bAfhaling = bAfhaling;
    }

    public boolean isbWachtOpOnderdelen() {
        return bWachtOpOnderdelen;
    }

    public void setbWachtOpOnderdelen(boolean bWachtOpOnderdelen) {
        this.bWachtOpOnderdelen = bWachtOpOnderdelen;
    }

    public boolean isbPompHersteld() {
        return bPompHersteld;
    }

    public void setbPompHersteld(boolean bPompHersteld) {
        this.bPompHersteld = bPompHersteld;
    }

    public LocalDateTime getDtPompHersteld() {
        return dtPompHersteld;
    }

    public void setDtPompHersteld(LocalDateTime dtPompHersteld) {
        this.dtPompHersteld = dtPompHersteld;
    }

    public boolean isbPompAfgehaald() {
        return bPompAfgehaald;
    }

    public void setbPompAfgehaald(boolean bPompAfgehaald) {
        this.bPompAfgehaald = bPompAfgehaald;
    }

    public boolean isbPompVerschroten() {
        return bPompVerschroten;
    }

    public void setbPompVerschroten(boolean bPompVerschroten) {
        this.bPompVerschroten = bPompVerschroten;
    }

    public boolean isbPompOnhersteldTerug() {
        return bPompOnhersteldTerug;
    }

    public void setbPompOnhersteldTerug(boolean bPompOnhersteldTerug) {
        this.bPompOnhersteldTerug = bPompOnhersteldTerug;
    }

    public boolean isbWachtOpEline() {
        return bWachtOpEline;
    }

    public void setbWachtOpEline(boolean bWachtOpEline) {
        this.bWachtOpEline = bWachtOpEline;
    }

    public boolean isbAkkoordKlant() {
        return bAkkoordKlant;
    }

    public void setbAkkoordKlant(boolean bAkkoordKlant) {
        this.bAkkoordKlant = bAkkoordKlant;
    }

    public boolean isbAndereOptieBesteld() {
        return bAndereOptieBesteld;
    }

    public void setbAndereOptieBesteld(boolean bAndereOptieBesteld) {
        this.bAndereOptieBesteld = bAndereOptieBesteld;
    }

    public boolean isbGeenBestelling() {
        return bGeenBestelling;
    }

    public void setbGeenBestelling(boolean bGeenBestelling) {
        this.bGeenBestelling = bGeenBestelling;
    }

    public boolean isbBestellingDoorgevoerd() {
        return bBestellingDoorgevoerd;
    }

    public void setbBestellingDoorgevoerd(boolean bBestellingDoorgevoerd) {
        this.bBestellingDoorgevoerd = bBestellingDoorgevoerd;
    }

    public boolean isbAlleOnderdelenBinnen() {
        return bAlleOnderdelenBinnen;
    }

    public void setbAlleOnderdelenBinnen(boolean bAlleOnderdelenBinnen) {
        this.bAlleOnderdelenBinnen = bAlleOnderdelenBinnen;
    }

    public boolean isbPompGeleverd() {
        return bPompGeleverd;
    }

    public void setbPompGeleverd(boolean bPompGeleverd) {
        this.bPompGeleverd = bPompGeleverd;
    }

    public boolean isbWachtOpLevernacier() {
        return bWachtOpLevernacier;
    }

    public void setbWachtOpLevernacier(boolean bWachtOpLevernacier) {
        this.bWachtOpLevernacier = bWachtOpLevernacier;
    }

    public boolean isGefactureerd() {
        return gefactureerd;
    }

    public void setGefactureerd(boolean gefactureerd) {
        this.gefactureerd = gefactureerd;
    }

    public boolean isbBestekVerstuurd() {
        return bBestekVerstuurd;
    }

    public void setbBestekVerstuurd(boolean bBestekVerstuurd) {
        this.bBestekVerstuurd = bBestekVerstuurd;
    }

    public boolean isbVoorlopigIngepland() {
        return bVoorlopigIngepland;
    }

    public void setbVoorlopigIngepland(boolean bVoorlopigIngepland) {
        this.bVoorlopigIngepland = bVoorlopigIngepland;
    }

    public LocalDateTime getDtAkkoordKlant() {
        return dtAkkoordKlant;
    }

    public void setDtAkkoordKlant(LocalDateTime dtAkkoordKlant) {
        this.dtAkkoordKlant = dtAkkoordKlant;
    }

    public boolean isbHerstelBestekAfgewerkt() {
        return bHerstelBestekAfgewerkt;
    }

    public void setbHerstelBestekAfgewerkt(boolean bHerstelBestekAfgewerkt) {
        this.bHerstelBestekAfgewerkt = bHerstelBestekAfgewerkt;
    }

    public String getCommentaarOpties() {
        return commentaarOpties;
    }

    public void setCommentaarOpties(String commentaarOpties) {
        this.commentaarOpties = commentaarOpties;
    }

    public LocalDateTime getDtStatusWeiziging() {
        return dtStatusWeiziging;
    }

    public void setDtStatusWijziging(LocalDateTime dtStatusWeiziging) {
        this.dtStatusWeiziging = dtStatusWeiziging;
    }

    public boolean isbDirectTeHerstellen() {
        return bDirectTeHerstellen;
    }

    public void setbDirectTeHerstellen(boolean bDirectTeHerstellen) {
        this.bDirectTeHerstellen = bDirectTeHerstellen;
    }

    public String getFactuurBedrag() {
        return factuurBedrag;
    }

    public void setFactuurBedrag(String factuurBedrag) {
        this.factuurBedrag = factuurBedrag;
    }

    public String getAantalTechniekers() {
        return aantalTechniekers;
    }

    public void setAantalTechniekers(String aantalTechniekers) {
        this.aantalTechniekers = aantalTechniekers;
    }

    public boolean isDoorgegevenFacturatie() {
        return doorgegevenFacturatie;
    }

    public void setDoorgegevenFacturatie(boolean doorgegevenFacturatie) {
        this.doorgegevenFacturatie = doorgegevenFacturatie;
    }

    public String getOpdrachtgever() {
        return opdrachtgever;
    }

    public void setOpdrachtgever(String opdrachtgever) {
        this.opdrachtgever = opdrachtgever;
    }

    public String getInterventieAdres() {
        return interventieAdres;
    }

    public void setInterventieAdres(String interventieAdres) {
        this.interventieAdres = interventieAdres;
    }

    public LocalDateTime getDtOfferteEindDatum() {
        return dtOfferteEindDatum;
    }

    public void setDtOfferteEindDatum(LocalDateTime dtOfferteEindDatum) {
        this.dtOfferteEindDatum = dtOfferteEindDatum;
    }

    public boolean isContractGoedgekeurd() {
        return contractGoedgekeurd;
    }

    public void setContractGoedgekeurd(boolean contractGoedgekeurd) {
        this.contractGoedgekeurd = contractGoedgekeurd;
    }

    public String getStringInplantingsDatum() {
        return stringInplantingsDatum;
    }

    public void setStringInplantingsDatum(String stringInplantingsDatum) {
        this.stringInplantingsDatum = stringInplantingsDatum;
    }

    public boolean isbPlaatsMaterialenOpWerkbon() {
        return bPlaatsMaterialenOpWerkbon;
    }

    public void setbPlaatsMaterialenOpWerkbon(boolean bPlaatsMaterialenOpWerkbon) {
        this.bPlaatsMaterialenOpWerkbon = bPlaatsMaterialenOpWerkbon;
    }

    public boolean isbGarantie() {
        return bGarantie;
    }

    public void setbGarantie(boolean bGarantie) {
        this.bGarantie = bGarantie;
    }

    public String getStringGarantie() {
        return stringGarantie;
    }

    public void setStringGarantie(String stringGarantie) {
        this.stringGarantie = stringGarantie;
    }

    public boolean isbVervolgzaamheden() {
        return bVervolgzaamheden;
    }

    public void setbVervolgzaamheden(boolean bVervolgzaamheden) {
        this.bVervolgzaamheden = bVervolgzaamheden;
    }

    public String getStringVervolgzaamheden() {
        return stringVervolgzaamheden;
    }

    public void setStringVervolgzaamheden(String stringVervolgzaamheden) {
        this.stringVervolgzaamheden = stringVervolgzaamheden;
    }

    public boolean isbOfferteOpTeMaken() {
        return bOfferteOpTeMaken;
    }

    public void setbOfferteOpTeMaken(boolean bOfferteOpTeMaken) {
        this.bOfferteOpTeMaken = bOfferteOpTeMaken;
    }

    public String getStringOfferteOpTeMaken() {
        return stringOfferteOpTeMaken;
    }

    public void setStringOfferteOpTeMaken(String stringOfferteOpTeMaken) {
        this.stringOfferteOpTeMaken = stringOfferteOpTeMaken;
    }

    public String getInternCommentaarTechnieker() {
        return internCommentaarTechnieker;
    }

    public void setInternCommentaarTechnieker(String internCommentaarTechnieker) {
        this.internCommentaarTechnieker = internCommentaarTechnieker;
    }

    public String getCommentaarEindklant() {
        return commentaarEindklant;
    }

    public void setCommentaarEindklant(String commentaarEindklant) {
        this.commentaarEindklant = commentaarEindklant;
    }

    public boolean isbLocked() {
        return bLocked;
    }

    public void setbLocked(boolean bLocked) {
        this.bLocked = bLocked;
    }

    public List<StringUren> getStringUrenList() {
        return stringUrenList;
    }

    public void setStringUrenList(List<StringUren> stringUrenList) {
        this.stringUrenList = stringUrenList;
    }

    public String getAftekenaar() {
        return aftekenaar;
    }

    public void setAftekenaar(String aftekenaar) {
        this.aftekenaar = aftekenaar;
    }

    public String getEmailAftekenaar() {
        return emailAftekenaar;
    }

    public void setEmailAftekenaar(String emailAftekenaar) {
        this.emailAftekenaar = emailAftekenaar;
    }

    public boolean isbNieuweOfferte() {
        return bNieuweOfferte;
    }

    public void setbNieuweOfferte(boolean bNieuweOfferte) {
        this.bNieuweOfferte = bNieuweOfferte;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public Personeel getPersoneel() {
        return personeel;
    }

    public void setPersoneel(Personeel personeel) {
        this.personeel = personeel;
    }

    public LocalDateTime getDtAttentieVolgendeStatus() {
        return dtAttentieVolgendeStatus;
    }

    public void setDtAttentieVolgendeStatus(LocalDateTime dtAttentieVolgendeStatus) {
        this.dtAttentieVolgendeStatus = dtAttentieVolgendeStatus;
    }

    @Override
    public int compareTo(Object o) {
        return this.getamNummer().compareTo(((DetailTicket) o).getamNummer());
    }

    public boolean isGeenCheckIn() {
        return geenCheckIn;
    }

    public void setGeenCheckIn(boolean geenCheckIn) {
        this.geenCheckIn = geenCheckIn;
    }

    public boolean isWerfregistratie() {
        return werfregistratie;
    }

    public void setWerfregistratie(boolean werfregistratie) {
        this.werfregistratie = werfregistratie;
    }

    public boolean isGeenWerfRegistratie() {
        return geenWerfRegistratie;
    }

    public void setGeenWerfRegistratie(boolean geenWerfRegistratie) {
        this.geenWerfRegistratie = geenWerfRegistratie;
    }

    public boolean isbKlantenBezoek() {
        return bKlantenBezoek;
    }

    public void setbKlantenBezoek(boolean bKlantenBezoek) {
        this.bKlantenBezoek = bKlantenBezoek;
    }

    public boolean isOnderhoudUitgevoerd() {
        return onderhoudUitgevoerd;
    }

    public void setOnderhoudUitgevoerd(boolean onderhoudUitgevoerd) {
        this.onderhoudUitgevoerd = onderhoudUitgevoerd;
    }

    public LocalDate getDateUitgevoerd() {
        return dateUitgevoerd;
    }

    public void setDateUitgevoerd(LocalDate dateUitgevoerd) {
        this.dateUitgevoerd = dateUitgevoerd;
    }

    public LocalDate getDateUitTeVoeren() {
        return dateUitTeVoeren;
    }

    public void setDateUitTeVoeren(LocalDate dateUitTeVoeren) {
        this.dateUitTeVoeren = dateUitTeVoeren;
    }

    public Personeel getTechnieker() {
        return technieker;
    }

    public void setTechnieker(Personeel technieker) {
        this.technieker = technieker;
    }

    public String getLinkExternProgramma() {
        return linkExternProgramma;
    }

    public void setLinkExternProgramma(String linkExternProgramma) {
        this.linkExternProgramma = linkExternProgramma;
    }

    public Set<Personeel> getToegewezenTechniekers() {
        return toegewezenTechniekers;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getGeschatAantalWerkuren() {
        return geschatAantalWerkuren;
    }

    public void setGeschatAantalWerkuren(String geschatAantalWerkuren) {
        this.geschatAantalWerkuren = geschatAantalWerkuren;
    }

    public boolean isbPompIsVerschroot() {
        return bPompIsVerschroot;
    }

    public void setbPompIsVerschroot(boolean bPompIsVerschroot) {
        this.bPompIsVerschroot = bPompIsVerschroot;
    }

    public String getEigenCommentaar() {
        return eigenCommentaar;
    }

    public void setEigenCommentaar(String eigenCommentaar) {
        this.eigenCommentaar = eigenCommentaar;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLaatsteStatus() {
        return laatsteStatus;
    }

    public void setLaatsteStatus(String laatsteStatus) {
        this.laatsteStatus = laatsteStatus;
    }
}


