package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import java.time.LocalDateTime;
import java.util.List;

public class InplanningEntity {

    private String internNummer;

    private MainTicket mainTicket;
    private String ticketNummer;

    private String aanmaker;

    private String statusAmcalReady = " ";
    private String statusPlanning = " ";
    private String omschrijving;

    private Klanten eindklant;
    private String bedrijfsNaam;
    private Adres ingegevenLeverAdres;
    private String postCode;
    private String stad;

    private List<Product> productList;

    private LocalDateTime dateTimeVolgendeStatus = LocalDateTime.now();
    private LocalDateTime dateTimeInplanning = LocalDateTime.now();
    private LocalDateTime dateTimeVoorlopigeInplanning = LocalDateTime.now();

    private String extraInfo = " ";
    private boolean bVoorlopigeInplanning;
    private boolean bIngepland;

    private String aantalTechniekers;

    public String getStatusAmcalReady() {
        return statusAmcalReady;
    }

    public void setStatusAmcalReady(String statusAmcalReady) {
        this.statusAmcalReady = statusAmcalReady;
    }

    public String getStatusPlanning() {
        return statusPlanning;
    }

    public void setStatusPlanning(String statusPlanning) {
        this.statusPlanning = statusPlanning;
    }

    public LocalDateTime getDateTimeVolgendeStatus() {
        return dateTimeVolgendeStatus;
    }

    public void setDateTimeVolgendeStatus(LocalDateTime dateTimeVolgendeStatus) {
        this.dateTimeVolgendeStatus = dateTimeVolgendeStatus;
    }

    public LocalDateTime getDateTimeInplanning() {
        return dateTimeInplanning;
    }

    public void setDateTimeInplanning(LocalDateTime dateTimeInplanning) {
        this.dateTimeInplanning = dateTimeInplanning;
    }

    public LocalDateTime getDateTimeVoorlopigeInplanning() {
        return dateTimeVoorlopigeInplanning;
    }

    public void setDateTimeVoorlopigeInplanning(LocalDateTime dateTimeVoorlopigeInplanning) {
        this.dateTimeVoorlopigeInplanning = dateTimeVoorlopigeInplanning;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getInternNummer() {
        return internNummer;
    }

    public void setInternNummer(String internNummer) {
        this.internNummer = internNummer;
    }

    public boolean isbVoorlopigeInplanning() {
        return bVoorlopigeInplanning;
    }

    public void setbVoorlopigeInplanning(boolean bVoorlopigeInplanning) {
        this.bVoorlopigeInplanning = bVoorlopigeInplanning;
    }

    public Klanten getEindklant() {
        return eindklant;
    }

    public void setEindklant(Klanten eindklant) {
        this.eindklant = eindklant;
    }

    public Adres getIngegevenLeverAdres() {
        return ingegevenLeverAdres;
    }

    public void setIngegevenLeverAdres(Adres ingegevenLeverAdres) {
        this.ingegevenLeverAdres = ingegevenLeverAdres;
    }

    public MainTicket getMainTicket() {
        return mainTicket;
    }

    public void setMainTicket(MainTicket mainTicket) {
        this.mainTicket = mainTicket;
    }

    public String getTicketNummer() {
        return ticketNummer;
    }

    public void setTicketNummer(String ticketNummer) {
        this.ticketNummer = ticketNummer;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getAantalTechniekers() {
        return aantalTechniekers;
    }

    public void setAantalTechniekers(String aantalTechniekers) {
        this.aantalTechniekers = aantalTechniekers;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public boolean isbIngepland() {
        return bIngepland;
    }

    public void setbIngepland(boolean bIngepland) {
        this.bIngepland = bIngepland;
    }

    public String getBedrijfsNaam() {
        return bedrijfsNaam;
    }

    public void setBedrijfsNaam(String bedrijfsNaam) {
        this.bedrijfsNaam = bedrijfsNaam;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getAanmaker() {
        return aanmaker;
    }

    public void setAanmaker(String aanmaker) {
        this.aanmaker = aanmaker;
    }
}
