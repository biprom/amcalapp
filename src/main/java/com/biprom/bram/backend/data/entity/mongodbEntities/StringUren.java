package com.biprom.bram.backend.data.entity.mongodbEntities;

public class StringUren {

    private String stringStartDatumTijd;

    private String stringeindDatumTijd;

    private Double pauze;

    private String omschrijving;

    public StringUren() {
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

    public Double getPauze() {
        return pauze;
    }

    public void setPauze(Double pauze) {
        this.pauze = pauze;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
}
