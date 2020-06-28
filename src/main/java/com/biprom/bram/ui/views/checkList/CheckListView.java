package com.biprom.bram.ui.views.checkList;


import com.biprom.bram.backend.data.entity.mongodbEntities.CheckListBestek;
import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.MainTicket;
import com.biprom.bram.backend.mongoRepositories.MainTicketRepository;
import com.biprom.bram.backend.mongoRepositories.PersoneelRepository;
import com.biprom.bram.ui.views.CheckListDesign;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@SpringView(name = "checkList")
public class CheckListView extends CheckListDesign implements View {

    MainTicketRepository mainTicketRepository;
    PersoneelRepository personeelRepository;

    MainTicket geselecteerdMainTicket;
    DetailTicket geslecteerdDetailTicket;

    Binder<CheckListBestek>checkListBestekBinder = new Binder<>(  );
    Binder<DetailTicket>detailTicketBinder = new Binder<>(  );

    Iterator<String>stringIterator;

    Boolean bNoDoubleEvents = false;

    List<Integer> amNummerList = new ArrayList<>(  );
    File noImageFile = new File("/data/kabba/amcalPics/5.jpeg");
    File file1Aansluiting = new File("/data/kabba/amcalPics/1.jpeg");
    File file2Aansluiting = new File("/data/kabba/amcalPics/2.jpeg");
    File file3Aansluiting = new File("/data/kabba/amcalPics/3.jpeg");
    File file4Aansluiting = new File("/data/kabba/amcalPics/4.jpeg");

    FileResource noImageResource = new FileResource(noImageFile);
    FileResource imgResource1Aansluiting = new FileResource(file1Aansluiting);
    FileResource imgResource2Aansluiting = new FileResource(file2Aansluiting);
    FileResource imgResource3Aansluiting = new FileResource(file3Aansluiting);
    FileResource imgResource4Aansluiting = new FileResource(file4Aansluiting);

    FileResource[] fileResourcesArrayAansluiting = {noImageResource,imgResource1Aansluiting, imgResource2Aansluiting, imgResource3Aansluiting, imgResource4Aansluiting};


    File file1Ontluchting = new File("/data/kabba/amcalPics/1o.jpeg");
    File file2Ontluchting = new File("/data/kabba/amcalPics/2o.jpeg");
    File file3Ontluchting = new File("/data/kabba/amcalPics/3o.jpeg");
    File file4Ontluchting = new File("/data/kabba/amcalPics/4o.jpeg");

    FileResource imgResource1Ontluchting = new FileResource(file1Ontluchting);
    FileResource imgResource2Ontluchting = new FileResource(file2Ontluchting);
    FileResource imgResource3Ontluchting = new FileResource(file3Ontluchting);
    FileResource imgResource4Ontluchting = new FileResource(file4Ontluchting);

    FileResource[] fileResourcesArrayOntluchting = {noImageResource,imgResource1Ontluchting, imgResource2Ontluchting, imgResource3Ontluchting, imgResource4Ontluchting};


    File file1Platen = new File("/data/kabba/amcalPics/1p.jpeg");
    File file2Platen = new File("/data/kabba/amcalPics/2p.jpeg");

    FileResource imgResource1Platen = new FileResource(file1Platen);
    FileResource imgResource2Platen = new FileResource(file2Platen);

    FileResource[] fileResourcesArrayPlaten = {noImageResource,imgResource1Platen, imgResource2Platen};

    @Autowired
    public CheckListView(MainTicketRepository mainTicketRepository,
                         PersoneelRepository personeelRepository) {

        this.mainTicketRepository = mainTicketRepository;
        this.personeelRepository = personeelRepository;

        setUpCheckBoxGroupTechniekers();
        setUpCBPositieMagazijn();
        setUpPositieAansluitingen();
        setUpBinder();
        setUpCBAansluitingen();
        setUpCheckBoxGroupValueChangeListeners();
    }

