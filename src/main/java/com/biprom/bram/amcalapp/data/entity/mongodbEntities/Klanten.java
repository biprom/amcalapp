package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Klanten {


    @org.springframework.data.annotation.Id
    @JsonIgnore
    public String id;

    public String bedrijfsNaam = "";
    public String afdeling = "";
    public List<Personen> aanvragers ;
    @JsonIgnore
    public List<Adres> leverAdressen;
    public Adres facturatieAdres = new Adres();
    public String btwNummer = "";
    public String alias;
    public String notitie;
    public Double lat = null;
    public Double lng = null;

    public Klanten(String bedrijfsNaam, String afdeling, List<Personen> aanvragers, List<Adres> leverAdressen) {
        this.bedrijfsNaam = bedrijfsNaam;
        this.afdeling = afdeling;
        this.aanvragers = aanvragers;
        this.leverAdressen = leverAdressen;
    }

    public Klanten() {

    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getBedrijfsNaam() {
        return bedrijfsNaam;
    }

    public void setBedrijfsNaam(String bedrijfsNaam) {
        this.bedrijfsNaam = bedrijfsNaam;
    }

    public String getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }

    public List<Personen> getAanvragers() {
        return aanvragers;
    }

    public void setAanvragers(List<Personen> aanvragers) {
        this.aanvragers = aanvragers;
    }

    public List<Adres> getLeverAdressen() {
        return leverAdressen;
    }

    public void setLeverAdressen(List<Adres> leverAdressen) {
        this.leverAdressen = leverAdressen;
    }

    public Adres getFacturatieAdres() {
        return facturatieAdres;
    }

    public void setFacturatieAdres(Adres facturatieAdres) {
        this.facturatieAdres = facturatieAdres;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public void setBtwNummer(String btwNummer) {
        this.btwNummer = btwNummer;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Klanten{" +
                "Id='" + id + '\'' +
                ", bedrijfsNaam='" + bedrijfsNaam + '\'' +
                ", afdeling='" + afdeling + '\'' +
                ", aanvragers=" + aanvragers +
                ", leverAdressen=" + leverAdressen +
                ", facturatieAdres=" + facturatieAdres +
                '}';
    }

    public String getNotitie() {
        return notitie;
    }

    public void setNotitie(String notitie) {
        this.notitie = notitie;
    }
}
