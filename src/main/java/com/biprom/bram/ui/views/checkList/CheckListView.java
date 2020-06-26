package com.biprom.bram.ui.views.checkList;


import com.biprom.bram.backend.data.entity.mongodbEntities.CheckListBestek;
import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.MainTicket;
import com.biprom.bram.backend.mongoRepositories.MainTicketRepository;
import com.biprom.bram.backend.mongoRepositories.PersoneelRepository;
import com.biprom.bram.ui.views.CheckListDesign;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@SpringView(name = "checkList")
public class CheckListView extends CheckListDesign implements View {

    MainTicketRepository mainTicketRepository;
    PersoneelRepository personeelRepository;

    MainTicket geselecteerdMainTicket;
    DetailTicket geslecteerdDetailTicket;

    Binder<CheckListBestek>checkListBestekBinder = new Binder<>(  );
    Binder<DetailTicket>detailTicketBinder = new Binder<>(  );

    @Autowired
    public CheckListView(MainTicketRepository mainTicketRepository,
                         PersoneelRepository personeelRepository) {

        this.mainTicketRepository = mainTicketRepository;
        this.personeelRepository = personeelRepository;

        setUpCheckBoxGroupTechniekers();
        setUpCBPositieMagazijn();
        setUpPositieAansluitingen();
        setUpBinder();
    }

    private void setUpPositieAansluitingen() {
        cbElektrischeAansluiting.setItems( 1,2,3,4 );
        cbElektrischeAansluiting.setItemCaptionGenerator( x -> {
            return getProperName(x);
        } );
        cbZijdeOntluchting.setItems( 1,2,3,4 );
        cbZijdeOntluchting.setItemCaptionGenerator( x -> {
            return getProperName(x);
        } );
        cbMontagePlaten.setItems( 1,2 );
        cbMontagePlaten.setItemCaptionGenerator( x -> {
            return getProperName(x);
        } );
    }

    private String getProperName(Integer integer) {
        String returnValue = "";
        switch (integer) {
            case 1:
                returnValue = "Links";
            break;
            case 2:
                returnValue = "Boven";
            break;
            case 3:
                returnValue = "Rechts";
            break;
            case 4:
                returnValue = "Onder";
            break;
        }
        return returnValue;
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
                .bind( CheckListBestek::getZijdeElektrischeAansluiting, CheckListBestek::setZijdeElektrischeAansluiting );
        checkListBestekBinder.forField( cbZijdeOntluchting )
                .bind( CheckListBestek::getZijdeOntluchting, CheckListBestek::setZijdeOntluchting );
        checkListBestekBinder.forField( cbMontagePlaten )
                .bind( CheckListBestek::getZijdeMontagePlaten, CheckListBestek::setZijdeMontagePlaten );
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

}