    private void setUpCBAansluitingen() {

        cbElektrischeAansluiting.addValueChangeListener(e -> {
            imgElektrischeAansluiting.setSource(fileResourcesArrayAansluiting[getImageNumber(e.getValue())]);
        });
        cbZijdeOntluchting.addValueChangeListener(e -> {
            imgOntluchtingAansluiting.setSource(fileResourcesArrayOntluchting[getImageNumber(e.getValue())]);
        });
        cbMontagePlaten.addValueChangeListener(e -> {
            imgMontagePlatenAansluiting.setSource(fileResourcesArrayPlaten[getImageNumber(e.getValue())]);
        });
    }

    private void setUpPositieAansluitingen() {
        cbElektrischeAansluiting.setItems( "1","2","3","4" );
        cbElektrischeAansluiting.setItemCaptionGenerator( x -> {
            return getProperName(x);
        } );
        cbZijdeOntluchting.setItems( "1o","2o","3o","4o" );
        cbZijdeOntluchting.setItemCaptionGenerator( x -> {
            return getProperName(x);
        } );
        cbMontagePlaten.setItems( "1p","2p" );
        cbMontagePlaten.setItemCaptionGenerator( x -> {
            return getProperName(x);
        } );
    }



    private void setUpCheckBoxGroupTechniekers() {
        checkbGroupTechniekers.setItems( personeelRepository.findAll().stream().collect( Collectors.toSet()));
        checkbGroupTechniekers.setItemDescriptionGenerator( x -> x.getVoorNaam() + " " + x.getAchterNaam() );
        checkbGroupTechniekers.setItemCaptionGenerator( x -> x.getInlogNaam() );
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String[] parameters = event.getParameters().split( "/" );
        try{
            geselecteerdMainTicket = mainTicketRepository.findByDetails_AmNummerContains( parameters[0] ).get( 0 );
        }
        catch (Exception e){
            Notification.show( "Ticket voor dit detail is niet gevonden" );
        }

        if ("".equals(parameters)) {
            titleLabel.setValue( "" );
        } else {
            titleLabel.setValue(  parameters[0] + " : " +  parameters[1]);
            vraagKlantLabel.setValue( "Opdracht van klant : " +geselecteerdMainTicket.getVraagKlant() );
        }
        geslecteerdDetailTicket = geselecteerdMainTicket.getDetails().stream().filter( x -> x.getamNummer().matches( parameters[0] ) ).findFirst().get();
        checkListBestekBinder.setBean( geslecteerdDetailTicket.getCheckListBestek() );
        detailTicketBinder.setBean( geslecteerdDetailTicket );

        checkMotorOfPompOK();

    }

    private void setUpCBPositieMagazijn() {
        cbPositieMagazijn.setItems( "A1" , "A2", "A3", "A4",
                "B0","B1" , "B2", "B3", "B4",
                "C0","C1" , "C2", "C3", "C4",
                "D0" , "D1" , "D2", "D3", "D4",
                "E1" , "E2",
                "F1" , "F2",
                "G1" , "G2",
                "H1" , "H2",
                "I1", "I2","I3");

    }

