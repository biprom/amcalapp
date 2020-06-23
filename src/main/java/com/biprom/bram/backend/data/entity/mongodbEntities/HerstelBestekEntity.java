package com.biprom.bram.backend.data.entity.mongodbEntities;

import java.time.LocalDateTime;

public class HerstelBestekEntity {

    private String internNummer;

    private boolean bDirecteHerstelling;

    private Klanten opdrachtGever;
    private String bedrijfsNaam;
    private Klanten eindKlant;

    private String pompType;
    private String pompStatus;

    private LocalDateTime dtLaatsteWijziging;
    private LocalDateTime dtAanmaakDatum;
    private LocalDateTime dtWachtOpAntwoord;

    private MainTicket mainTicket;

    private String omschrijving;

    public String getInternNummer() {
        return internNummer;
    }

    public void setInternNummer(String internNummer) {
        this.internNummer = internNummer;
    }

    public Klanten getOpdrachtGever() {
        return opdrachtGever;
    }

    public void setOpdrachtGever(Klanten opdrachtGever) {
        this.opdrachtGever = opdrachtGever;
    }

    public Klanten getEindKlant() {
        return eindKlant;
    }

    public void setEindKlant(Klanten eindKlant) {
        this.eindKlant = eindKlant;
    }

    public String getPompType() {
        return pompType;
    }

    public void setPompType(String pompType) {
        this.pompType = pompType;
    }

    public String getPompStatus() {
        return pompStatus;
    }

    public void setPompStatus(String pompStatus) {
        this.pompStatus = pompStatus;
    }

    public LocalDateTime getDtLaatsteWijziging() {
        return dtLaatsteWijziging;
    }

    public void setDtLaatsteWijziging(LocalDateTime dtLaatsteWijziging) {
        this.dtLaatsteWijziging = dtLaatsteWijziging;
    }

    public LocalDateTime getDtAanmaakDatum() {
        return dtAanmaakDatum;
    }

    public void setDtAanmaakDatum(LocalDateTime dtAanmaakDatum) {
        this.dtAanmaakDatum = dtAanmaakDatum;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public LocalDateTime getDtWachtOpAntwoord() {
        return dtWachtOpAntwoord;
    }

    public void setDtWachtOpAntwoord(LocalDateTime dtWachtOpAntwoord) {
        this.dtWachtOpAntwoord = dtWachtOpAntwoord;
    }

    public MainTicket getMainTicket() {
        return mainTicket;
    }

    public void setMainTicket(MainTicket mainTicket) {
        this.mainTicket = mainTicket;
    }

    public boolean isbDirecteHerstelling() {
        return bDirecteHerstelling;
    }

    public void setbDirecteHerstelling(boolean bDirecteHerstelling) {
        this.bDirecteHerstelling = bDirecteHerstelling;
    }

    public String getBedrijfsNaam() {
        return bedrijfsNaam;
    }

    public void setBedrijfsNaam(String bedrijfsNaam) {
        this.bedrijfsNaam = bedrijfsNaam;
    }
}
