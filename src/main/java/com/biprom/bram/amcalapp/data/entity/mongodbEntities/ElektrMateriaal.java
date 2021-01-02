package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import java.time.LocalDateTime;
import java.util.List;

public class ElektrMateriaal {

    @org.springframework.data.annotation.Id
    String id;

    private String artikelCode;
    private Integer stockAantal = 0;
    private String opmerkingen;
    private String materiaal;
    private String toebehoren;
    private String merk;
    private String omschrijvingFabrikant;
    private String eigenOmschrijving;
    private String geselecteerdeParameters;
    private Double brutoPrijs = 0.0;
    private Double nettoPrijs = 0.0;

    private List<String> imageList;
    private LocalDateTime datePrijs;

    public ElektrMateriaal() {
    }

    public String getArtikelCode() {
        return artikelCode;
    }

    public void setArtikelCode(String artikelCode) {
        this.artikelCode = artikelCode;
    }

    public Integer getStockAantal() {
        return stockAantal;
    }

    public void setStockAantal(Integer stockAantal) {
        this.stockAantal = stockAantal;
    }

    public String getOpmerkingen() {
        return opmerkingen;
    }

    public void setOpmerkingen(String opmerkingen) {
        this.opmerkingen = opmerkingen;
    }

    public String getOmschrijvingFabrikant() {
        return omschrijvingFabrikant;
    }

    public void setOmschrijvingFabrikant(String omschrijvingFabrikant) {
        this.omschrijvingFabrikant = omschrijvingFabrikant;
    }

    public String getEigenOmschrijving() {
        return eigenOmschrijving;
    }

    public void setEigenOmschrijving(String eigenOmschrijving) {
        this.eigenOmschrijving = eigenOmschrijving;
    }

    public String getGeselecteerdeParameters() {
        return geselecteerdeParameters;
    }

    public void setGeselecteerdeParameters(String geselecteerdeParameters) {
        this.geselecteerdeParameters = geselecteerdeParameters;
    }

    public Double getBrutoPrijs() {
        return brutoPrijs;
    }

    public void setBrutoPrijs(Double brutoPrijs) {
        this.brutoPrijs = brutoPrijs;
    }

    public Double getNettoPrijs() {
        return nettoPrijs;
    }

    public void setNettoPrijs(Double nettoPrijs) {
        this.nettoPrijs = nettoPrijs;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public LocalDateTime getDatePrijs() {
        return datePrijs;
    }

    public void setDatePrijs(LocalDateTime datePrijs) {
        this.datePrijs = datePrijs;
    }

    public String getMateriaal() {
        return materiaal;
    }

    public void setMateriaal(String materiaal) {
        this.materiaal = materiaal;
    }

    public String getToebehoren() {
        return toebehoren;
    }

    public void setToebehoren(String toebehoren) {
        this.toebehoren = toebehoren;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }
}
