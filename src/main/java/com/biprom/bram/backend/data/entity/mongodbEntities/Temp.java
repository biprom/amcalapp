package com.biprom.bram.backend.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

public class Temp {

    @Id
    @JsonIgnore
    String index;

    //Hoofdgegevens
    private String artikelCode;
    private String fabrieksOmschrijving;
    private String netto;
    private String brutoPrijs;

    public String getArtikelCode() {
        return artikelCode;
    }

    public void setArtikelCode(String artikelCode) {
        this.artikelCode = artikelCode;
    }

    public String getFabrieksOmschrijving() {
        return fabrieksOmschrijving;
    }

    public void setFabrieksOmschrijving(String fabrieksOmschrijving) {
        this.fabrieksOmschrijving = fabrieksOmschrijving;
    }

    public String getNetto() {
        return netto;
    }

    public void setNetto(String netto) {
        this.netto = netto;
    }

    public String getBrutoPrijs() {
        return brutoPrijs;
    }

    public void setBrutoPrijs(String brutoPrijs) {
        this.brutoPrijs = brutoPrijs;
    }
}

