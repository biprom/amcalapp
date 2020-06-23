package com.biprom.bram.backend.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Personeel {

    @org.springframework.data.annotation.Id
    @JsonIgnore
    public String id;

    private String voorNaam;
    private String achterNaam;
    private String afdeling;
    private String functie;
    private String telfoonNummer;
    private String gsmNummer;
    private String internNummer;
    private String eMail;
    private String inlogNaam;
    private String wachtWoord;
    private String adres;
    private Boolean inDienst = false;
    private String calendarId;

    public Personeel() {
    }

    public Personeel(String inlogNaam) {
        this.inlogNaam = inlogNaam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public void setVoorNaam(String voorNaam) {
        this.voorNaam = voorNaam;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }

    public String getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getTelfoonNummer() {
        return telfoonNummer;
    }

    public void setTelfoonNummer(String telfoonNummer) {
        this.telfoonNummer = telfoonNummer;
    }

    public String getGsmNummer() {
        return gsmNummer;
    }

    public void setGsmNummer(String gsmNummer) {
        this.gsmNummer = gsmNummer;
    }

    public String getInternNummer() {
        return internNummer;
    }

    public void setInternNummer(String internNummer) {
        this.internNummer = internNummer;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Boolean getInDienst() {
        return inDienst;
    }

    public void setInDienst(Boolean inDienst) {
        this.inDienst = inDienst;
    }

    public String getInlogNaam() {
        return inlogNaam;
    }

    public void setInlogNaam(String inlogNaam) {
        this.inlogNaam = inlogNaam;
    }

    public String getWachtWoord() {
        return wachtWoord;
    }

    public void setWachtWoord(String wachtWoord) {
        this.wachtWoord = wachtWoord;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }
}