    private void setUpBinder() {
        checkListBestekBinder.forField( checkbGroupTechniekers )
                .bind( x -> x.getGedemonteerdDoorList().stream().collect( Collectors.toSet()),(x, y)-> x.setGedemonteerdDoorList( y.stream().collect( Collectors.toList()) )  );
        checkListBestekBinder.forField( cbgEUPallet )
                .bind(x -> x.getBinnengebrachtOp(),(x,y) -> x.setBinnengebrachtOp( y ) );
        checkListBestekBinder.forField( tfBinnengebrachtAndereManier )
                .bind( CheckListBestek::getBinnengebrachtOpAndere, CheckListBestek::setBinnengebrachtOpAndere  );
        checkListBestekBinder.forField( cbPositieMagazijn )
                .bind( CheckListBestek::getPositieMagazijn, CheckListBestek::setPositieMagazijn );
        checkListBestekBinder.forField( tfAnderePositie )
                .bind( CheckListBestek::getAnderPositie, CheckListBestek::setAnderPositie );
        checkListBestekBinder.forField( cbElektrischeAansluiting )
                .bind( x -> String.valueOf(x.getZijdeElektrischeAansluiting()), (x,y) -> x.setZijdeElektrischeAansluiting(getImageNumber(y)) );
        checkListBestekBinder.forField( cbZijdeOntluchting )
                .bind( x -> String.valueOf(x.getZijdeOntluchting()), (x,y) -> x.setZijdeOntluchting(getImageNumber(y)) );
        checkListBestekBinder.forField( cbMontagePlaten )
                .bind( x -> String.valueOf(x.getZijdeMontagePlaten()), (x,y) -> x.setZijdeMontagePlaten(getImageNumber(y)) );
        checkListBestekBinder.forField( taBijkomendCommentaarPomp )
                .bind( CheckListBestek::getCommentaarPomp, CheckListBestek::setCommentaarPomp );
        checkListBestekBinder.forField( tfUitGebreidPompType )
                .bind( CheckListBestek::getUitgebreidPompType, CheckListBestek::setUitgebreidPompType );
        checkListBestekBinder.forField( tfArtikelNummerPomp )
                .bind( CheckListBestek::getArtikelNummerPomp, CheckListBestek::setArtikelNummerPomp );
        checkListBestekBinder.forField( tfProductDatumPomp )
                .bind( CheckListBestek::getProductieDatumPomp, CheckListBestek::setProductieDatumPomp );
        checkListBestekBinder.forField( tfSerieNummerPomp )
                .bind( CheckListBestek::getSerieNummerPomp, CheckListBestek::setSerieNummerPomp );
        checkListBestekBinder.forField( tfLandPomp )
                .bind( CheckListBestek::getPompLand, CheckListBestek::setPompLand );
        checkListBestekBinder.forField( tfMotorType )
                .bind( CheckListBestek::getMotorType, CheckListBestek::setMotorType );
        checkListBestekBinder.forField( tfNominaalVermogen )
                .bind( CheckListBestek::getNominaalVermogen, CheckListBestek::setNominaalVermogen );
        checkListBestekBinder.forField( tfToerental )
                .bind( CheckListBestek::getToerental, CheckListBestek::setToerental );
        checkListBestekBinder.forField( tfArtikelNummerMotor )
                .bind( CheckListBestek::getArtikelNummerMotor, CheckListBestek::setArtikelNummerMotor );
        checkListBestekBinder.forField( tfUDriehoek )
                .bind( CheckListBestek::getUdriehoek, CheckListBestek::setUdriehoek );
        checkListBestekBinder.forField( tfUSter )
                .bind( CheckListBestek::getUster, CheckListBestek::setUster );
        checkListBestekBinder.forField( cbFreqController )
                .bind( CheckListBestek::isFreqRegelaar, CheckListBestek::setFreqRegelaar );
        checkListBestekBinder.forField( cbNietStandardPomp )
                .bind( CheckListBestek::isNietStandaardPomp, CheckListBestek::setNietStandaardPomp );
        checkListBestekBinder.forField( tfProductieDatumMotor )
                .bind( CheckListBestek::getProductieDatumMotor, CheckListBestek::setProductieDatumMotor );
        checkListBestekBinder.forField( tfIsolatieWeerstand )
                .bind( x -> x.getRisolatie(), (x,y)-> x.setRisolatie( y ) );
        checkListBestekBinder.forField( tfRfase1 )
                .bind( CheckListBestek::getRfase1, CheckListBestek::setRfase1 );
        checkListBestekBinder.forField( tfRfase2 )
                .bind( CheckListBestek::getRfase2, CheckListBestek::setRfase2 );
        checkListBestekBinder.forField( tfRfase3 )
                .bind( CheckListBestek::getRfase3, CheckListBestek::setRfase3 );
        checkListBestekBinder.forField( cbgMotorElektrischInOrde )
                .bind( x -> x.getMotorMechElektrInOrde().stream().collect( Collectors.toSet()),(x, y)-> x.setMotorMechElektrInOrde( y ) );

        checkListBestekBinder.forField( cbgIsolatieweerstand )
                .bind( x -> x.getIsolatieWeerstand().stream().collect( Collectors.toSet()),(x, y)-> x.setIsolatieWeerstand( y )  );
        checkListBestekBinder.forField( cbgWikkelingswaardes )
                .bind( x -> x.getWikkelingsWaardes().stream().collect( Collectors.toSet()),(x, y)-> x.setWikkelingsWaardes( y )  );
        checkListBestekBinder.forField( cbgMotorVerbrand )
                .bind( x -> x.getMotorVerbrand().stream().collect( Collectors.toSet()),(x, y)-> x.setMotorVerbrand( y )  );
        checkListBestekBinder.forField( cbgControlBox )
                .bind( x -> x.getControlBox().stream().collect( Collectors.toSet()),(x, y)-> x.setControlBox( y)  );
        checkListBestekBinder.forField( cbgWaterInMotor )
                .bind( x -> x.getWaterInMotor().stream().collect( Collectors.toSet()),(x, y)-> x.setWaterInMotor( y )  );
        checkListBestekBinder.forField( cbgMotorkabel )
                .bind( x -> x.getMotorKabel().stream().collect( Collectors.toSet()),(x, y)-> x.setMotorKabel( y )  );
        checkListBestekBinder.forField( cbgVochtInMotor )
                .bind( x -> x.getVochtInMotor().stream().collect( Collectors.toSet()),(x, y)-> x.setVochtInMotor( y )  );
        checkListBestekBinder.forField( cbgMotorLagers )
                .bind( x -> x.getMotorLagers().stream().collect( Collectors.toSet()),(x, y)-> x.setMotorLagers( y )  );
        checkListBestekBinder.forField( cbgMotorDichtingen )
                .bind( x -> x.getMotorDichtingen().stream().collect( Collectors.toSet()),(x, y)-> x.setMotorDichtingen( y )  );
        checkListBestekBinder.forField( cbgVentilator )
                .bind( x -> x.getVentilator().stream().collect( Collectors.toSet()),(x, y)-> x.setVentilator( y )  );
        checkListBestekBinder.forField( cbgBeschadingMotor )
                .bind( x -> x.getRotorStatorFlens().stream().collect( Collectors.toSet()),(x, y)-> x.setRotorStatorFlens( y )  );

        checkListBestekBinder.forField( cbgGarantie )
                .bind( x -> x.getGarantie().stream().collect( Collectors.toSet()),(x, y)-> x.setGarantie( y )  );
        checkListBestekBinder.forField( cbgBinnengebrachtPomp )
                .bind( x -> x.getPompBinnengebracht().stream().collect( Collectors.toSet()),(x, y)-> x.setPompBinnengebracht( y )  );
        checkListBestekBinder.forField( cbgStaatPomp )
                .bind( x -> x.getPompstaat().stream().collect( Collectors.toSet()),(x, y)-> x.setPompstaat( y )  );
        checkListBestekBinder.forField( cbgPrimaireAsafdichtingen )
                .bind( x -> x.getAsafdichting().stream().collect( Collectors.toSet()),(x, y)-> x.setAsafdichting( y )  );
        checkListBestekBinder.forField( cbgSecundaireAsafdichtingen )
                .bind( x -> x.getSecundaireAsafdichting().stream().collect( Collectors.toSet()),(x, y)-> x.setSecundaireAsafdichting( y )  );

        checkListBestekBinder.forField( cbgPompas )
                .bind( x -> x.getPompas().stream().collect( Collectors.toSet()),(x, y)-> x.setPompas( y )  );
        checkListBestekBinder.forField( cbgWaaiers )
                .bind( x -> x.getWaaiers().stream().collect( Collectors.toSet()),(x, y)-> x.setWaaiers( y )  );
        checkListBestekBinder.forField( cbgKamers )
                .bind( x -> x.getKamers().stream().collect( Collectors.toSet()),(x, y)-> x.setKamers( y )  );
        checkListBestekBinder.forField( cbgDichtingen )
                .bind( x -> x.getDichtingen().stream().collect( Collectors.toSet()),(x, y)-> x.setDichtingen( y )  );
        checkListBestekBinder.forField( cbgAantasting )
                .bind( x -> x.getAantastingen().stream().collect( Collectors.toSet()),(x, y)-> x.setAantastingen( y )  );
        checkListBestekBinder.forField( cbgLagers )
                .bind( x -> x.getLagers().stream().collect( Collectors.toSet()),(x, y)-> x.setLagers( y )  );
        checkListBestekBinder.forField( cbgSpaltringen )
                .bind( x -> x.getSpaltringen().stream().collect( Collectors.toSet()),(x, y)-> x.setSpaltringen( y )  );
        checkListBestekBinder.forField( cbgBinnenwerk )
                .bind( x -> x.getBinnenwerk().stream().collect( Collectors.toSet()),(x, y)-> x.setBinnenwerk( y )  );
        checkListBestekBinder.forField( cbgVuilInPomp )
                .bind( x -> x.getContaminatie().stream().collect( Collectors.toSet()),(x, y)-> x.setContaminatie( y )  );
        checkListBestekBinder.forField( cbgStatusPomp )
                .bind( x -> x.getPompStatus().stream().collect( Collectors.toSet()),(x, y)-> x.setPompStatus( y )  );

        checkListBestekBinder.forField( tfExtraCommentaarWaterInMotor )
                .bind( CheckListBestek::getWaterInOnderdeelCommentaar, CheckListBestek::setWaterInOnderdeelCommentaar );
        checkListBestekBinder.forField( tfCommentaarMotorkabel )
                .bind( CheckListBestek::getMotorKabelCommentaar, CheckListBestek::setMotorKabelCommentaar );
        checkListBestekBinder.forField( tfLagerAsMotorCommentaar )
                .bind( CheckListBestek::getLagersMotorCommentaar, CheckListBestek::setLagersMotorCommentaar );
        checkListBestekBinder.forField( tfLagerVentMotorCommentaar1 )
                .bind( CheckListBestek::getLagersMotorVentilatorCommentaar, CheckListBestek::setLagersMotorVentilatorCommentaar );
        checkListBestekBinder.forField( tfCommentaarPompBinnengebracht )
                .bind( CheckListBestek::getPompBinnengebrachtCommentaar, CheckListBestek::setPompBinnengebrachtCommentaar );
        checkListBestekBinder.forField( tfCommentaarPompDemonteerbaar )
                .bind( CheckListBestek::getStaatPompCommentaar, CheckListBestek::setStaatPompCommentaar );
        checkListBestekBinder.forField( tfPrimaireAsafdichtingCommentaar )
                .bind( CheckListBestek::getAsafdichtingCommentaar, CheckListBestek::setAsafdichtingCommentaar );
        checkListBestekBinder.forField( tfSecundaireAsafdichtingCommentaar )
                .bind( CheckListBestek::getSecundaireAsafdichtingCommentaar, CheckListBestek::setSecundaireAsafdichtingCommentaar );
        checkListBestekBinder.forField( tfPompasCommentaar )
                .bind( CheckListBestek::getPompasCommentaar, CheckListBestek::setPompasCommentaar );
        checkListBestekBinder.forField( tfWaaiersCommentaar )
                .bind( CheckListBestek::getWaaiersCommentaar, CheckListBestek::setWaaiersCommentaar );
        checkListBestekBinder.forField( tfKamersCommentaar )
                .bind( CheckListBestek::getKamersCommentaar, CheckListBestek::setKamersCommentaar );
        checkListBestekBinder.forField( tfDichtingenCommentaar )
                .bind( CheckListBestek::getDichtingenCommentaar, CheckListBestek::setDichtingenCommentaar );
        checkListBestekBinder.forField( tfAantastingCommentaar )
                .bind( CheckListBestek::getAantastingCommentaar, CheckListBestek::setAantastingCommentaar );
        checkListBestekBinder.forField( tfLagersCommentaar )
                .bind( CheckListBestek::getLagersPompCommentaar, CheckListBestek::setLagersPompCommentaar );
        checkListBestekBinder.forField( tfSpaltRingenCommentaar )
                .bind( CheckListBestek::getSpaltRingenCommentaar, CheckListBestek::setSpaltRingenCommentaar );
        checkListBestekBinder.forField( tfPompStatusCommentaar )
                .bind( CheckListBestek::getPompStatusCommentaar, CheckListBestek::setPompStatusCommentaar );

        checkListBestekBinder.forField( dateDemontage )
                .bind( x -> x.getDatumDemontage(), (x,y) -> x.setDatumDemontage( y ) );
        checkListBestekBinder.forField( tfUrenDemontage )
                .bind( x -> x.getAantalUrenDemontage(), (x,y) -> x.setAantalUrenDemontage( y ) );
        checkListBestekBinder.forField( checkbBestekkostenAanrekenen )
                .bind( x -> x.isBestekKostenAanrekenen(), (x,y) -> x.setBestekKostenAanrekenen( y ) );

        checkListBestekBinder.forField( datefHerstelDatum )
                .bind( x -> x.getDatumHerstel(), (x,y) -> x.setDatumHerstel( y ) );
        checkListBestekBinder.forField( dtUitersteHerstelDatum )
                .bind( x -> x.getUitersteHerstelDatum(), (x,y) -> x.setUitersteHerstelDatum( y ) );
        checkListBestekBinder.forField( tfUrenHerstelling )
                .bind( x -> x.getAantalUrenHerstelling(), (x,y) -> x.setAantalUrenHerstelling( y ) );
        checkListBestekBinder.forField( tfUrenReinigen )
                .bind( x -> x.getAantalUrenReiniging(), (x,y) -> x.setAantalUrenReiniging( y ) );
        detailTicketBinder.forField( checkbHerstellingAfgewerkt )
                .bind( x -> x.isbPompHersteld(), (x,y) -> x.setbPompHersteld( y ) );

        checkListBestekBinder.forField( tfDebiet )
                .bind( x -> x.getMeetresultaatDebiet(), (x,y) -> x.setMeetresultaatDebiet( y ) );
        checkListBestekBinder.forField( tfDruk )
                .bind( x -> x.getMeetresultaatDruk(), (x,y) -> x.setMeetresultaatDruk( y ) );
        checkListBestekBinder.forField( tfDebietVuilWater )
                .bind( x -> x.getDrukTestVuilwaterDruk(), (x,y) -> x.setDrukTestVuilwaterDruk( y ) );

        checkListBestekBinder.addValueChangeListener( x -> {
            mainTicketRepository.save( geselecteerdMainTicket );
        } );
        detailTicketBinder.addValueChangeListener( x -> {
            mainTicketRepository.save( geselecteerdMainTicket );
        } );
    }

