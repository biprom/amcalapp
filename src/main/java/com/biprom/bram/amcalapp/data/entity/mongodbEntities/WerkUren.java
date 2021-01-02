package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class WerkUren {

    @JsonIgnore
    @org.springframework.data.annotation.Id
    private String id;

    private LocalDateTime startDatumTijd;
    private String stringStartDatumTijd;

    private LocalDateTime eindDatumTijd;
    private String stringeindDatumTijd;
    private Double pauze;
    private Long ladenEnLossen;

    private String omschrijving;

    private boolean ingegevenDoorTechnieker;

    @JsonIgnore
    private String inlogNaamTechnieker;
    @JsonIgnore
    private boolean aangepast;
    @JsonIgnore
    private LocalDateTime startDatumTijdAangepast;
    @JsonIgnore
    private LocalDateTime eindDatumTijdAangepast;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public LocalDateTime getStartDatumTijd() {
        return startDatumTijd;
    }

    public void setStartDatumTijd(LocalDateTime startDatumTijd) {
        this.startDatumTijd = startDatumTijd;
    }

    public LocalDateTime getEindDatumTijd() {
        return eindDatumTijd;
    }

    public void setEindDatumTijd(LocalDateTime eindDatumTijd) {
        this.eindDatumTijd = eindDatumTijd;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public Double getPauze() {
        return pauze;
    }

    public void setPauze(Double pauze) {
        this.pauze = pauze;
    }

    public Long getLadenEnLossen() {
        return ladenEnLossen;
    }

    public void setLadenEnLossen(Long ladenEnLossen) {
        this.ladenEnLossen = ladenEnLossen;
    }

    public String getStringStartDatumTijd() {
        return stringStartDatumTijd;
    }

    public void setStringStartDatumTijd(String stringStartDatumTijd) {
        this.stringStartDatumTijd = stringStartDatumTijd;
    }

    public String getStringeindDatumTijd() {
        return stringeindDatumTijd;
    }

    public void setStringeindDatumTijd(String stringeindDatumTijd) {
        this.stringeindDatumTijd = stringeindDatumTijd;
    }

    public String getInlogNaamTechnieker() {
        return inlogNaamTechnieker;
    }

    public void setInlogNaamTechnieker(String inlogNaamTechnieker) {
        this.inlogNaamTechnieker = inlogNaamTechnieker;
    }

    public boolean isIngegevenDoorTechnieker() {
        return ingegevenDoorTechnieker;
    }

    public void setIngegevenDoorTechnieker(boolean ingegevenDoorTechnieker) {
        this.ingegevenDoorTechnieker = ingegevenDoorTechnieker;
    }

    public Boolean getAangepast() {
        return aangepast;
    }

    public void setAangepast(Boolean aangepast) {
        this.aangepast = aangepast;
    }

    public LocalDateTime getStartDatumTijdAangepast() {
        return startDatumTijdAangepast;
    }

    public void setStartDatumTijdAangepast(LocalDateTime startDatumTijdAangepast) {
        this.startDatumTijdAangepast = startDatumTijdAangepast;
    }

    public LocalDateTime getEindDatumTijdAangepast() {
        return eindDatumTijdAangepast;
    }

    public void setEindDatumTijdAangepast(LocalDateTime eindDatumTijdAangepast) {
        this.eindDatumTijdAangepast = eindDatumTijdAangepast;
    }
}
