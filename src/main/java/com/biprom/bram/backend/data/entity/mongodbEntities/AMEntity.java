package com.biprom.bram.backend.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AMEntity {


    @org.springframework.data.annotation.Id
    @JsonIgnore
    private String Id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonIgnore
    private LocalDateTime detailAanmaakDatum;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonIgnore
    private LocalDateTime productieDatumPomp;

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
    private LocalDateTime dtWachtOpAntwoord;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonIgnore
    private LocalDateTime dtStatusWeiziging;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonIgnore
    private LocalDateTime dtOfferteEindDatum;
    private boolean bBestekVerstuurd;
    private boolean bRappel1;
    private boolean bRappel2;
    private boolean bRappel3;
    private boolean bAkkoordKlant;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonIgnore
    private LocalDateTime dtAkkoordKlant;
    private boolean bAndereOptieBesteld;
    private boolean bGeenBestelling;
    private boolean bLevering;
    private boolean bAfhaling;
    private boolean bBestellingDoorgevoerd;
    private boolean bAlleOnderdelenBinnen;
    private boolean bWachtOpOnderdelen;
    private boolean bPompHersteld;
    private boolean opdrachtAfgewerkt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonIgnore
    private LocalDateTime dtPompHersteld;
    private boolean bPompGeleverd;
    private boolean bPompAfgehaald;
    private boolean bPompVerschroten;
    private boolean bPompOnhersteldTerug;
    private boolean bHerstelBestekAfgewerkt;

    private String pomp;
    private String factuurNummer;
    private String annulatieReden;
    private boolean annulatie;

    private String herinnering;

    private ArrayList<Product> benodigdheden;

    private List<String> pictureList;
    private List<String> pdfList;
    private Personeel toegewezenPersoneel;

    private String omschrijvingVraagKlacht;

    @JsonIgnore
    private CheckListBestek checkListBestek;


    private String amNummer;
    private String commentaarOpties;
    private String externeReferentie;


    private boolean bHerstellingBestek;

    @JsonIgnore
    private boolean contractGoedgekeurd;


    private boolean gefactureerd;
    private boolean gedeeltelijkTeFactureren;
    private String factuurBedrag;

    private String opdrachtgever;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public boolean isbLocked() {
        return bLocked;
    }

    public void setbLocked(boolean bLocked) {
        this.bLocked = bLocked;
    }

    public boolean isbTeDemonteren() {
        return bTeDemonteren;
    }

    public void setbTeDemonteren(boolean bTeDemonteren) {
        this.bTeDemonteren = bTeDemonteren;
    }

    public boolean isbDirectTeHerstellen() {
        return bDirectTeHerstellen;
    }

    public void setbDirectTeHerstellen(boolean bDirectTeHerstellen) {
        this.bDirectTeHerstellen = bDirectTeHerstellen;
    }

    public boolean isbBestekTeMaken() {
        return bBestekTeMaken;
    }

    public void setbBestekTeMaken(boolean bBestekTeMaken) {
        this.bBestekTeMaken = bBestekTeMaken;
    }

    public boolean isbGoedgekeurdEline() {
        return bGoedgekeurdEline;
    }

    public void setbGoedgekeurdEline(boolean bGoedgekeurdEline) {
        this.bGoedgekeurdEline = bGoedgekeurdEline;
    }

    public boolean isbWachtOpLevernacier() {
        return bWachtOpLevernacier;
    }

    public void setbWachtOpLevernacier(boolean bWachtOpLevernacier) {
        this.bWachtOpLevernacier = bWachtOpLevernacier;
    }

    public boolean isbWachtOpEline() {
        return bWachtOpEline;
    }

    public void setbWachtOpEline(boolean bWachtOpEline) {
        this.bWachtOpEline = bWachtOpEline;
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

    public LocalDateTime getDtStatusWeiziging() {
        return dtStatusWeiziging;
    }

    public void setDtStatusWeiziging(LocalDateTime dtStatusWeiziging) {
        this.dtStatusWeiziging = dtStatusWeiziging;
    }

    public LocalDateTime getDtOfferteEindDatum() {
        return dtOfferteEindDatum;
    }

    public void setDtOfferteEindDatum(LocalDateTime dtOfferteEindDatum) {
        this.dtOfferteEindDatum = dtOfferteEindDatum;
    }

    public boolean isbBestekVerstuurd() {
        return bBestekVerstuurd;
    }

    public void setbBestekVerstuurd(boolean bBestekVerstuurd) {
        this.bBestekVerstuurd = bBestekVerstuurd;
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

    public boolean isbAkkoordKlant() {
        return bAkkoordKlant;
    }

    public void setbAkkoordKlant(boolean bAkkoordKlant) {
        this.bAkkoordKlant = bAkkoordKlant;
    }

    public LocalDateTime getDtAkkoordKlant() {
        return dtAkkoordKlant;
    }

    public void setDtAkkoordKlant(LocalDateTime dtAkkoordKlant) {
        this.dtAkkoordKlant = dtAkkoordKlant;
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

    public boolean isbPompGeleverd() {
        return bPompGeleverd;
    }

    public void setbPompGeleverd(boolean bPompGeleverd) {
        this.bPompGeleverd = bPompGeleverd;
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

    public boolean isbHerstelBestekAfgewerkt() {
        return bHerstelBestekAfgewerkt;
    }

    public void setbHerstelBestekAfgewerkt(boolean bHerstelBestekAfgewerkt) {
        this.bHerstelBestekAfgewerkt = bHerstelBestekAfgewerkt;
    }

    public String getPomp() {
        return pomp;
    }

    public void setPomp(String pomp) {
        this.pomp = pomp;
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

    public String getHerinnering() {
        return herinnering;
    }

    public void setHerinnering(String herinnering) {
        this.herinnering = herinnering;
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

    public List<String> getPdfList() {
        return pdfList;
    }

    public void setPdfList(List<String> pdfList) {
        this.pdfList = pdfList;
    }

    public Personeel getToegewezenPersoneel() {
        return toegewezenPersoneel;
    }

    public void setToegewezenPersoneel(Personeel toegewezenPersoneel) {
        this.toegewezenPersoneel = toegewezenPersoneel;
    }

    public CheckListBestek getCheckListBestek() {
        return checkListBestek;
    }

    public void setCheckListBestek(CheckListBestek checkListBestek) {
        this.checkListBestek = checkListBestek;
    }

    public String getAmNummer() {
        return amNummer;
    }

    public void setAmNummer(String amNummer) {
        this.amNummer = amNummer;
    }

    public String getCommentaarOpties() {
        return commentaarOpties;
    }

    public void setCommentaarOpties(String commentaarOpties) {
        this.commentaarOpties = commentaarOpties;
    }

    public boolean isbHerstellingBestek() {
        return bHerstellingBestek;
    }

    public void setbHerstellingBestek(boolean bHerstellingBestek) {
        this.bHerstellingBestek = bHerstellingBestek;
    }

    public boolean isContractGoedgekeurd() {
        return contractGoedgekeurd;
    }

    public void setContractGoedgekeurd(boolean contractGoedgekeurd) {
        this.contractGoedgekeurd = contractGoedgekeurd;
    }

    public boolean isGefactureerd() {
        return gefactureerd;
    }

    public void setGefactureerd(boolean gefactureerd) {
        this.gefactureerd = gefactureerd;
    }

    public String getFactuurBedrag() {
        return factuurBedrag;
    }

    public void setFactuurBedrag(String factuurBedrag) {
        this.factuurBedrag = factuurBedrag;
    }

    public String getOpdrachtgever() {
        return opdrachtgever;
    }

    public void setOpdrachtgever(String opdrachtgever) {
        this.opdrachtgever = opdrachtgever;
    }

    public String getExterneReferentie() {
        return externeReferentie;
    }

    public void setExterneReferentie(String externeReferentie) {
        this.externeReferentie = externeReferentie;
    }

    public boolean isGedeeltelijkTeFactureren() {
        return gedeeltelijkTeFactureren;
    }

    public void setGedeeltelijkTeFactureren(boolean gedeeltelijkTeFactureren) {
        this.gedeeltelijkTeFactureren = gedeeltelijkTeFactureren;
    }

    public boolean isOpdrachtAfgewerkt() {
        return opdrachtAfgewerkt;
    }

    public void setOpdrachtAfgewerkt(boolean opdrachtAfgewerkt) {
        this.opdrachtAfgewerkt = opdrachtAfgewerkt;
    }

    public String getOmschrijvingVraagKlacht() {
        return omschrijvingVraagKlacht;
    }

    public void setOmschrijvingVraagKlacht(String omschrijvingVraagKlacht) {
        this.omschrijvingVraagKlacht = omschrijvingVraagKlacht;
    }


}