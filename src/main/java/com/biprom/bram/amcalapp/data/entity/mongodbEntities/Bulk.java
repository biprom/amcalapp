package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import org.springframework.data.annotation.Id;

public class Bulk {

    @Id
    String index;

    private String artikelNummer;
    private String omschrijvingArtikelFabrikant;
    private String bulkGroep;
    private String bulkBOM;
    private Integer bulkHoeveelheid;
    private Integer bulkNummer;
    private String bulkOmschrijvingFabrikant;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public String getBulkGroep() {
        return bulkGroep;
    }

    public void setBulkGroep(String bulkGroep) {
        this.bulkGroep = bulkGroep;
    }

    public String getBulkBOM() {
        return bulkBOM;
    }

    public void setBulkBOM(String bulkBOM) {
        this.bulkBOM = bulkBOM;
    }

    public Integer getBulkHoeveelheid() {
        return bulkHoeveelheid;
    }

    public void setBulkHoeveelheid(Integer bulkHoeveelheid) {
        this.bulkHoeveelheid = bulkHoeveelheid;
    }

    public Integer getBulkNummer() {
        return bulkNummer;
    }

    public void setBulkNummer(Integer bulkNummer) {
        this.bulkNummer = bulkNummer;
    }

    public String getBulkOmschrijvingFabrikant() {
        return bulkOmschrijvingFabrikant;
    }

    public void setBulkOmschrijvingFabrikant(String bulkOmschrijvingFabrikant) {
        this.bulkOmschrijvingFabrikant = bulkOmschrijvingFabrikant;
    }
}
