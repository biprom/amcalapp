package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class MobiliteitEntity {

    private String id;

    private LocalDate datum;
    private String naamTechnieker;

    private LocalDateTime startWerkdag;
    private LocalDateTime startWerkdagAfgerond;

    private LocalDateTime vertrekAtelier;
    private LocalDateTime vertrekAtelierAfgerond;

    private Integer verplaatsingEersteLocatie;
    private String stadEersteLocatie;
    private LocalDateTime startEersteLocatie;

    private Double pauze;

    private Integer verplaatsingLaatsteLocatie;
    private String stadLaatsteLocatie;
    private LocalDateTime eindeLaatsteLocatie;

    private LocalDateTime terugInAtelier;
    private LocalDateTime terugInAtelierAfgerond;
    private LocalDateTime vertrekNaarHuis;
    private LocalDateTime vertrekNaarHuisAfgerond;

    private Integer mobiliteitSmorgens;
    private Integer mobiliteitSavonds;
    private Boolean bMobSmorgens;
    private Boolean bMobSavonds;
    private Boolean bMobSmorgensInit;
    private Boolean bMobSavondsInit;

    private String mobiliteitUren;
    private Integer totaalMobiliteit;

    private Double totaalUren;
    private String modWerkuren;
    private Double correctPauze;

    private String mobiliteitTotKM;
    private String werkuren;

    private Boolean lock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalDateTime getStartWerkdag() {
        return startWerkdag;
    }

    public void setStartWerkdag(LocalDateTime startWerkdag) {
        this.startWerkdag = startWerkdag;
    }

    public LocalDateTime getStartWerkdagAfgerond() {
        return startWerkdagAfgerond;
    }

    public void setStartWerkdagAfgerond(LocalDateTime startWerkdagAfgerond) {
        this.startWerkdagAfgerond = startWerkdagAfgerond;
    }

    public LocalDateTime getVertrekAtelier() {
        return vertrekAtelier;
    }

    public void setVertrekAtelier(LocalDateTime vertrekAtelier) {
        this.vertrekAtelier = vertrekAtelier;
    }

    public LocalDateTime getVertrekAtelierAfgerond() {
        return vertrekAtelierAfgerond;
    }

    public void setVertrekAtelierAfgerond(LocalDateTime vertrekAtelierAfgerond) {
        this.vertrekAtelierAfgerond = vertrekAtelierAfgerond;
    }

    public Integer getVerplaatsingEersteLocatie() {
        return verplaatsingEersteLocatie;
    }

    public void setVerplaatsingEersteLocatie(Integer verplaatsingEersteLocatie) {
        this.verplaatsingEersteLocatie = verplaatsingEersteLocatie;
    }

    public String getStadEersteLocatie() {
        return stadEersteLocatie;
    }

    public void setStadEersteLocatie(String stadEersteLocatie) {
        this.stadEersteLocatie = stadEersteLocatie;
    }

    public LocalDateTime getStartEersteLocatie() {
        return startEersteLocatie;
    }

    public void setStartEersteLocatie(LocalDateTime startEersteLocatie) {
        this.startEersteLocatie = startEersteLocatie;
    }


    public Double getPauze() {
        return pauze;
    }

    public void setPauze(Double pauze) {
        this.pauze = pauze;
    }

    public Integer getVerplaatsingLaatsteLocatie() {
        return verplaatsingLaatsteLocatie;
    }

    public void setVerplaatsingLaatsteLocatie(Integer verplaatsingLaatsteLocatie) {
        this.verplaatsingLaatsteLocatie = verplaatsingLaatsteLocatie;
    }

    public String getStadLaatsteLocatie() {
        return stadLaatsteLocatie;
    }

    public void setStadLaatsteLocatie(String stadLaatsteLocatie) {
        this.stadLaatsteLocatie = stadLaatsteLocatie;
    }

    public LocalDateTime getEindeLaatsteLocatie() {
        return eindeLaatsteLocatie;
    }

    public void setEindeLaatsteLocatie(LocalDateTime eindeLaatsteLocatie) {
        this.eindeLaatsteLocatie = eindeLaatsteLocatie;
    }

    public LocalDateTime getTerugInAtelier() {
        return terugInAtelier;
    }

    public void setTerugInAtelier(LocalDateTime terugInAtelier) {
        this.terugInAtelier = terugInAtelier;
    }

    public LocalDateTime getTerugInAtelierAfgerond() {
        return terugInAtelierAfgerond;
    }

    public void setTerugInAtelierAfgerond(LocalDateTime terugInAtelierAfgerond) {
        this.terugInAtelierAfgerond = terugInAtelierAfgerond;
    }

    public LocalDateTime getVertrekNaarHuis() {
        return vertrekNaarHuis;
    }

    public void setVertrekNaarHuis(LocalDateTime vertrekNaarHuis) {
        this.vertrekNaarHuis = vertrekNaarHuis;
    }

    public LocalDateTime getVertrekNaarHuisAfgerond() {
        return vertrekNaarHuisAfgerond;
    }

    public void setVertrekNaarHuisAfgerond(LocalDateTime vertrekNaarHuisAfgerond) {
        this.vertrekNaarHuisAfgerond = vertrekNaarHuisAfgerond;
    }

    public Integer getMobiliteitSmorgens() {
        return mobiliteitSmorgens;
    }

    public void setMobiliteitSmorgens(Integer mobiliteitSmorgens) {
        this.mobiliteitSmorgens = mobiliteitSmorgens;
    }

    public Integer getMobiliteitSavonds() {
        return mobiliteitSavonds;
    }

    public void setMobiliteitSavonds(Integer mobiliteitSavonds) {
        this.mobiliteitSavonds = mobiliteitSavonds;
    }

    public Boolean getbMobSmorgens() {
        return bMobSmorgens;
    }

    public void setbMobSmorgens(Boolean bMobSmorgens) {
        this.bMobSmorgens = bMobSmorgens;
    }

    public Boolean getbMobSavonds() {
        return bMobSavonds;
    }

    public void setbMobSavonds(Boolean bMobSavonds) {
        this.bMobSavonds = bMobSavonds;
    }

    public String getMobiliteitUren() {
        return mobiliteitUren;
    }

    public void setMobiliteitUren(String mobiliteitUren) {
        this.mobiliteitUren = mobiliteitUren;
    }

    public Integer getTotaalMobiliteit() {
        return totaalMobiliteit;
    }

    public void setTotaalMobiliteit(Integer totaalMobiliteit) {
        this.totaalMobiliteit = totaalMobiliteit;
    }

    public Double getTotaalUren() {
        return totaalUren;
    }

    public void setTotaalUren(Double totaalUren) {
        this.totaalUren = totaalUren;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public String getNaamTechnieker() {
        return naamTechnieker;
    }

    public void setNaamTechnieker(String naamTechnieker) {
        this.naamTechnieker = naamTechnieker;
    }

    public Boolean getbMobSmorgensInit() {
        return bMobSmorgensInit;
    }

    public void setbMobSmorgensInit(Boolean bMobSmorgensInit) {
        this.bMobSmorgensInit = bMobSmorgensInit;
    }

    public Boolean getbMobSavondsInit() {
        return bMobSavondsInit;
    }

    public void setbMobSavondsInit(Boolean bMobSavondsInit) {
        this.bMobSavondsInit = bMobSavondsInit;
    }

    public String getModWerkuren() {
        return modWerkuren;
    }

    public void setModWerkuren(String modWerkuren) {
        this.modWerkuren = modWerkuren;
    }

    public String getMobiliteitTotKM() {
        return mobiliteitTotKM;
    }

    public void setMobiliteitTotKM(String mobiliteitTotKM) {
        this.mobiliteitTotKM = mobiliteitTotKM;
    }

    public String getWerkuren() {
        return werkuren;
    }

    public void setWerkuren(String werkuren) {
        this.werkuren = werkuren;
    }

    public Double getCorrectPauze() {
        return correctPauze;
    }

    public void setCorrectPauze(Double correctPauze) {
        this.correctPauze = correctPauze;
    }
}
