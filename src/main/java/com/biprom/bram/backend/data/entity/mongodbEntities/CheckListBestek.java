package com.biprom.bram.backend.data.entity.mongodbEntities;


import org.springframework.data.annotation.Id;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class CheckListBestek {

    @Id
    String index;

    private String productieDatumMotor;

    private String motorMechanischInOrde;
    private String motorElekrischInOrde;

    private String Risolatie;
    private String Rfase1;
    private String Rfase2;
    private String Rfase3;
    private String positieMagazijn;
    private String anderPositie;
    private String amNummer;
    private LocalDateTime datumBinnengebracht = LocalDateTime.now();

    private String demontagePersoon;
    private String geholpenDoor;
    private List<Personeel> gedemonteerdDoorList = new ArrayList<>();

    private String hersteldDoor;
    private String herstellingGeholpenDoor;
    private List<Personeel> hersteldDoorList = new ArrayList<>();

    private Set<String> binnengebrachtOp;
    private String binnengebrachtOpAndere;
    private String commentaarMotor;
    private Set<String> isolatieWeerstand;
    private Set<String> wikkelingsWaardes;
    private String wikkelWaarde1 = new String("");
    private String wikkelWaarde2 = new String("");
    private String wikkelWaarde3 = new String("");
    private Set<String> waterInMotor;
    private Set<String> motorLagers;
    private String lagersMotorCommentaar;
    private String lagersMotorVentilatorCommentaar;
    private Set<String> ventilator;
    private Set<String> rotorStatorFlens;
    private Set<String> motorVerbrand = new HashSet<>();

    private String commentaarPomp = "";
    private Set<String> pompBinnengebracht;
    private Set<String> pompstaat;
    private Set<String> garantie;

    private Set<String> asafdichting;
    private Set<String> secundaireAsafdichting = new HashSet<>(  );
    private String asafdichtingCommentaar;
    private String secundaireAsafdichtingCommentaar;
    private String waterInOnderdeelCommentaar;

    private String staatPompCommentaar;
    private String pompBinnengebrachtCommentaar;

    private Set<String> pompas;
    private String pompasCommentaar;

    private Set<String> waaiers;
    private String waaiersCommentaar;

    private Set<String> kamers;
    private String kamersCommentaar;

    private Set<String> dichtingen;
    private String dichtingenCommentaar;

    private Set<String> aantastingen;
    private String aantastingCommentaar;

    private Set<String> lagers;
    private String lagersPompCommentaar;

    private Set<String> spaltringen;
    private String spaltRingenCommentaar;

    private Set<String> binnenwerk;

    private Set<String> contaminatie;

    private Set<String> pompStatus;
    private String pompStatusCommentaar;
    private String commentaarPompNaarJonasNaHerstelling;
    private String motorKabelCommentaar;

    private String productieDatumPomp;
    private String serieNummerPomp;
    private String pompLand;
    private String uitgebreidPompType;

    private String meetresultaatDebiet;
    private String meetresultaatDruk;
    private String drukTestVuilwaterDruk;

    private String nominaalDebiet;
    private String nominaalDruk;
    private String nominlaalVuilWaterDruk;

    private String motorType;
    private String nominaalVermogen;
    private String toerental;
    private String artikelNummerMotor;
    private String artikelNummerPomp;
    private String uster;
    private String udriehoek;

    private boolean freqRegelaar;
    private boolean nietStandaardPomp;
    private boolean bestekKostenAanrekenen = true;

    private Integer zijdeElektrischeAansluiting;
    private Integer zijdeOntluchting;
    private Integer zijdeMontagePlaten;

    private boolean gnNabehandeling;
    private boolean herschilderen;
    private boolean zandstralenEnHerschilderen;
    private boolean zuurbehandeling;
    private boolean inGoedeStaat;
    private boolean bGeenLogoPlaatsen;

    private String aantalUrenDemontage = " ";
    private String aantalUrenHerstelling = " ";
    private String aantalUrenReiniging = " ";
    private String aantalVoorspeldeUren = " ";
    private Integer kostprijsInspectie = 50;
    private Integer leverTermijn = 5;
    private LocalDateTime uitersteHerstelDatum = LocalDateTime.now();

    private LocalDate datumHerstel = LocalDate.now();
    private LocalDateTime datumDemontage = LocalDateTime.now();

    private boolean pompMWGespoten;
    private boolean pompMWGezandstraald;
    private boolean zuurbehandelingUitgevoerd;

    private Set<String> controlBox = new HashSet<>(  );
    private Set<String> motorKabel = new HashSet<>(  );
    private Set<String> vochtInMotor = new HashSet<>(  );

    private Set<String> motorDichtingen = new HashSet<>(  );

    private String vermogenPomp = new String("");
    private String qNomPomp = new String("");
    private String hNomPomp = new String("");


    public Set<String> getMotorVerbrand() {
        return motorVerbrand;
    }

    public void setMotorVerbrand(Set<String> motorVerbrand) {
        this.motorVerbrand = motorVerbrand;
    }

    public String getMotorMechanischInOrde() {
        return motorMechanischInOrde;
    }

    public void setMotorMechanischInOrde(String motorMechanischInOrde) {
        this.motorMechanischInOrde = motorMechanischInOrde;
    }

    public String getMotorElekrischInOrde() {
        return motorElekrischInOrde;
    }

    public void setMotorElekrischInOrde(String motorElekrischInOrde) {
        this.motorElekrischInOrde = motorElekrischInOrde;
    }

    public String getRisolatie() {
        return Risolatie;
    }

    public void setRisolatie(String risolatie) {
        Risolatie = risolatie;
    }

    public String getRfase1() {
        return Rfase1;
    }

    public void setRfase1(String rfase1) {
        Rfase1 = rfase1;
    }

    public String getRfase2() {
        return Rfase2;
    }

    public void setRfase2(String rfase2) {
        Rfase2 = rfase2;
    }

    public String getRfase3() {
        return Rfase3;
    }

    public void setRfase3(String rfase3) {
        Rfase3 = rfase3;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPositieMagazijn() {
        return positieMagazijn;
    }

    public void setPositieMagazijn(String positieMagazijn) {
        this.positieMagazijn = positieMagazijn;
    }

    public String getAnderPositie() {
        return anderPositie;
    }

    public void setAnderPositie(String anderPositie) {
        this.anderPositie = anderPositie;
    }

    public String getAmNummer() {
        return amNummer;
    }

    public void setAmNummer(String amNummer) {
        this.amNummer = amNummer;
    }

    public LocalDateTime getDatumBinnengebracht() {
        return datumBinnengebracht;
    }

    public void setDatumBinnengebracht(LocalDateTime datumBinnengebracht) {
        this.datumBinnengebracht = datumBinnengebracht;
    }

    public Set<String> getBinnengebrachtOp() {
        return binnengebrachtOp;
    }

    public void setBinnengebrachtOp(Set<String> binnengebrachtOp) {
        this.binnengebrachtOp = binnengebrachtOp;
    }

    public String getBinnengebrachtOpAndere() {
        return binnengebrachtOpAndere;
    }

    public void setBinnengebrachtOpAndere(String binnengebrachtOpAndere) {
        this.binnengebrachtOpAndere = binnengebrachtOpAndere;
    }

    public String getCommentaarMotor() {
        return commentaarMotor;
    }

    public void setCommentaarMotor(String commentaarMotor) {
        this.commentaarMotor = commentaarMotor;
    }

    public Set<String> getIsolatieWeerstand() {
        return isolatieWeerstand;
    }

    public void setIsolatieWeerstand(Set<String> isolatieWeerstand) {
        this.isolatieWeerstand = isolatieWeerstand;
    }

    public Set<String> getWikkelingsWaardes() {
        return wikkelingsWaardes;
    }

    public void setWikkelingsWaardes(Set<String> wikkelingsWaardes) {
        this.wikkelingsWaardes = wikkelingsWaardes;
    }

    public Set<String> getWaterInMotor() {
        return waterInMotor;
    }

    public void setWaterInMotor(Set<String> waterInMotor) {
        this.waterInMotor = waterInMotor;
    }

    public Set<String> getMotorLagers() {
        return motorLagers;
    }

    public void setMotorLagers(Set<String> motorLagers) {
        this.motorLagers = motorLagers;
    }

    public String getLagersMotorCommentaar() {
        return lagersMotorCommentaar;
    }

    public void setLagersMotorCommentaar(String lagersMotorCommentaar) {
        this.lagersMotorCommentaar = lagersMotorCommentaar;
    }

    public Set<String> getVentilator() {
        return ventilator;
    }

    public void setVentilator(Set<String> ventilator) {
        this.ventilator = ventilator;
    }

    public Set<String> getRotorStatorFlens() {
        return rotorStatorFlens;
    }

    public void setRotorStatorFlens(Set<String> rotorStatorFlens) {
        this.rotorStatorFlens = rotorStatorFlens;
    }

    public String getCommentaarPomp() {
        return commentaarPomp;
    }

    public void setCommentaarPomp(String commentaarPomp) {
        this.commentaarPomp = commentaarPomp;
    }

    public Set<String> getPompBinnengebracht() {
        return pompBinnengebracht;
    }

    public void setPompBinnengebracht(Set<String> pompBinnengebracht) {
        this.pompBinnengebracht = pompBinnengebracht;
    }

    public Set<String> getPompstaat() {
        return pompstaat;
    }

    public void setPompstaat(Set<String> pompstaat) {
        this.pompstaat = pompstaat;
    }

    public Set<String> getGarantie() {
        return garantie;
    }

    public void setGarantie(Set<String> garantie) {
        this.garantie = garantie;
    }

    public Set<String> getAsafdichting() {
        return asafdichting;
    }

    public void setAsafdichting(Set<String> asafdichting) {
        this.asafdichting = asafdichting;
    }

    public String getAsafdichtingCommentaar() {
        return asafdichtingCommentaar;
    }

    public void setAsafdichtingCommentaar(String asafdichtingCommentaar) {
        this.asafdichtingCommentaar = asafdichtingCommentaar;
    }

    public Set<String> getPompas() {
        return pompas;
    }

    public void setPompas(Set<String> pompas) {
        this.pompas = pompas;
    }

    public String getPompasCommentaar() {
        return pompasCommentaar;
    }

    public void setPompasCommentaar(String pompasCommentaar) {
        this.pompasCommentaar = pompasCommentaar;
    }

    public Set<String> getWaaiers() {
        return waaiers;
    }

    public void setWaaiers(Set<String> waaiers) {
        this.waaiers = waaiers;
    }

    public String getWaaiersCommentaar() {
        return waaiersCommentaar;
    }

    public void setWaaiersCommentaar(String waaiersCommentaar) {
        this.waaiersCommentaar = waaiersCommentaar;
    }

    public Set<String> getKamers() {
        return kamers;
    }

    public void setKamers(Set<String> kamers) {
        this.kamers = kamers;
    }

    public String getKamersCommentaar() {
        return kamersCommentaar;
    }

    public void setKamersCommentaar(String kamersCommentaar) {
        this.kamersCommentaar = kamersCommentaar;
    }

    public Set<String> getDichtingen() {
        return dichtingen;
    }

    public void setDichtingen(Set<String> dichtingen) {
        this.dichtingen = dichtingen;
    }

    public String getDichtingenCommentaar() {
        return dichtingenCommentaar;
    }

    public void setDichtingenCommentaar(String dichtingenCommentaar) {
        this.dichtingenCommentaar = dichtingenCommentaar;
    }

    public Set<String> getAantastingen() {
        return aantastingen;
    }

    public void setAantastingen(Set<String> aantastingen) {
        this.aantastingen = aantastingen;
    }

    public String getAantastingCommentaar() {
        return aantastingCommentaar;
    }

    public void setAantastingCommentaar(String aantastingCommentaar) {
        this.aantastingCommentaar = aantastingCommentaar;
    }

    public Set<String> getLagers() {
        return lagers;
    }

    public void setLagers(Set<String> lagers) {
        this.lagers = lagers;
    }

    public String getLagersPompCommentaar() {
        return lagersPompCommentaar;
    }

    public void setLagersPompCommentaar(String lagersPompCommentaar) {
        this.lagersPompCommentaar = lagersPompCommentaar;
    }

    public Set<String> getSpaltringen() {
        return spaltringen;
    }

    public void setSpaltringen(Set<String> spaltringen) {
        this.spaltringen = spaltringen;
    }

    public String getSpaltRingenCommentaar() {
        return spaltRingenCommentaar;
    }

    public void setSpaltRingenCommentaar(String spaltRingenCommentaar) {
        this.spaltRingenCommentaar = spaltRingenCommentaar;
    }

    public Set<String> getBinnenwerk() {
        return binnenwerk;
    }

    public void setBinnenwerk(Set<String> binnenwerk) {
        this.binnenwerk = binnenwerk;
    }

    public Set<String> getContaminatie() {
        return contaminatie;
    }

    public void setContaminatie(Set<String> contaminatie) {
        this.contaminatie = contaminatie;
    }

    public Set<String> getPompStatus() {
        return pompStatus;
    }

    public void setPompStatus(Set<String> pompStatus) {
        this.pompStatus = pompStatus;
    }

    public String getPompStatusCommentaar() {
        return pompStatusCommentaar;
    }

    public void setPompStatusCommentaar(String pompStatusCommentaar) {
        this.pompStatusCommentaar = pompStatusCommentaar;
    }

    public String getMotorType() {
        return motorType;
    }

    public void setMotorType(String motorType) {
        this.motorType = motorType;
    }

    public String getNominaalVermogen() {
        return nominaalVermogen;
    }

    public void setNominaalVermogen(String nominaalVermogen) {
        this.nominaalVermogen = nominaalVermogen;
    }

    public String getToerental() {
        return toerental;
    }

    public void setToerental(String toerental) {
        this.toerental = toerental;
    }

    public String getUster() {
        return uster;
    }

    public void setUster(String uster) {
        this.uster = uster;
    }

    public String getUdriehoek() {
        return udriehoek;
    }

    public void setUdriehoek(String udriehoek) {
        this.udriehoek = udriehoek;
    }

    public boolean isFreqRegelaar() {
        return freqRegelaar;
    }

    public void setFreqRegelaar(boolean freqRegelaar) {
        this.freqRegelaar = freqRegelaar;
    }

    public boolean isNietStandaardPomp() {
        return nietStandaardPomp;
    }

    public void setNietStandaardPomp(boolean nietStandaardPomp) {
        this.nietStandaardPomp = nietStandaardPomp;
    }

    public Integer getZijdeElektrischeAansluiting() {
        return zijdeElektrischeAansluiting;
    }

    public void setZijdeElektrischeAansluiting(Integer zijdeElektrischeAansluiting) {
        this.zijdeElektrischeAansluiting = zijdeElektrischeAansluiting;
    }
    //testf
    public Integer getZijdeOntluchting() {
        return zijdeOntluchting;
    }

    public void setZijdeOntluchting(Integer zijdeOntluchting) {
        this.zijdeOntluchting = zijdeOntluchting;
    }

    public Integer getZijdeMontagePlaten() {
        return zijdeMontagePlaten;
    }

    public void setZijdeMontagePlaten(Integer zijdeMontagePlaten) {
        this.zijdeMontagePlaten = zijdeMontagePlaten;
    }

    public String getDemontagePersoon() {
        return demontagePersoon;
    }

    public void setDemontagePersoon(String demontagePersoon) {
        this.demontagePersoon = demontagePersoon;
    }


    public boolean isPompMWGespoten() {
        return pompMWGespoten;
    }

    public void setPompMWGespoten(boolean pompMWGespoten) {
        this.pompMWGespoten = pompMWGespoten;
    }

    public boolean isPompMWGezandstraald() {
        return pompMWGezandstraald;
    }

    public void setPompMWGezandstraald(boolean pompMWGezandstraald) {
        this.pompMWGezandstraald = pompMWGezandstraald;
    }

    public String getProductieDatumMotor() {
        return productieDatumMotor;
    }

    public void setProductieDatumMotor(String productieDatumMotor) {
        this.productieDatumMotor = productieDatumMotor;
    }

    public String getArtikelNummerMotor() {
        return artikelNummerMotor;
    }

    public void setArtikelNummerMotor(String artikelNummerMotor) {
        this.artikelNummerMotor = artikelNummerMotor;
    }

    public String getArtikelNummerPomp() {
        return artikelNummerPomp;
    }

    public void setArtikelNummerPomp(String artikelNummerPomp) {
        this.artikelNummerPomp = artikelNummerPomp;
    }

    public String getProductieDatumPomp() {
        return productieDatumPomp;
    }

    public void setProductieDatumPomp(String productieDatumPomp) {
        this.productieDatumPomp = productieDatumPomp;
    }

    public String getSerieNummerPomp() {
        return serieNummerPomp;
    }

    public void setSerieNummerPomp(String serieNummerPomp) {
        this.serieNummerPomp = serieNummerPomp;
    }

    public String getPompLand() {
        return pompLand;
    }

    public void setPompLand(String pompLand) {
        this.pompLand = pompLand;
    }

    public String getAantalUrenDemontage() {
        return aantalUrenDemontage;
    }

    public void setAantalUrenDemontage(String aantalUrenDemontage) {
        this.aantalUrenDemontage = aantalUrenDemontage;
    }

    public String getAantalUrenHerstelling() {
        return aantalUrenHerstelling;
    }

    public void setAantalUrenHerstelling(String aantalUrenHerstelling) {
        this.aantalUrenHerstelling = aantalUrenHerstelling;
    }

    public String getAantalUrenReiniging() {
        return aantalUrenReiniging;
    }

    public void setAantalUrenReiniging(String aantalUrenReiniging) {
        this.aantalUrenReiniging = aantalUrenReiniging;
    }

    public Integer getKostprijsInspectie() {
        return kostprijsInspectie;
    }

    public void setKostprijsInspectie(Integer kostprijsInspectie) {
        this.kostprijsInspectie = kostprijsInspectie;
    }

    public Integer getLeverTermijn() {
        return leverTermijn;
    }

    public void setLeverTermijn(Integer leverTermijn) {
        this.leverTermijn = leverTermijn;
    }

    public boolean isBestekKostenAanrekenen() {//demo
        return bestekKostenAanrekenen;
    }

    public void setBestekKostenAanrekenen(boolean bestekKostenAanrekenen) {
        this.bestekKostenAanrekenen = bestekKostenAanrekenen;
    }

    public String getLagersMotorVentilatorCommentaar() {
        return lagersMotorVentilatorCommentaar;
    }

    public void setLagersMotorVentilatorCommentaar(String lagersMotorVentilatorCommentaar) {
        this.lagersMotorVentilatorCommentaar = lagersMotorVentilatorCommentaar;
    }

    public LocalDateTime getUitersteHerstelDatum() {
            return uitersteHerstelDatum;
    }

    public void setUitersteHerstelDatum(LocalDateTime uitersteHerstelDatum) {
            this.uitersteHerstelDatum = uitersteHerstelDatum;
    }

    public boolean isHerschilderen() {
        return herschilderen;
    }

    public void setHerschilderen(boolean herschilderen) {
        this.herschilderen = herschilderen;
    }

    public boolean isZandstralenEnHerschilderen() {
        return zandstralenEnHerschilderen;
    }

    public void setZandstralenEnHerschilderen(boolean zandstralenEnHerschilderen) {
        this.zandstralenEnHerschilderen = zandstralenEnHerschilderen;
    }

    public boolean isInGoedeStaat() {
        return inGoedeStaat;
    }

    public void setInGoedeStaat(boolean inGoedeStaat) {
        this.inGoedeStaat = inGoedeStaat;
    }

    public boolean isbGeenLogoPlaatsen() {
        return bGeenLogoPlaatsen;
    }

    public void setbGeenLogoPlaatsen(boolean bGeenLogoPlaatsen) {
        this.bGeenLogoPlaatsen = bGeenLogoPlaatsen;
    }

    public String getUitgebreidPompType() {
        return uitgebreidPompType;
    }

    public void setUitgebreidPompType(String uitgebreidPompType) {
        this.uitgebreidPompType = uitgebreidPompType;
    }

    public LocalDate getDatumHerstel() {
        return datumHerstel;
    }

    public void setDatumHerstel(LocalDate datumHerstel) {
        this.datumHerstel = datumHerstel;
    }

    public String getMeetresultaatDebiet() {
        return meetresultaatDebiet;
    }

    public void setMeetresultaatDebiet(String meetresultaatDebiet) {
        this.meetresultaatDebiet = meetresultaatDebiet;
    }

    public String getMeetresultaatDruk() {
        return meetresultaatDruk;
    }

    public void setMeetresultaatDruk(String meetresultaatDruk) {
        this.meetresultaatDruk = meetresultaatDruk;
    }

    public String getDrukTestVuilwaterDruk() {
        return drukTestVuilwaterDruk;
    }

    public void setDrukTestVuilwaterDruk(String drukTestVuilwaterDruk) {
        this.drukTestVuilwaterDruk = drukTestVuilwaterDruk;
    }

    public LocalDateTime getDatumDemontage() {
        return datumDemontage;
    }

    public void setDatumDemontage(LocalDateTime datumDemontage) {
        this.datumDemontage = datumDemontage;
    }

    public String getNominaalDebiet() {
        return nominaalDebiet;
    }

    public void setNominaalDebiet(String nominaalDebiet) {
        this.nominaalDebiet = nominaalDebiet;
    }

    public String getNominaalDruk() {
        return nominaalDruk;
    }

    public void setNominaalDruk(String nominaalDruk) {
        this.nominaalDruk = nominaalDruk;
    }

    public String getNominlaalVuilWaterDruk() {
        return nominlaalVuilWaterDruk;
    }

    public void setNominlaalVuilWaterDruk(String nominlaalVuilWaterDruk) {
        this.nominlaalVuilWaterDruk = nominlaalVuilWaterDruk;
    }

    public String getCommentaarPompNaarJonasNaHerstelling() {
        return commentaarPompNaarJonasNaHerstelling;
    }

    public void setCommentaarPompNaarJonasNaHerstelling(String commentaarPompNaarJonasNaHerstelling) {
        this.commentaarPompNaarJonasNaHerstelling = commentaarPompNaarJonasNaHerstelling;
    }

    public String getAantalVoorspeldeUren() {
        return aantalVoorspeldeUren;
    }

    public void setAantalVoorspeldeUren(String aantalVoorspeldeUren) {
        this.aantalVoorspeldeUren = aantalVoorspeldeUren;
    }

    public String getGeholpenDoor() {
        return geholpenDoor;
    }

    public void setGeholpenDoor(String geholpenDoor) {
        this.geholpenDoor = geholpenDoor;
    }

    public String getHersteldDoor() {
        return hersteldDoor;
    }

    public void setHersteldDoor(String hersteldDoor) {
        this.hersteldDoor = hersteldDoor;
    }

    public String getHerstellingGeholpenDoor() {
        return herstellingGeholpenDoor;
    }

    public void setHerstellingGeholpenDoor(String herstellingGeholpenDoor) {
        this.herstellingGeholpenDoor = herstellingGeholpenDoor;
    }

    public List<Personeel> getGedemonteerdDoorList() {
        return gedemonteerdDoorList;
    }

    public void setGedemonteerdDoorList(List<Personeel> gedemonteerdDoorList) {
        this.gedemonteerdDoorList = gedemonteerdDoorList;
    }

    public List<Personeel> getHersteldDoorList() {
        return hersteldDoorList;
    }

    public void setHersteldDoorList(List<Personeel> hersteldDoorList) {
        this.hersteldDoorList = hersteldDoorList;
    }

    public boolean isZuurbehandeling() {
        return zuurbehandeling;
    }

    public void setZuurbehandeling(boolean zuurbehandeling) {
        this.zuurbehandeling = zuurbehandeling;
    }

    public boolean isZuurbehandelingUitgevoerd() {
        return zuurbehandelingUitgevoerd;
    }

    public void setZuurbehandelingUitgevoerd(boolean zuurbehandelingUitgevoerd) {
        this.zuurbehandelingUitgevoerd = zuurbehandelingUitgevoerd;
    }

    public Set<String> getControlBox() {
        return controlBox;
    }

    public void setControlBox(Set<String> controlBox) {
        this.controlBox = controlBox;
    }

    public Set<String> getMotorKabel() {
        return motorKabel;
    }

    public void setMotorKabel(Set<String> motorKabel) {
        this.motorKabel = motorKabel;
    }

    public Set<String> getVochtInMotor() {
        return vochtInMotor;
    }

    public void setVochtInMotor(Set<String> vochtInMotor) {
        this.vochtInMotor = vochtInMotor;
    }

    public Set<String> getMotorDichtingen() {
        return motorDichtingen;
    }

    public void setMotorDichtingen(Set<String> motorDichtingen) {
        this.motorDichtingen = motorDichtingen;
    }

    public Set<String> getSecundaireAsafdichting() {
        return secundaireAsafdichting;
    }

    public void setSecundaireAsafdichting(Set<String> secundaireAsafdichting) {
        this.secundaireAsafdichting = secundaireAsafdichting;
    }

    public String getSecundaireAsafdichtingCommentaar() {
        return secundaireAsafdichtingCommentaar;
    }

    public void setSecundaireAsafdichtingCommentaar(String secundaireAsafdichtingCommentaar) {
        this.secundaireAsafdichtingCommentaar = secundaireAsafdichtingCommentaar;
    }

    public String getStaatPompCommentaar() {
        return staatPompCommentaar;
    }

    public void setStaatPompCommentaar(String staatPompCommentaar) {
        this.staatPompCommentaar = staatPompCommentaar;
    }

    public String getPompBinnengebrachtCommentaar() {
        return pompBinnengebrachtCommentaar;
    }

    public void setPompBinnengebrachtCommentaar(String pompBinnengebrachtCommentaar) {
        this.pompBinnengebrachtCommentaar = pompBinnengebrachtCommentaar;
    }

    public String getMotorKabelCommentaar() {
        return motorKabelCommentaar;
    }

    public void setMotorKabelCommentaar(String motorKabelCommentaar) {
        this.motorKabelCommentaar = motorKabelCommentaar;
    }

    public String getWaterInOnderdeelCommentaar() {
        return waterInOnderdeelCommentaar;
    }

    public void setWaterInOnderdeelCommentaar(String waterInOnderdeelCommentaar) {
        this.waterInOnderdeelCommentaar = waterInOnderdeelCommentaar;
    }

    public boolean isGnNabehandeling() {
        return gnNabehandeling;
    }

    public void setGnNabehandeling(boolean gnNabehandeling) {
        this.gnNabehandeling = gnNabehandeling;
    }

    public String getVermogenPomp() {
        return vermogenPomp;
    }

    public void setVermogenPomp(String vermogenPomp) {
        this.vermogenPomp = vermogenPomp;
    }

    public String getqNomPomp() {
        return qNomPomp;
    }

    public void setqNomPomp(String qNomPomp) {
        this.qNomPomp = qNomPomp;
    }

    public String gethNomPomp() {
        return hNomPomp;
    }

    public void sethNomPomp(String hNomPomp) {
        this.hNomPomp = hNomPomp;
    }

    public String getWikkelWaarde1() {
        return wikkelWaarde1;
    }

    public void setWikkelWaarde1(String wikkelWaarde1) {
        this.wikkelWaarde1 = wikkelWaarde1;
    }

    public String getWikkelWaarde2() {
        return wikkelWaarde2;
    }

    public void setWikkelWaarde2(String wikkelWaarde2) {
        this.wikkelWaarde2 = wikkelWaarde2;
    }

    public String getWikkelWaarde3() {
        return wikkelWaarde3;
    }

    public void setWikkelWaarde3(String wikkelWaarde3) {
        this.wikkelWaarde3 = wikkelWaarde3;
    }
}