    public void checkMotorOfPompOK(){

        if(bNoDoubleEvents == false) {

        bNoDoubleEvents = true;
            stringIterator = cbgMotorElektrischInOrde.getValue().iterator();
            while (stringIterator.hasNext()) {
                String stringToCompare = stringIterator.next();

                if (stringToCompare.matches("Moteur électrique OK")) {
                    vLayoutMotorElektrisch.setVisible(false);
                    cbgMotorElektrischInOrde.deselect("Moteur électrique NOK");
                } else if (stringToCompare.matches("Moteur électrique NOK")) {
                    vLayoutMotorElektrisch.setVisible(true);
                    cbgMotorElektrischInOrde.deselect("Moteur électrique OK");
                    //cbgMotorElektrischInOrde.setStyleName("horizontal optionGroupRedStyle");
                }
                if (stringToCompare.matches("Moteur mécanique OK")) {
                    vLayoutMotorMechanisch.setVisible(false);
                    cbgMotorElektrischInOrde.deselect("Moteur mécanique NOK");
                } else if (stringToCompare.matches("Moteur mécanique NOK")) {
                    vLayoutMotorMechanisch.setVisible(true);
                    cbgMotorElektrischInOrde.deselect("Moteur mécanique OK");
                    //cbgMotorElektrischInOrde.setStyleName("horizontal optionGroupGreenStyle");
                }
            }
            bNoDoubleEvents = false;
        }


    }

