package com.biprom.bram.amcalapp.data.entity.mongodbEntities;


import java.time.LocalDateTime;

public class Fittings {

    @org.springframework.data.annotation.Id
    String Id;

    private String fabrieksOmschrijving;
    private String eigenOmschrijving;
    private String geselecteerdeParameters;
    private String materiaal;
    private String artikelType;
    private String metrisch1;
    private String metrisch2;
    private String inch1;
    private String inch2;
    private String artikelCode;
    private Integer stockAantal;
    private LocalDateTime datePrijs;

    public String getFabrieksOmschrijving() {
        return fabrieksOmschrijving;
    }

    public void setFabrieksOmschrijving(String fabrieksOmschrijving) {
        this.fabrieksOmschrijving = fabrieksOmschrijving;
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

    public String getMateriaal() {
        return materiaal;
    }

    public void setMateriaal(String materiaal) {
        this.materiaal = materiaal;
    }

    public String getArtikelType() {
        return artikelType;
    }

    public void setArtikelType(String artikelType) {
        this.artikelType = artikelType;
    }

    public String getMetrisch1() {
        return metrisch1;
    }

    public void setMetrisch1(String metrisch1) {
        this.metrisch1 = metrisch1;
    }

    public String getMetrisch2() {
        return metrisch2;
    }

    public void setMetrisch2(String metrisch2) {
        this.metrisch2 = metrisch2;
    }

    public String getInch1() {
        return inch1;
    }

    public void setInch1(String inch1) {
        this.inch1 = inch1;
    }

    public String getInch2() {
        return inch2;
    }

    public void setInch2(String inch2) {
        this.inch2 = inch2;
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

    public LocalDateTime getDatePrijs() {
        return datePrijs;
    }

    public void setDatePrijs(LocalDateTime datePrijs) {
        this.datePrijs = datePrijs;
    }
}
