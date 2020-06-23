package com.biprom.bram.backend.data.entity.mongodbEntities;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CheckHerstelBestek {

    @org.springframework.data.annotation.Id
    String Id;

    String amNummer;
    private boolean bTeDemonteren;
    private boolean bWachtOpAntwoord;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dtWachtOpAntwoord;
    private boolean bRappel1;
    private boolean bRappel2;
    private boolean bRappel3;
    private boolean bLevering;
    private boolean bAfhaling;
    private boolean bWachtOpOnderdelen;
    private boolean bPompHersteld;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dtPompHersteld;
    private boolean bPompAfgehaald;
    private boolean bPompVerschroten;
    private boolean bPompOnhersteldTerug;
    private boolean bTeFactureren;

    public CheckHerstelBestek(String index, String amNummer, boolean bTeDemonteren, boolean bWachtOpAntwoord, LocalDateTime dtWachtOpAntwoord, boolean bRappel1, boolean bRappel2, boolean bRappel3, boolean bLevering, boolean bAfhaling, boolean bWachtOpOnderdelen, boolean bPompHersteld, LocalDateTime dtPompHersteld, boolean bPompAfgehaald, boolean bPompVerschroten, boolean bPompOnhersteldTerug, boolean bTeFactureren) {
        this.Id = index;
        this.amNummer = amNummer;
        this.bTeDemonteren = bTeDemonteren;
        this.bWachtOpAntwoord = bWachtOpAntwoord;
        this.dtWachtOpAntwoord = dtWachtOpAntwoord;
        this.bRappel1 = bRappel1;
        this.bRappel2 = bRappel2;
        this.bRappel3 = bRappel3;
        this.bLevering = bLevering;
        this.bAfhaling = bAfhaling;
        this.bWachtOpOnderdelen = bWachtOpOnderdelen;
        this.bPompHersteld = bPompHersteld;
        this.dtPompHersteld = dtPompHersteld;
        this.bPompAfgehaald = bPompAfgehaald;
        this.bPompVerschroten = bPompVerschroten;
        this.bPompOnhersteldTerug = bPompOnhersteldTerug;
        this.bTeFactureren = bTeFactureren;
    }

    public String getIndex() {
        return Id;
    }

    public void setIndex(String index) {
        this.Id = index;
    }

    public String getAmNummer() {
        return amNummer;
    }

    public void setAmNummer(String amNummer) {
        this.amNummer = amNummer;
    }

    public boolean isbTeDemonteren() {
        return bTeDemonteren;
    }

    public void setbTeDemonteren(boolean bTeDemonteren) {
        this.bTeDemonteren = bTeDemonteren;
    }


    public boolean isbWachtOpAntwoord() {
        return bWachtOpAntwoord;
    }

    public void setbWachtOpAntwoord(boolean bWachtOpAntwoord) {
        this.bWachtOpAntwoord = bWachtOpAntwoord;
    }

    public LocalDateTime getDtWachtOpAntwoord() {
        return dtWachtOpAntwoord;
    }

    public void setDtWachtOpAntwoord(LocalDateTime dtWachtOpAntwoord) {
        this.dtWachtOpAntwoord = dtWachtOpAntwoord;
    }

    public boolean isbRappel1() {
        return bRappel1;
    }

    public void setbRappel1(boolean bRappel1) {
        this.bRappel1 = bRappel1;
    }

    public boolean isbRappel2() {
        return bRappel2;
    }

    public void setbRappel2(boolean bRappel2) {
        this.bRappel2 = bRappel2;
    }

    public boolean isbRappel3() {
        return bRappel3;
    }

    public void setbRappel3(boolean bRappel3) {
        this.bRappel3 = bRappel3;
    }

    public boolean isbLevering() {
        return bLevering;
    }

    public void setbLevering(boolean bLevering) {
        this.bLevering = bLevering;
    }

    public boolean isbAfhaling() {
        return bAfhaling;
    }

    public void setbAfhaling(boolean bAfhaling) {
        this.bAfhaling = bAfhaling;
    }

    public boolean isbWachtOpOnderdelen() {
        return bWachtOpOnderdelen;
    }

    public void setbWachtOpOnderdelen(boolean bWachtOpOnderdelen) {
        this.bWachtOpOnderdelen = bWachtOpOnderdelen;
    }

    public boolean isbPompHersteld() {
        return bPompHersteld;
    }

    public void setbPompHersteld(boolean bPompHersteld) {
        this.bPompHersteld = bPompHersteld;
    }

    public LocalDateTime getDtPompHersteld() {
        return dtPompHersteld;
    }

    public void setDtPompHersteld(LocalDateTime dtPompHersteld) {
        this.dtPompHersteld = dtPompHersteld;
    }

    public boolean isbPompAfgehaald() {
        return bPompAfgehaald;
    }

    public void setbPompAfgehaald(boolean bPompAfgehaald) {
        this.bPompAfgehaald = bPompAfgehaald;
    }

    public boolean isbPompVerschroten() {
        return bPompVerschroten;
    }

    public void setbPompVerschroten(boolean bPompVerschroten) {
        this.bPompVerschroten = bPompVerschroten;
    }

    public boolean isbPompOnhersteldTerug() {
        return bPompOnhersteldTerug;
    }

    public void setbPompOnhersteldTerug(boolean bPompOnhersteldTerug) {
        this.bPompOnhersteldTerug = bPompOnhersteldTerug;
    }

    public boolean isbTeFactureren() {
        return bTeFactureren;
    }

    public void setbTeFactureren(boolean bTeFactureren) {
        this.bTeFactureren = bTeFactureren;
    }
}
