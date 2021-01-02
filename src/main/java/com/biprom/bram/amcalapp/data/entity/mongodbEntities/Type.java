package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Type {

    @Id
    String index;

    private String referentie;
    private String omschrijving;
    private String jaar;
    private String pos;
    private int rang;
    private String vageOmschrijving;
    private int oudePrijs;
    private String type;
    private String brutoPrijs;

    private LocalDateTime datumPrijs;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getReferentie() {
        return referentie;
    }

    public void setReferentie(String referentie) {
        this.referentie = referentie;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrutoPrijs() {
        return brutoPrijs;
    }

    public void setBrutoPrijs(String brutoPrijs) {
        this.brutoPrijs = brutoPrijs;
    }

    public LocalDateTime getDatumPrijs() {
        return datumPrijs;
    }

    public void setDatumPrijs(LocalDateTime datumPrijs) {
        this.datumPrijs = datumPrijs;
    }

    public String getVageOmschrijving() {
        return vageOmschrijving;
    }

    public void setVageOmschrijving(String vageOmschrijving) {
        this.vageOmschrijving = vageOmschrijving;
    }

    public String getJaar() {
        return jaar;
    }

    public void setJaar(String jaar) {
        this.jaar = jaar;
    }
}
