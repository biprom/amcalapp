package com.biprom.bram.backend.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Adres {

    @org.springframework.data.annotation.Id
    @JsonIgnore
    private String Id;

    private String land = " ";
    private String stad = " ";
    private String straat = " ";
    private String postcode = " ";
    private String commentaar = " ";
    private Double lat;
    private Double lng;
    public Integer verplaatsing;

    public Adres(String land, String stad, String straat, String nummer, String postcode, String bus, String commentaar, Double lat, Double lng) {
        this.land = land;
        this.stad = stad;
        this.straat = straat;
        this.postcode = postcode;
        this.commentaar = commentaar;
        this.lat = lat;
        this.lng = lng;
    }

    public Adres() {
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCommentaar() {
        return commentaar;
    }

    public void setCommentaar(String commentaar) {
        this.commentaar = commentaar;
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

    public Integer getVerplaatsing() {
        return verplaatsing;
    }

    public void setVerplaatsing(Integer verplaatsing) {
        this.verplaatsing = verplaatsing;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "Id='" + Id + '\'' +
                ", land='" + land + '\'' +
                ", stad='" + stad + '\'' +
                ", straat='" + straat + '\'' +
                ", postcode='" + postcode + '\'' +
                ", commentaar='" + commentaar + '\'' +
                '}';
    }
}
