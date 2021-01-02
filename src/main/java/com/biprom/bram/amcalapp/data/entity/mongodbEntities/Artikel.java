package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Artikel {
    @org.springframework.data.annotation.Id
    @JsonIgnore
    String Id;

    //Hoofdgegevens
    private boolean fitting;

    private String artikelCode;
    private Integer stockAantal;
    private String bulkNummer;
    private int aantalInBulk;
    private String commentaarToevoeging;
    private String eigenOmschrijving;
    private String fabrieksOmschrijving;
    private String geselecteerdeParameters;
    private String opmerkingen;
    private String materiaal;

    private LocalDateTime datePrijs;

    private String brutoPrijs;
    private String korting;
    private String netto;
    private String jaarPrijs;

    private List<String> afmetingen = new ArrayList<>(  );
    private List<String> artikelType = new ArrayList<>(  );
    private List<String> pompType = new ArrayList<>(  );
    private List<String> asAfdichting = new ArrayList<>(  );
    private List<String> kitSlijtdelen = new ArrayList<>(  );
    private List<String> pompKapVoet = new ArrayList<>(  );
    private List<String> kitORingen = new ArrayList<>(  );
    private List<String> kitWaaiersKamer = new ArrayList<>(  );
    private List<String> mantelTrekstangTreklatPompas = new ArrayList<>(  );
    private List<String> allerlei = new ArrayList<>(  );

    //extra parameters om tabel excel stock te genereren
    private String tijdstipIngave;
    private String parameters;
    private String vageOmschrijving;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public boolean isFitting() {
        return fitting;
    }

    public void setFitting(boolean fitting) {
        this.fitting = fitting;
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

    public String getBulkNummer() {
        return bulkNummer;
    }

    public void setBulkNummer(String bulkNummer) {
        this.bulkNummer = bulkNummer;
    }

    public int getAantalInBulk() {
        return aantalInBulk;
    }

    public void setAantalInBulk(int aantalInBulk) {
        this.aantalInBulk = aantalInBulk;
    }

    public String getCommentaarToevoeging() {
        return commentaarToevoeging;
    }

    public void setCommentaarToevoeging(String commentaarToevoeging) {
        this.commentaarToevoeging = commentaarToevoeging;
    }

    public String getEigenOmschrijving() {
        return eigenOmschrijving;
    }

    public void setEigenOmschrijving(String eigenOmschrijving) {
        this.eigenOmschrijving = eigenOmschrijving;
    }

    public String getFabrieksOmschrijving() {
        return fabrieksOmschrijving;
    }

    public void setFabrieksOmschrijving(String fabrieksOmschrijving) {
        this.fabrieksOmschrijving = fabrieksOmschrijving;
    }

    public String getGeselecteerdeParameters() {
        return geselecteerdeParameters;
    }

    public void setGeselecteerdeParameters(String geselecteerdeParameters) {
        this.geselecteerdeParameters = geselecteerdeParameters;
    }

    public String getOpmerkingen() {
        return opmerkingen;
    }

    public void setOpmerkingen(String opmerkingen) {
        this.opmerkingen = opmerkingen;
    }

    public String getMateriaal() {
        return materiaal;
    }

    public void setMateriaal(String materiaal) {
        this.materiaal = materiaal;
    }

    public LocalDateTime getDatePrijs() {
        return datePrijs;
    }

    public void setDatePrijs(LocalDateTime datePrijs) {
        this.datePrijs = datePrijs;
    }

    public String getBrutoPrijs() {
        return brutoPrijs;
    }

    public void setBrutoPrijs(String brutoPrijs) {
        this.brutoPrijs = brutoPrijs;
    }

    public String getKorting() {
        return korting;
    }

    public void setKorting(String korting) {
        this.korting = korting;
    }

    public String getNetto() {
        return netto;
    }

    public void setNetto(String netto) {
        this.netto = netto;
    }

    public String getJaarPrijs() {
        return jaarPrijs;
    }

    public void setJaarPrijs(String jaarPrijs) {
        this.jaarPrijs = jaarPrijs;
    }

    public List<String> getAfmetingen() {
        return afmetingen;
    }

    public void setAfmetingen(List<String> afmetingen) {
        this.afmetingen = afmetingen;
    }

    public List<String> getArtikelType() {
        return artikelType;
    }

    public void setArtikelType(List<String> artikelType) {
        this.artikelType = artikelType;
    }

    public List<String> getPompType() {
        return pompType;
    }

    public void setPompType(List<String> pompType) {
        this.pompType = pompType;
    }

    public List<String> getAsAfdichting() {
        return asAfdichting;
    }

    public void setAsAfdichting(List<String> asAfdichting) {
        this.asAfdichting = asAfdichting;
    }

    public List<String> getKitSlijtdelen() {
        return kitSlijtdelen;
    }

    public void setKitSlijtdelen(List<String> kitSlijtdelen) {
        this.kitSlijtdelen = kitSlijtdelen;
    }

    public List<String> getPompKapVoet() {
        return pompKapVoet;
    }

    public void setPompKapVoet(List<String> pompKapVoet) {
        this.pompKapVoet = pompKapVoet;
    }

    public List<String> getKitORingen() {
        return kitORingen;
    }

    public void setKitORingen(List<String> kitORingen) {
        this.kitORingen = kitORingen;
    }

    public List<String> getKitWaaiersKamer() {
        return kitWaaiersKamer;
    }

    public void setKitWaaiersKamer(List<String> kitWaaiersKamer) {
        this.kitWaaiersKamer = kitWaaiersKamer;
    }

    public List<String> getMantelTrekstangTreklatPompas() {
        return mantelTrekstangTreklatPompas;
    }

    public void setMantelTrekstangTreklatPompas(List<String> mantelTrekstangTreklatPompas) {
        this.mantelTrekstangTreklatPompas = mantelTrekstangTreklatPompas;
    }

    public List<String> getAllerlei() {
        return allerlei;
    }

    public void setAllerlei(List<String> allerlei) {
        this.allerlei = allerlei;
    }

    public String getTijdstipIngave() {
        return tijdstipIngave;
    }

    public void setTijdstipIngave(String tijdstipIngave) {
        this.tijdstipIngave = tijdstipIngave;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getVageOmschrijving() {
        return vageOmschrijving;
    }

    public void setVageOmschrijving(String vageOmschrijving) {
        this.vageOmschrijving = vageOmschrijving;
    }
}
