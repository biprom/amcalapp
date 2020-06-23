package com.biprom.bram.backend.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Personen {

    @org.springframework.data.annotation.Id
    @JsonIgnore
    private String Id;

    private String naam = " ";
    private String voorNaam = " ";
    private String telefoonNummer = " ";
    private String emailAdres = " ";
    private String gsmNummer = " ";
    private String functie = " ";

    public Personen() {
    }

    public Personen(String naam, String voorNaam, String telefoonNummer, String emailAdres, String gsmNummer) {
        this.naam = naam;
        this.voorNaam = voorNaam;
        this.telefoonNummer = telefoonNummer;
        this.emailAdres = emailAdres;
        this.gsmNummer = gsmNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public void setVoorNaam(String voorNaam) {
        this.voorNaam = voorNaam;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public String getGsmNummer() {
        return gsmNummer;
    }

    public void setGsmNummer(String gsmNummer) {
        this.gsmNummer = gsmNummer;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    @Override
    public String toString() {
        return "Personen{" +
                "Id='" + Id + '\'' +
                ", naam='" + naam + '\'' +
                ", voorNaam='" + voorNaam + '\'' +
                ", telefoonNummer='" + telefoonNummer + '\'' +
                ", emailAdres='" + emailAdres + '\'' +
                ", gsmNummer='" + gsmNummer + '\'' +
                '}';
    }
}
