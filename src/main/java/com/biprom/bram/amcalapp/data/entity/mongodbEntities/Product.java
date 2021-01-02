package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Product {


    @Id
    @JsonIgnore
    String index;

    Boolean inStruct;

    //Hoofdgegevens
    private String artikelNummer = "";
    private String kitNummer = " ";
    private String omschrijvingArtikelFabrikant = "";
    private String eigenOmschrijving = "";
    private String Bruto2018 = "0";
    private String Bruto = "0";
    private String jaar = "";
    private String Korting = "0";
    private  double gewichtArtikel = 0;
    private String linkGrundfos = "0";
    private String spareBOM = " ";
    private String bulkBOM = " ";
    private String vageOmschrijving = "";
    private String opmerkingen;

    private Double aantalVoorspeldOfferte = 1.0;
    private Double kortingKlant = 0.0;
    private Double nettoKlantOfferte = 0.0;
    private Double werkelijkAantalVerbruikt = 0.0;
    private Double nettoKlantRegie = 0.0;
    private Double kortingAmcal = 0.0;
    private Double nettoAmcal = 0.0;
    private Double winstMarge = 0.0;
    private Double nettoKlantSysteem2 = 0.0;

    private boolean bProductAanwezig;
    private boolean bProductTeBestellen;
    private boolean bProductBesteld;
    private boolean bProductOrderBevestiging;
    private boolean bProductGeleverd;
    private Boolean optie;
    private boolean fitting;
    private boolean spare;
    private boolean elekrischMateriaal;

    private String geselecteerdeParameters;
    private String materiaal;
    private String metrisch1;
    private String metrisch2;
    private String inch1;
    private String inch2;

    private List<String> afmetingen;
    private List<String> artikelType;
    private List<String> pompType;
    private List<String> asAfdichting;
    private List<String> kitSlijtdelen;
    private List<String> pompKapVoet;
    private List<String> kitORingen;
    private List<String> kitWaaiersKamer;
    private List<String> mantelTrekstangTreklatPompas;
    private List<String> allerlei;

    private String bulkNummer;
    private String commentaarToevoeging;
    private Integer stockAantal;
    private int aantalInBulk;
    private String toebehoren;
    private String merk;
    private List<String> imageList;


    @JsonIgnore
    private boolean bGoedgekeurdKlant = true;

    @JsonIgnore
    private LocalDateTime dateProductBesteld;
    @JsonIgnore
    private LocalDate dateProductLeverdatum;
    @JsonIgnore
    private LocalDateTime datePrijs;
    @JsonIgnore
    private String linkFoto;

    public Product() {
    }

    public Product(String vageOmschrijving) {
        this.vageOmschrijving = vageOmschrijving;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }

    public boolean isbProductAanwezig() {
        return bProductAanwezig;
    }

    public void setbProductAanwezig(boolean bProductAanwezig) {
        this.bProductAanwezig = bProductAanwezig;
    }

    public boolean isbProductTeBestellen() {
        return bProductTeBestellen;
    }

    public void setbProductTeBestellen(boolean bProductTeBestellen) {
        this.bProductTeBestellen = bProductTeBestellen;
    }

    public boolean isbProductBesteld() {
        return bProductBesteld;
    }

    public void setbProductBesteld(boolean bProductBesteld) {
        this.bProductBesteld = bProductBesteld;
    }

    public boolean isbProductOrderBevestiging() {
        return bProductOrderBevestiging;
    }

    public void setbProductOrderBevestiging(boolean bProductOrderBevestiging) {
        this.bProductOrderBevestiging = bProductOrderBevestiging;
    }

    public boolean isbProductGeleverd() {
        return bProductGeleverd;
    }

    public void setbProductGeleverd(boolean bProductGeleverd) {
        this.bProductGeleverd = bProductGeleverd;
    }

    public LocalDateTime getDateProductBesteld() {
        return dateProductBesteld;
    }

    public void setDateProductBesteld(LocalDateTime dateProductBesteld) {
        this.dateProductBesteld = dateProductBesteld;
    }

    public String getKorting() {
        return Korting;
    }

    public void setKorting(String korting) {
        this.Korting = korting;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getArtikelNummer() {
        return artikelNummer;
    }

    public void setArtikelNummer(String artikelNummer) {
        this.artikelNummer = artikelNummer;
    }

    public String getOmschrijvingArtikelFabrikant() {
        return omschrijvingArtikelFabrikant;
    }

    public void setOmschrijvingArtikelFabrikant(String omschrijvingArtikelFabrikant) {
        this.omschrijvingArtikelFabrikant = omschrijvingArtikelFabrikant;
    }

    public String getBruto2018() {
        return Bruto2018;
    }

    public void setBruto2018(String bruto2018) {
        this.Bruto2018 = bruto2018;
    }

    public double getGewichtArtikel() {
        return gewichtArtikel;
    }

    public void setGewichtArtikel(double gewichtArtikel) {
        this.gewichtArtikel = gewichtArtikel;
    }

    public String getLinkGrundfos() {
        return linkGrundfos;
    }

    public void setLinkGrundfos(String linkGrundfos) {
        this.linkGrundfos = linkGrundfos;
    }

    public Double getAantalVoorspeldOfferte() {
        return aantalVoorspeldOfferte;
    }

    public void setAantalVoorspeldOfferte(Double aantalVoorspeldOfferte) {
        this.aantalVoorspeldOfferte = aantalVoorspeldOfferte;
    }

    public Double getKortingKlant() {
        return kortingKlant;
    }

    public void setKortingKlant(Double kortingKlant) {
        this.kortingKlant = kortingKlant;
    }

    public Double getNettoKlantOfferte() {
        return nettoKlantOfferte;
    }

    public void setNettoKlantOfferte(Double nettoKlantOfferte) {
        this.nettoKlantOfferte = nettoKlantOfferte;
    }

    public Double getWerkelijkAantalVerbruikt() {
        return werkelijkAantalVerbruikt;
    }

    public void setWerkelijkAantalVerbruikt(Double werkelijkAantalVerbruikt) {
        this.werkelijkAantalVerbruikt = werkelijkAantalVerbruikt;
    }

    public Double getNettoKlantRegie() {
        return nettoKlantRegie;
    }

    public void setNettoKlantRegie(Double nettoKlantRegie) {
        this.nettoKlantRegie = nettoKlantRegie;
    }

    public Double getKortingAmcal() {
        return kortingAmcal;
    }

    public void setKortingAmcal(Double kortingAmcal) {
        this.kortingAmcal = kortingAmcal;
    }

    public Double getNettoAmcal() {
        return nettoAmcal;
    }

    public void setNettoAmcal(Double nettoAmcal) {
        this.nettoAmcal = nettoAmcal;
    }

    public String getEigenOmschrijving() {
        return eigenOmschrijving;
    }

    public void setEigenOmschrijving(String eigenOmschrijving) {
        this.eigenOmschrijving = eigenOmschrijving;
    }

    public String getKitNummer() {
        return kitNummer;
    }

    public void setKitNummer(String kitNummer) {
        this.kitNummer = kitNummer;
    }

    public String getSpareBOM() {
        return spareBOM;
    }

    public void setSpareBOM(String spareBOM) {
        this.spareBOM = spareBOM;
    }

    public String getBulkBOM() {
        return bulkBOM;
    }

    public void setBulkBOM(String bulkBOM) {
        this.bulkBOM = bulkBOM;
    }

    public Double getWinstMarge() {
        return winstMarge;
    }

    public void setWinstMarge(Double winstMarge) {
        this.winstMarge = winstMarge;
    }

    public Double getNettoKlantSysteem2() {
        return nettoKlantSysteem2;
    }

    public void setNettoKlantSysteem2(Double nettoKlantSysteem2) {
        this.nettoKlantSysteem2 = nettoKlantSysteem2;
    }

    public String getVageOmschrijving() {
        return vageOmschrijving;
    }

    public void setVageOmschrijving(String vageOmschrijving) {
        this.vageOmschrijving = vageOmschrijving;
    }

    public LocalDateTime getDatePrijs() {
        return datePrijs;
    }

    public void setDatePrijs(LocalDateTime datePrijs) {
        this.datePrijs = datePrijs;
    }

    public LocalDate getDateProductLeverdatum() {
        return dateProductLeverdatum;
    }

    public void setDateProductLeverdatum(LocalDate dateProductLeverdatum) {
        this.dateProductLeverdatum = dateProductLeverdatum;
    }

    public Boolean getOptie() {
        return optie;
    }

    public void setOptie(Boolean optie) {
        this.optie = optie;
    }

    public String getBruto() {
        return Bruto;
    }

    public void setBruto(String bruto) {
        Bruto = bruto;
    }

    public String getJaar() {
        return jaar;
    }

    public void setJaar(String jaar) {
        this.jaar = jaar;
    }

    public boolean isbGoedgekeurdKlant() {
        return bGoedgekeurdKlant;
    }

    public void setbGoedgekeurdKlant(boolean bGoedgekeurdKlant) {
        this.bGoedgekeurdKlant = bGoedgekeurdKlant;
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

    public Boolean getInStruct() {
        return inStruct;
    }

    public void setInStruct(Boolean inStruct) {
        this.inStruct = inStruct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "artikelNummer='" + artikelNummer + '\'' +
                ", omschrijvingArtikelFabrikant='" + omschrijvingArtikelFabrikant + '\'' +
                ", eigenOmschrijving='" + eigenOmschrijving + '\'' +
                ", vageOmschrijving='" + vageOmschrijving + '\'' +
                '}';
    }

    public String getOpmerkingen() {
        return opmerkingen;
    }

    public void setOpmerkingen(String opmerkingen) {
        this.opmerkingen = opmerkingen;
    }

    public boolean isFitting() {
        return fitting;
    }

    public void setFitting(boolean fitting) {
        this.fitting = fitting;
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

    public String getBulkNummer() {
        return bulkNummer;
    }

    public void setBulkNummer(String bulkNummer) {
        this.bulkNummer = bulkNummer;
    }

    public String getCommentaarToevoeging() {
        return commentaarToevoeging;
    }

    public void setCommentaarToevoeging(String commentaarToevoeging) {
        this.commentaarToevoeging = commentaarToevoeging;
    }

    public Integer getStockAantal() {
        return stockAantal;
    }

    public void setStockAantal(Integer stockAantal) {
        this.stockAantal = stockAantal;
    }

    public int getAantalInBulk() {
        return aantalInBulk;
    }

    public void setAantalInBulk(int aantalInBulk) {
        this.aantalInBulk = aantalInBulk;
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

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public boolean isSpare() {
        return spare;
    }

    public void setSpare(boolean spare) {
        this.spare = spare;
    }

    public boolean isElekrischMateriaal() {
        return elekrischMateriaal;
    }

    public void setElekrischMateriaal(boolean elekrischMateriaal) {
        this.elekrischMateriaal = elekrischMateriaal;
    }
}
