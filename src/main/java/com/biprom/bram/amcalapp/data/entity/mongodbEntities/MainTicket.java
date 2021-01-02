package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SpringComponent


public class MainTicket {

    @org.springframework.data.annotation.Id
    @JsonIgnore
    public String Id;

    public Klanten opdrachtgever = new Klanten(  );
    public Personen contactPersoonKlant = new Personen(  );
    public Set<Personen> contactPersoonenKlant = new HashSet<>();
    public String referentieOpdrachtgever = " ";
    public boolean checkInByAtelier;

    public String merk;

    public Klanten eindKlant= new Klanten(  );
    public Personen contactPersoonEindklant= new Personen(  );
    public Set<Personen> contactPersoonenEindklant = new HashSet<>();
    public String referentieEindklant = " ";

    public Adres ingegevenLeverAdres = new Adres(  );


    public String vraagKlant = " ";
    public String interneOpmerkingen = " ";

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd" , shape = JsonFormat.Shape.STRING)
    public LocalDateTime aanvraagDatumTicket = LocalDateTime.now();

    public String ticketNummer = LocalDateTime.now().toString();

    public ArrayList<DetailTicket> details = new ArrayList<DetailTicket>();

    public Personeel toegewezenPersoneel;


    @JsonIgnore
    public boolean gezien;
    @JsonIgnore
    public boolean afgewerkt;
    @JsonIgnore
    public Personeel aangemaaktDOor;
    @JsonIgnore
    public LocalDate deadline;
    @JsonIgnore
    public String title;

    public MainTicket() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public Klanten getOpdrachtgever() {
        return opdrachtgever;
    }

    public void setOpdrachtgever(Klanten opdrachtgever) {
        this.opdrachtgever = opdrachtgever;
    }

    public Personen getContactPersoonKlant() {
        return contactPersoonKlant;
    }

    public void setContactPersoonKlant(Personen contactPersoonKlant) {
        this.contactPersoonKlant = contactPersoonKlant;
    }

    public String getReferentieOpdrachtgever() {
        return referentieOpdrachtgever;
    }

    public void setReferentieOpdrachtgever(String referentieOpdrachtgever) {
        this.referentieOpdrachtgever = referentieOpdrachtgever;
    }

    public Klanten getEindKlant() {
        return eindKlant;
    }

    public void setEindKlant(Klanten eindKlant) {
        this.eindKlant = eindKlant;
    }

    public Personen getContactPersoonEindklant() {
        return contactPersoonEindklant;
    }

    public void setContactPersoonEindklant(Personen contactPersoonEindklant) {
        this.contactPersoonEindklant = contactPersoonEindklant;
    }

    public String getReferentieEindklant() {
        return referentieEindklant;
    }

    public void setReferentieEindklant(String referentieEindklant) {
        this.referentieEindklant = referentieEindklant;
    }


    public String getVraagKlant() {
        return vraagKlant;
    }

    public void setVraagKlant(String vraagKlant) {
        this.vraagKlant = vraagKlant;
    }

    public String getInterneOpmerkingen() {
        return interneOpmerkingen;
    }

    public void setInterneOpmerkingen(String interneOpmerkingen) {
        this.interneOpmerkingen = interneOpmerkingen;
    }

    public LocalDateTime getAanvraagDatumTicket() {
        return aanvraagDatumTicket;
    }

    public void setAanvraagDatumTicket(LocalDateTime aanvraagDatumTicket) {
        this.aanvraagDatumTicket = aanvraagDatumTicket;
    }

    public String getTicketNummer() {
        return ticketNummer;
    }

    public void setTicketNummer(String ticketNummer) {
        this.ticketNummer = ticketNummer;
    }

    public ArrayList<DetailTicket> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<DetailTicket> details) {
        this.details = details;
    }

    @JsonIgnore
    public String getContactPersoonKlantNaam(){
        return contactPersoonKlant.getVoorNaam() + " " + contactPersoonKlant.getNaam();
    }

    @JsonIgnore
    public String getContactPersoonEindklantNaam(){
        if(contactPersoonEindklant != null){
            return contactPersoonEindklant.getVoorNaam()+ " " + contactPersoonEindklant.getNaam();
        }
        return "na";
    }

    public Adres getIngegevenLeverAdres() {
        return ingegevenLeverAdres;
    }

    public void setIngegevenLeverAdres(Adres ingegevenLeverAdres) {
        this.ingegevenLeverAdres = ingegevenLeverAdres;
    }

    public boolean isCheckInByAtelier() {
        return checkInByAtelier;
    }

    public void setCheckInByAtelier(boolean checkInByAtelier) {
        this.checkInByAtelier = checkInByAtelier;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public Personeel getToegewezenPersoneel() {
        return toegewezenPersoneel;
    }

    public void setToegewezenPersoneel(Personeel toegewezenPersoneel) {
        this.toegewezenPersoneel = toegewezenPersoneel;
    }

    public boolean isGezien() {
        return gezien;
    }

    public void setGezien(boolean gezien) {
        this.gezien = gezien;
    }

    public boolean isAfgewerkt() {
        return afgewerkt;
    }

    public void setAfgewerkt(boolean afgewerkt) {
        this.afgewerkt = afgewerkt;
    }

    public Personeel getAangemaaktDOor() {
        return aangemaaktDOor;
    }

    public void setAangemaaktDOor(Personeel aangemaaktDOor) {
        this.aangemaaktDOor = aangemaaktDOor;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Personen> getContactPersoonenKlant() {
        return contactPersoonenKlant;
    }

    public void setContactPersoonenKlant(Set<Personen> contactPersoonenKlant) {
        this.contactPersoonenKlant = contactPersoonenKlant;
    }

    public Set<Personen> getContactPersoonenEindklant() {
        return contactPersoonenEindklant;
    }

    public void setContactPersoonenEindklant(Set<Personen> contactPersoonenEindklant) {
        this.contactPersoonenEindklant = contactPersoonenEindklant;
    }
}