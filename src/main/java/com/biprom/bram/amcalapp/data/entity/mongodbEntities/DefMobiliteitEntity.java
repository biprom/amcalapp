package com.biprom.bram.amcalapp.data.entity.mongodbEntities;

public class DefMobiliteitEntity {

    private String datum;

    private String startWerkdag;
    private String plaatsEersteLocatie;

    private String eindeLaatsteLocatie;
    private String plaatsLaatsteLocatie;

    private Boolean bMobiliteitSmorgens;
    private Boolean getbMobiliteitSavonds;

    private String totaalMobiliteit;
    private String totaalWerkuren;
    private Boolean locked;

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getStartWerkdag() {
        return startWerkdag;
    }

    public void setStartWerkdag(String startWerkdag) {
        this.startWerkdag = startWerkdag;
    }

    public String getPlaatsEersteLocatie() {
        return plaatsEersteLocatie;
    }

    public void setPlaatsEersteLocatie(String plaatsEersteLocatie) {
        this.plaatsEersteLocatie = plaatsEersteLocatie;
    }

    public String getEindeLaatsteLocatie() {
        return eindeLaatsteLocatie;
    }

    public void setEindeLaatsteLocatie(String eindeLaatsteLocatie) {
        this.eindeLaatsteLocatie = eindeLaatsteLocatie;
    }

    public String getPlaatsLaatsteLocatie() {
        return plaatsLaatsteLocatie;
    }

    public void setPlaatsLaatsteLocatie(String plaatsLaatsteLocatie) {
        this.plaatsLaatsteLocatie = plaatsLaatsteLocatie;
    }

    public Boolean getbMobiliteitSmorgens() {
        return bMobiliteitSmorgens;
    }

    public void setbMobiliteitSmorgens(Boolean bMobiliteitSmorgens) {
        this.bMobiliteitSmorgens = bMobiliteitSmorgens;
    }

    public Boolean getGetbMobiliteitSavonds() {
        return getbMobiliteitSavonds;
    }

    public void setGetbMobiliteitSavonds(Boolean getbMobiliteitSavonds) {
        this.getbMobiliteitSavonds = getbMobiliteitSavonds;
    }

    public String getTotaalMobiliteit() {
        return totaalMobiliteit;
    }

    public void setTotaalMobiliteit(String totaalMobiliteit) {
        this.totaalMobiliteit = totaalMobiliteit;
    }

    public String getTotaalWerkuren() {
        return totaalWerkuren;
    }

    public void setTotaalWerkuren(String totaalWerkuren) {
        this.totaalWerkuren = totaalWerkuren;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

}
