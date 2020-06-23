package com.biprom.bram.backend.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Contacten {

    @org.springframework.data.annotation.Id
    @JsonIgnore
    private String Id;

    private String Firma;
    private String Naam;
    private String Voornaam;
    private String Email;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirma() {
        return Firma;
    }

    public void setFirma(String firma) {
        Firma = firma;
    }

    public String getNaam() {
        return Naam;
    }

    public void setNaam(String naam) {
        Naam = naam;
    }

    public String getVoornaam() {
        return Voornaam;
    }

    public void setVoornaam(String voornaam) {
        Voornaam = voornaam;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
