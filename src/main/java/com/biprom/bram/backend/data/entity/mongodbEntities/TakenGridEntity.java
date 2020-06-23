package com.biprom.bram.backend.data.entity.mongodbEntities;

import org.springframework.data.annotation.Id;

public class TakenGridEntity {

    @Id
    String index;

    private String bedrijfsnaam;

    private Adres adres;
    private Personen contactPersoon;
    DetailTicket detailTicket;
    MainTicket mainTicket;


    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Personen getContactPersoon() {
        return contactPersoon;
    }

    public void setContactPersoon(Personen contactPersoon) {
        this.contactPersoon = contactPersoon;
    }

    public DetailTicket getDetailTicket() {
        return detailTicket;
    }

    public void setDetailTicket(DetailTicket detailTicket) {
        this.detailTicket = detailTicket;
    }

    public MainTicket getMainTicket() {
        return mainTicket;
    }

    public void setMainTicket(MainTicket mainTicket) {
        this.mainTicket = mainTicket;
    }
}