    private String getProperName(String toConvert) {
        String returnValue = "";
        switch (toConvert) {
            case "1":
                returnValue = "Links";
                break;
            case "1o":
                returnValue = "Links";
                break;
            case "1p":
                returnValue = "Links";
                break;
            case "2":
                returnValue = "Boven";
                break;
            case "2o":
                returnValue = "Boven";
                break;
            case "2p":
                returnValue = "Boven";
                break;
            case "3":
                returnValue = "Rechts";
                break;
            case "3o":
                returnValue = "Rechts";
                break;
            case "4":
                returnValue = "Onder";
                break;
            case "4o":
                returnValue = "Onder";
                break;
        }
        return returnValue;
    }

    private Integer getImageNumber(String toConvert) {
        Integer returnValue = 0;
        switch (toConvert) {
            case "1":
                returnValue = 1;
                break;
            case "1o":
                returnValue = 1;
                break;
            case "1p":
                returnValue = 1;
                break;
            case "2":
                returnValue = 2;
                break;
            case "2o":
                returnValue = 2;
                break;
            case "2p":
                returnValue = 2;
                break;
            case "3":
                returnValue = 3;
                break;
            case "3o":
                returnValue = 3;
                break;
            case "4":
                returnValue = 4;
                break;
            case "4o":
                returnValue = 4;
                break;
        }
        return returnValue;
    }

