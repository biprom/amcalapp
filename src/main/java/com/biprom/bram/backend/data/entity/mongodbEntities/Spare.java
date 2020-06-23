package com.biprom.bram.backend.data.entity.mongodbEntities;

import org.springframework.data.annotation.Id;

public class Spare {

    @Id
    String Index;

    private String artikelNummer;
    private String omschrijvingArtikelFabrikant;
    private String spareGroup;
    private String spareBOM;
    private Integer spareHoeveelheid;
    private String spareNummer;
    private String spareOmschrijvingFabrikant;


    public String getIndex() {
        return Index;
    }

    public void setIndex(String index) {
        Index = index;
    }

    public String getArtikelNummer() {
        return artikelNummer;
    }

    public void setArtikelNummer(String artikelNummer) {
        this.artikelNummer = artikelNummer;
    }

    public String getOmschrijvingArtikelFabrikant() {
        return omschrijvingArtikelFabrikant;
    }

    public void setOmschrijvingArtikelFabrikant(String omschrijvingArtikelFabrikant) {
        this.omschrijvingArtikelFabrikant = omschrijvingArtikelFabrikant;
    }

    public String getSpareGroup() {
        return spareGroup;
    }

    public void setSpareGroup(String spareGroup) {
        this.spareGroup = spareGroup;
    }

    public String getSpareBOM() {
        return spareBOM;
    }

    public void setSpareBOM(String spareBOM) {
        this.spareBOM = spareBOM;
    }

    public Integer getSpareHoeveelheid() {
        return spareHoeveelheid;
    }

    public void setSpareHoeveelheid(Integer spareHoeveelheid) {
        this.spareHoeveelheid = spareHoeveelheid;
    }

    public String getSpareNummer() {
        return spareNummer;
    }

    public void setSpareNummer(String spareNummer) {
        this.spareNummer = spareNummer;
    }

    public String getSpareOmschrijvingFabrikant() {
        return spareOmschrijvingFabrikant;
    }

    public void setSpareOmschrijvingFabrikant(String spareOmschrijvingFabrikant) {
        this.spareOmschrijvingFabrikant = spareOmschrijvingFabrikant;
    }
}
