package com.biprom.bram.amcalapp.data.entity.mongodbEntities;



public class FittingPrijsLijst {

    @org.springframework.data.annotation.Id
    String Id;

    private String artikCode;
    private String commentaarToevoeging;
    private String bruto2019;
    private String korting2019;
    private String netto2019;

    public String getArtikCode() {
        return artikCode;
    }

    public void setArtikCode(String artikCode) {
        this.artikCode = artikCode;
    }

    public String getCommentaarToevoeging() {
        return commentaarToevoeging;
    }

    public void setCommentaarToevoeging(String commentaarToevoeging) {
        this.commentaarToevoeging = commentaarToevoeging;
    }

    public String getBruto2019() {
        return bruto2019;
    }

    public void setBruto2019(String bruto2019) {
        this.bruto2019 = bruto2019;
    }

    public String getKorting2019() {
        return korting2019;
    }

    public void setKorting2019(String korting2019) {
        this.korting2019 = korting2019;
    }

    public String getNetto2019() {
        return netto2019;
    }

    public void setNetto2019(String netto2019) {
        this.netto2019 = netto2019;
    }
}
