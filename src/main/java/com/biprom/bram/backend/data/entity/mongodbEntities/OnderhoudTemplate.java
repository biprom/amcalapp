package com.biprom.bram.backend.data.entity.mongodbEntities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OnderhoudTemplate {

    @org.springframework.data.annotation.Id
    private String Id;

    Klanten opdrachtGever;
    Set<Personen> contactPersonenListOpdrachtgever;
    Klanten eindKlant;
    Set<Personen> contactPersonenListEindKlant;
    String internNummer;
    String onderhoudContractNummer;
    boolean contractGoedgekeurd;
    LocalDateTime aanmaakDatum;
    String externeReferentie;
    String onderhoudFrequentie;
    LocalDate startOnderhoud;
    String aantalTechniekers;
    String vraagKlant;
    String interneOpmerkingen;
    List<Product> productList = new ArrayList<>(  );
    List<DetailTicket> onderhoudsList;

    public String getId() {
        return Id;
    }

    public Klanten getOpdrachtGever() {
        return opdrachtGever;
    }

    public void setOpdrachtGever(Klanten opdrachtGever) {
        this.opdrachtGever = opdrachtGever;
    }

    public String getInternNummer() {
        return internNummer;
    }

    public void setInternNummer(String internNummer) {
        this.internNummer = internNummer;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getOnderhoudContractNummer() {
        return onderhoudContractNummer;
    }

    public void setOnderhoudContractNummer(String onderhoudContractNummer) {
        this.onderhoudContractNummer = onderhoudContractNummer;
    }


    public boolean isContractGoedgekeurd() {
        return contractGoedgekeurd;
    }

    public void setContractGoedgekeurd(boolean contractGoedgekeurd) {
        this.contractGoedgekeurd = contractGoedgekeurd;
    }

    public LocalDateTime getAanmaakDatum() {
        return aanmaakDatum;
    }

    public void setAanmaakDatum(LocalDateTime aanmaakDatum) {
        this.aanmaakDatum = aanmaakDatum;
    }

    public String getExterneReferentie() {
        return externeReferentie;
    }

    public void setExterneReferentie(String externeReferentie) {
        this.externeReferentie = externeReferentie;
    }

    public String getOnderhoudFrequentie() {
        return onderhoudFrequentie;
    }

    public void setOnderhoudFrequentie(String onderhoudFrequentie) {
        this.onderhoudFrequentie = onderhoudFrequentie;
    }

    public LocalDate getStartOnderhoud() {
        return startOnderhoud;
    }

    public void setStartOnderhoud(LocalDate startOnderhoud) {
        this.startOnderhoud = startOnderhoud;
    }

    public String getAantalTechniekers() {
        return aantalTechniekers;
    }

    public void setAantalTechniekers(String aantalTechniekers) {
        this.aantalTechniekers = aantalTechniekers;
    }

    public String getVraagKlant() {
        return vraagKlant;
    }

    public void setVraagKlant(String vraagKlant) {
        this.vraagKlant = vraagKlant;
    }

    public String getInterneOpmerkingen() {
        return interneOpmerkingen;
    }

    public void setInterneOpmerkingen(String interneOpmerkingen) {
        this.interneOpmerkingen = interneOpmerkingen;
    }

    public List<DetailTicket> getOnderhoudsList() {
        return onderhoudsList;
    }

    public void setOnderhoudsList(List<DetailTicket> onderhoudsList) {
        this.onderhoudsList = onderhoudsList;
    }



    public Klanten getEindKlant() {
        return eindKlant;
    }

    public void setEindKlant(Klanten eindKlant) {
        this.eindKlant = eindKlant;
    }

    public Set<Personen> getContactPersonenListOpdrachtgever() {
        return contactPersonenListOpdrachtgever;
    }

    public void setContactPersonenListOpdrachtgever(Set<Personen> contactPersonenListOpdrachtgever) {
        this.contactPersonenListOpdrachtgever = contactPersonenListOpdrachtgever;
    }

    public Set<Personen> getContactPersonenListEindKlant() {
        return contactPersonenListEindKlant;
    }

    public void setContactPersonenListEindKlant(Set<Personen> contactPersonenListEindKlant) {
        this.contactPersonenListEindKlant = contactPersonenListEindKlant;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
