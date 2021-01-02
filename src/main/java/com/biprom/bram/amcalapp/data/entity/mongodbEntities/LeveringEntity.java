package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class LeveringEntity {

    @Id
    String index;

    String amNummer;
    String artikelNummer;
    String eigenOmschrijving;
    Boolean geleverd;
    LocalDate leverDatum;
    Personeel internPersoneel;

    public LeveringEntity(String index, String amNummer, String artikelNummer, String eigenOmschrijving, boolean geleverd, LocalDate leverDatum, Personeel toegewezenPersoneel) {
        this.index = index;
        this.amNummer = amNummer;
        this.artikelNummer = artikelNummer;
        this.eigenOmschrijving = eigenOmschrijving;
        this.geleverd = geleverd;
        this.leverDatum = leverDatum;
        this.internPersoneel = toegewezenPersoneel;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAmNummer() {
        return amNummer;
    }

    public void setAmNummer(String amNummer) {
        this.amNummer = amNummer;
    }

    public String getArtikelNummer() {
        return artikelNummer;
    }

    public void setArtikelNummer(String artikelNummer) {
        this.artikelNummer = artikelNummer;
    }

    public String getEigenOmschrijving() {
        return eigenOmschrijving;
    }

    public void setEigenOmschrijving(String eigenOmschrijving) {
        this.eigenOmschrijving = eigenOmschrijving;
    }

    public Boolean getGeleverd() {
        return geleverd;
    }

    public void setGeleverd(Boolean geleverd) {
        this.geleverd = geleverd;
    }

    public LocalDate getLeverDatum() {
        return leverDatum;
    }

    public void setLeverDatum(LocalDate leverDatum) {
        this.leverDatum = leverDatum;
    }

    public Personeel getInternPersoneel() {
        return internPersoneel;
    }

    public void setInternPersoneel(Personeel internPersoneel) {
        this.internPersoneel = internPersoneel;
    }
}