    public void setStyleToAllCheckBoxGroups(){
        while (hLaytoutMotorElektrischInOrde.iterator().hasNext()){
            Component componentToCheck = iterator().next();
            if(componentToCheck.equals(CheckBoxGroup.class)){
                setStyleToComponent((CheckBoxGroup) componentToCheck);
            }
        }
        while (vLayoutMotorElektrisch.iterator().hasNext()){
            Component componentToCheck = iterator().next();
            if(componentToCheck.equals(CheckBoxGroup.class)){
                setStyleToComponent((CheckBoxGroup) componentToCheck);
            }
        }
        while (vLayoutMotorMechanisch.iterator().hasNext()){
            Component componentToCheck = iterator().next();
            if(componentToCheck.equals(CheckBoxGroup.class)){
                setStyleToComponent((CheckBoxGroup) componentToCheck);
            }
        }
        while (vLayoutPompeigenschappen.iterator().hasNext()){
            Component componentToCheck = iterator().next();
            if(componentToCheck.equals(CheckBoxGroup.class)){
                setStyleToComponent((CheckBoxGroup) componentToCheck);
            }
        }
    }

    public void setStyleToComponent(CheckBoxGroup checkboxgroupToCheck){
        if(checkboxgroupToCheck.getValue().iterator().hasNext() == true){
            checkboxgroupToCheck.setStyleName("horizontal optionGroupGreenStyle");
        }
        else{
            checkboxgroupToCheck.setStyleName("horizontal optionGroupRedStyle");
        }
    }
    private void setUpCheckBoxGroupValueChangeListeners() {
        cbgMotorElektrischInOrde.addValueChangeListener(x -> {

            checkMotorOfPompOK();

            if(cbgMotorElektrischInOrde.getValue().isEmpty()){
                cbgMotorElektrischInOrde.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgMotorElektrischInOrde.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgIsolatieweerstand.addValueChangeListener(x -> {
            if(cbgIsolatieweerstand.getValue().isEmpty()){
                cbgIsolatieweerstand.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgIsolatieweerstand.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgMotorVerbrand.addValueChangeListener(x -> {
            if(cbgMotorVerbrand.getValue().isEmpty()){
                cbgMotorVerbrand.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgMotorVerbrand.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgControlBox.addValueChangeListener(x -> {
            if(cbgControlBox.getValue().isEmpty()){
                cbgControlBox.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgControlBox.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgWaterInMotor.addValueChangeListener(x -> {
            if(cbgWaterInMotor.getValue().isEmpty()){
                cbgWaterInMotor.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgWaterInMotor.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgMotorkabel.addValueChangeListener(x -> {
            if(cbgMotorkabel.getValue().isEmpty()){
                cbgMotorkabel.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgMotorkabel.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgVochtInMotor.addValueChangeListener(x -> {
            if(cbgVochtInMotor.getValue().isEmpty()){
                cbgVochtInMotor.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgVochtInMotor.setStyleName("horizontal optionGroupGreenStyle");
            }
        });

        cbgMotorLagers.addValueChangeListener(x -> {
            if(cbgMotorLagers.getValue().isEmpty()){
                cbgMotorLagers.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgMotorLagers.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgMotorDichtingen.addValueChangeListener(x -> {
            if(cbgMotorDichtingen.getValue().isEmpty()){
                cbgMotorDichtingen.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgMotorDichtingen.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgVentilator.addValueChangeListener(x -> {
            if(cbgVentilator.getValue().isEmpty()){
                cbgVentilator.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgVentilator.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgBeschadingMotor.addValueChangeListener(x -> {
            if(cbgBeschadingMotor.getValue().isEmpty()){
                cbgBeschadingMotor.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgBeschadingMotor.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgBinnengebrachtPomp.addValueChangeListener(x -> {
            if(cbgBinnengebrachtPomp.getValue().isEmpty()){
                cbgBinnengebrachtPomp.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgBinnengebrachtPomp.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgStaatPomp.addValueChangeListener(x -> {
            if(cbgStaatPomp.getValue().isEmpty()){
                cbgStaatPomp.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgStaatPomp.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgPrimaireAsafdichtingen.addValueChangeListener(x -> {
            if(cbgPrimaireAsafdichtingen.getValue().isEmpty()){
                cbgPrimaireAsafdichtingen.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgPrimaireAsafdichtingen.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgSecundaireAsafdichtingen.addValueChangeListener(x -> {
            if(cbgSecundaireAsafdichtingen.getValue().isEmpty()){
                cbgSecundaireAsafdichtingen.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgSecundaireAsafdichtingen.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgPompas.addValueChangeListener(x -> {
            if(cbgPompas.getValue().isEmpty()){
                cbgPompas.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgPompas.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgWaaiers.addValueChangeListener(x -> {
            if(cbgWaaiers.getValue().isEmpty()){
                cbgWaaiers.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgWaaiers.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgKamers.addValueChangeListener(x -> {
            if(cbgKamers.getValue().isEmpty()){
                cbgKamers.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgKamers.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgDichtingen.addValueChangeListener(x -> {
            if(cbgDichtingen.getValue().isEmpty()){
                cbgDichtingen.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgDichtingen.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgAantasting.addValueChangeListener(x -> {
            if(cbgAantasting.getValue().isEmpty()){
                cbgAantasting.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgAantasting.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgLagers.addValueChangeListener(x -> {
            if(cbgLagers.getValue().isEmpty()){
                cbgLagers.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgLagers.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgSpaltringen.addValueChangeListener(x -> {
            if(cbgSpaltringen.getValue().isEmpty()){
                cbgSpaltringen.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgSpaltringen.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgBinnenwerk.addValueChangeListener(x -> {
            if(cbgBinnenwerk.getValue().isEmpty()){
                cbgBinnenwerk.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgBinnenwerk.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgVuilInPomp.addValueChangeListener(x -> {
            if(cbgVuilInPomp.getValue().isEmpty()){
                cbgVuilInPomp.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgVuilInPomp.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgStatusPomp.addValueChangeListener(x -> {
            if(cbgStatusPomp.getValue().isEmpty()){
                cbgStatusPomp.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgStatusPomp.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        cbgPompBehandeling.addValueChangeListener(x -> {
            if(cbgPompBehandeling.getValue().isEmpty()){
                cbgPompBehandeling.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgPompBehandeling.setStyleName("horizontal optionGroupGreenStyle");
            }
        });

    }

}
