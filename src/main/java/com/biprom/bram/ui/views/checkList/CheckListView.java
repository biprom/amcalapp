package com.biprom.bram.ui.views.checkList;


import com.biprom.bram.backend.data.entity.mongodbEntities.CheckListBestek;
import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.MainTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.Product;
import com.biprom.bram.backend.mongoRepositories.MainTicketRepository;
import com.biprom.bram.backend.mongoRepositories.PersoneelRepository;
import com.biprom.bram.ui.views.CheckListDesign;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@SpringView(name = "checkList")
public class CheckListView extends CheckListDesign implements View {

    MainTicketRepository mainTicketRepository;
    PersoneelRepository personeelRepository;

    MainTicket geselecteerdMainTicket;
    DetailTicket geslecteerdDetailTicket;

    Binder<CheckListBestek>checkListBestekBinder = new Binder<>(  );
    Binder<DetailTicket>detailTicketBinder = new Binder<>(  );
    
    List<Product>productList = new ArrayList<>();

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
        setUpRadioButtonsPompElktrMechOk();
        setUpBinder();
        setUpCBAansluitingen();
        setUpCheckBoxGroupValueChangeListeners();
        setUpbChangeCheckList();
        setUpGrid();
    }

    private void setUpGrid() {

            productGrid.addComponentColumn(e ->{
            TextField tfChangeAantalVoorspeldOfferte = new TextField( "Geschat aantal" );
            tfChangeAantalVoorspeldOfferte.setValue( e.getAantalVoorspeldOfferte().toString() );
            String value = e.getBruto();
            tfChangeAantalVoorspeldOfferte.addValueChangeListener( f -> {
                try{
                    e.setWerkelijkAantalVerbruikt( Double.valueOf( tfChangeAantalVoorspeldOfferte.getValue() ) );
                    e.setAantalVoorspeldOfferte( Double.valueOf( tfChangeAantalVoorspeldOfferte.getValue() ) );
                }
                catch (Exception g){
                    Notification.show("Aantal voorspeld offerte is niet ingevuld") ;
                }

            });
            return tfChangeAantalVoorspeldOfferte;
        }).setSortable( false ).setCaption( "Aantal voorspeld?" ).setId("geschataantal");

        productGrid.addComponentColumn( e -> {
            TextField tf = new TextField();
            tf.setValue( e.getArtikelNummer()  );
            if(e.getJaar().contains("2020")){
                tf.setStyleName("my_green_style");
            }
            else{
                tf.setStyleName("my_orange_style");
            }
            return tf;
        } ).setSortable( false ).setCaption( "Artikelnummer" ).setId("Artikelnummer").setHidden( false );

        productGrid.addComponentColumn(e -> {
            TextField tfEigenOmschrijving = new TextField("Eigen omschrijving");
            tfEigenOmschrijving.setWidth( "100%" );
            if(e.getEigenOmschrijving() != null){
                tfEigenOmschrijving.setValue(e.getEigenOmschrijving());
            }
            tfEigenOmschrijving.setValueChangeMode(ValueChangeMode.BLUR);
            tfEigenOmschrijving.addValueChangeListener(f -> e.setEigenOmschrijving(f.getValue()));
            productGrid.getDataProvider().refreshAll();
            geslecteerdDetailTicket.setBenodigdheden((ArrayList<Product>) productList);
            mainTicketRepository.save( geselecteerdMainTicket );
            return tfEigenOmschrijving;
        }).setSortable( false ).setCaption("Eigen omschrijving").setId( "eigen omschrijving" );

        productGrid.addComponentColumn( e -> {
            TextField tfVageOmschrijving = new TextField("Vage omschrijving");
            tfVageOmschrijving.setWidth( "100%" );
            if(e.getVageOmschrijving() !=null){
                tfVageOmschrijving.setValue( e.getVageOmschrijving() );
            }
            tfVageOmschrijving.addValueChangeListener( f -> e.setVageOmschrijving( f.getValue() ) );
//            tfVageOmschrijving.addBlurListener( f -> {
//                if(!geselecteerdMateriaalLijst.get( geselecteerdMateriaalLijst.size() - 1 ).getVageOmschrijving().matches("" )){
//                    geselecteerdMateriaalLijst.add( new Product() );
//                    tbBenodigdMateriaal.setItems( geselecteerdMateriaalLijst );
//                }
//                else{
//                    //Notification.show( "blurlistener does not work" );
//                }
//            } );
            return tfVageOmschrijving;

        } ).setSortable( false ).setCaption( "Vage omschrijivng" ).setId( "Vage omschrijving" );

        productGrid.addComponentColumn( item -> {
            CheckBox checkBox = new CheckBox(  );
            if(item.getOptie() != null){
                checkBox.setValue( item.getOptie() );
            }
            else{
                checkBox.setValue( false );
                item.setOptie( false );
            }
            checkBox.addValueChangeListener( e -> {
                item.setOptie( e.getValue() );
            } );
            return checkBox;
        } ).setSortable( false ).setCaption( "Als optie" ).setId( "optie" );

        productGrid.addComponentColumn( item -> {
            Button button = new Button("Voeg artikel toe");
            button.addClickListener(x -> {
                productList.add(new Product());
                productGrid.getDataProvider().refreshAll();
            });
            button.setStyleName("primary");
            return button;
        });

        productGrid.addComponentColumn( item -> {
            Button button = new Button("Verwijder artikel");
            button.addClickListener(x -> {
                productList.remove(item);
                productGrid.getDataProvider().refreshAll();
                geslecteerdDetailTicket.setBenodigdheden((ArrayList<Product>) productList);
                mainTicketRepository.save( geselecteerdMainTicket );
            });
            button.setStyleName("danger");
            return button;
        });


    }

    private void setUpRadioButtonsPompElktrMechOk() {

        rbgMotorElektrisch.addValueChangeListener(x -> {
            if(rbgMotorElektrisch.getValue().matches("NL_Motor Elektrisch in orde_NLFR_Moteur électrique OK_FR")){
                vLayoutMotorElektrisch.setVisible(false);
            }
            if(rbgMotorElektrisch.getValue().matches("NL_Motor NIET Elektrisch in orde_NLFR_Moteur électrique NOK_FR")){
                vLayoutMotorElektrisch.setVisible(true);
            }
        });


        rbgMotorMechanisch.addValueChangeListener(x -> {
            if(rbgMotorMechanisch.getValue().matches("NL_Motor Mechanisch in orde_NLFR_Moteur mécanique OK_FR")){
                vLayoutMotorMechanisch.setVisible(false);
            }
            if(rbgMotorMechanisch.getValue().matches("NL_Motor Mechanisch NIET in orde_NLFR_Moteur mécanique NOK_FR")){
                vLayoutMotorMechanisch.setVisible(true);
            }
        });
    }

    private void setUpbChangeCheckList() {
        bChangeCheckList.addClickListener(x -> {
            geslecteerdDetailTicket.getCheckListBestek().setAantastingen(formatString(geslecteerdDetailTicket.getCheckListBestek().getAantastingen()));
            geslecteerdDetailTicket.getCheckListBestek().setAsafdichting(formatString(geslecteerdDetailTicket.getCheckListBestek().getAsafdichting()));
            geslecteerdDetailTicket.getCheckListBestek().setBinnenwerk(formatString(geslecteerdDetailTicket.getCheckListBestek().getBinnenwerk()));
            geslecteerdDetailTicket.getCheckListBestek().setContaminatie(formatString(geslecteerdDetailTicket.getCheckListBestek().getContaminatie()));
            geslecteerdDetailTicket.getCheckListBestek().setControlBox(formatString(geslecteerdDetailTicket.getCheckListBestek().getControlBox()));
            geslecteerdDetailTicket.getCheckListBestek().setDichtingen(formatString(geslecteerdDetailTicket.getCheckListBestek().getDichtingen()));
            geslecteerdDetailTicket.getCheckListBestek().setGarantie(formatString(geslecteerdDetailTicket.getCheckListBestek().getGarantie()));
            geslecteerdDetailTicket.getCheckListBestek().setIsolatieWeerstand(formatString(geslecteerdDetailTicket.getCheckListBestek().getIsolatieWeerstand()));
            geslecteerdDetailTicket.getCheckListBestek().setKamers(formatString(geslecteerdDetailTicket.getCheckListBestek().getKamers()));
            geslecteerdDetailTicket.getCheckListBestek().setLagers(formatString(geslecteerdDetailTicket.getCheckListBestek().getLagers()));
            geslecteerdDetailTicket.getCheckListBestek().setMotorDichtingen(formatString(geslecteerdDetailTicket.getCheckListBestek().getMotorDichtingen()));
            geslecteerdDetailTicket.getCheckListBestek().setMotorKabel(formatString(geslecteerdDetailTicket.getCheckListBestek().getMotorKabel()));
            geslecteerdDetailTicket.getCheckListBestek().setMotorLagers(formatString(geslecteerdDetailTicket.getCheckListBestek().getMotorLagers()));
            geslecteerdDetailTicket.getCheckListBestek().setMotorVerbrand(formatString(geslecteerdDetailTicket.getCheckListBestek().getMotorVerbrand()));
            geslecteerdDetailTicket.getCheckListBestek().setPompas(formatString(geslecteerdDetailTicket.getCheckListBestek().getPompas()));
            geslecteerdDetailTicket.getCheckListBestek().setPompBinnengebracht(formatString(geslecteerdDetailTicket.getCheckListBestek().getPompBinnengebracht()));
            geslecteerdDetailTicket.getCheckListBestek().setPompstaat(formatString(geslecteerdDetailTicket.getCheckListBestek().getPompstaat()));
            geslecteerdDetailTicket.getCheckListBestek().setPompStatus(formatString(geslecteerdDetailTicket.getCheckListBestek().getPompStatus()));
            geslecteerdDetailTicket.getCheckListBestek().setRotorStatorFlens(formatString(geslecteerdDetailTicket.getCheckListBestek().getRotorStatorFlens()));

            geslecteerdDetailTicket.getCheckListBestek().setSecundaireAsafdichting(formatString(geslecteerdDetailTicket.getCheckListBestek().getSecundaireAsafdichting()));
            geslecteerdDetailTicket.getCheckListBestek().setSpaltringen(formatString(geslecteerdDetailTicket.getCheckListBestek().getSpaltringen()));
            geslecteerdDetailTicket.getCheckListBestek().setVentilator(formatString(geslecteerdDetailTicket.getCheckListBestek().getVentilator()));
            geslecteerdDetailTicket.getCheckListBestek().setVochtInMotor(formatString(geslecteerdDetailTicket.getCheckListBestek().getVochtInMotor()));
            geslecteerdDetailTicket.getCheckListBestek().setWaaiers(formatString(geslecteerdDetailTicket.getCheckListBestek().getWaaiers()));
            geslecteerdDetailTicket.getCheckListBestek().setWaterInMotor(formatString(geslecteerdDetailTicket.getCheckListBestek().getWaterInMotor()));
            geslecteerdDetailTicket.getCheckListBestek().setWikkelingsWaardes(formatString(geslecteerdDetailTicket.getCheckListBestek().getWikkelingsWaardes()));

            mainTicketRepository.save(geselecteerdMainTicket);

        });
    }

    private Set<String> formatString(Set<String> collect) {

            System.out.println("functie aangesproken");
            Set<String> set = new HashSet();

           List<String> newList = new ArrayList(collect);

           if((newList.size()>1)&&(newList.get(0).toString().contains("FR"))){
               Collections.swap(newList,0,1);
               set.add(newList.subList(0,2).stream().collect(Collectors.joining()));
           }
           if((newList.size()>1)&&(newList.get(0).toString().contains("NL"))){
               set.add(newList.subList(0,2).stream().collect(Collectors.joining()));
           }

            if((newList.size()>2)&&(newList.get(2).toString().contains("FR"))){
                Collections.swap(newList,2,3);
            }
            if(newList.size()>2){
                set.add(newList.subList(2,4).stream().collect(Collectors.joining()));
            }
            return set;


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

        checkbGroupTechniekersHersteld.setItems( personeelRepository.findAll().stream().collect( Collectors.toSet()));
        checkbGroupTechniekersHersteld.setItemDescriptionGenerator( x -> x.getVoorNaam() + " " + x.getAchterNaam() );
        checkbGroupTechniekersHersteld.setItemCaptionGenerator( x -> x.getInlogNaam() );
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
            hideCertainFields(parameters[2]);
        }
        geslecteerdDetailTicket = geselecteerdMainTicket.getDetails().stream().filter( x -> x.getamNummer().matches( parameters[0] ) ).findFirst().get();
        checkListBestekBinder.setBean( geslecteerdDetailTicket.getCheckListBestek() );
        detailTicketBinder.setBean( geslecteerdDetailTicket );
        if(geslecteerdDetailTicket.getBenodigdheden() != null){
            productList = geslecteerdDetailTicket.getBenodigdheden();
        }
        productGrid.setItems(productList);

    }

    private void hideCertainFields(String parameter) {
        switch (parameter){
            case "CheckListFase.VOORBEREIDING":
                Notification.show("Voorbereiding");
                vLayoutMain.setEnabled(false);
                break;
            case "CheckListFase.DEMONTAGE":
                Notification.show("Demontage");
                checkbGroupTechniekersHersteld.setEnabled(false);
                vLayoutHerstelling.setEnabled(false);
                break;
            case "CheckListFase.HERSTELLING":
                Notification.show("Herstelling");
                checkbGroupTechniekers.setEnabled(false);
                hLayoutAansluitingen.setEnabled(false);
                hLaytoutMotorElektrischInOrde.setEnabled(false);
                vLayoutMotorElektrisch.setEnabled(false);
                vLayoutMotorMechanisch.setEnabled(false);
                vLayoutPompeigenschappen.setEnabled(false);
                vLayoutDemontage.setEnabled(false);
                break;
        }
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
        checkListBestekBinder.forField( checkbGroupTechniekersHersteld )
                .bind( x -> x.getHersteldDoorList().stream().collect( Collectors.toSet()),(x, y)-> x.setHersteldDoorList( y.stream().collect( Collectors.toList()) )  );
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
        checkListBestekBinder.forField(tfPompVermogen)
                .bind(CheckListBestek::getVermogenPomp, CheckListBestek::setVermogenPomp );
        checkListBestekBinder.forField( tfProductDatumPomp )
                .bind( CheckListBestek::getProductieDatumPomp, CheckListBestek::setProductieDatumPomp );
        checkListBestekBinder.forField( tfSerieNummerPomp )
                .bind( CheckListBestek::getSerieNummerPomp, CheckListBestek::setSerieNummerPomp );
        checkListBestekBinder.forField( tfQnom )
                .bind( CheckListBestek::getqNomPomp, CheckListBestek::setqNomPomp );
        checkListBestekBinder.forField( tfHnom )
                .bind( CheckListBestek::gethNomPomp, CheckListBestek::sethNomPomp );
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
        checkListBestekBinder.forField( rbgMotorElektrisch )
                .bind(( x -> x.getMotorElekrischInOrde()),(x, y)-> x.setMotorElekrischInOrde( y ) );
        checkListBestekBinder.forField( rbgMotorMechanisch )
                .bind( x -> x.getMotorMechanischInOrde(),(x, y)-> x.setMotorMechanischInOrde( y ) );
        checkListBestekBinder.forField( cbgIsolatieweerstand )
                .bind( x -> x.getIsolatieWeerstand().stream().collect( Collectors.toSet()),(x, y)-> x.setIsolatieWeerstand( y )  );
        checkListBestekBinder.forField( cbgWikkelingswaardes )
                .bind( x -> x.getWikkelingsWaardes().stream().collect( Collectors.toSet()),(x, y)-> x.setWikkelingsWaardes( y )  );
        checkListBestekBinder.forField( cbgWikkelingswaardes )
                .bind( x -> x.getWikkelingsWaardes().stream().collect( Collectors.toSet()),(x, y)-> x.setWikkelingsWaardes( y )  );
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
        checkListBestekBinder.forField(checkbGeenNabehandeling)
                .bind(CheckListBestek::isGnNabehandeling, (x,y)-> x.setGnNabehandeling(y));
        checkListBestekBinder.forField(checkbHerschilderen)
                .bind(CheckListBestek::isHerschilderen, (x,y)-> x.setHerschilderen(y));
        checkListBestekBinder.forField(checkbZandstralen)
                .bind(CheckListBestek::isZandstralenEnHerschilderen, (x,y)-> x.setZandstralenEnHerschilderen(y));
        checkListBestekBinder.forField(checkbGeenLogo)
                .bind(CheckListBestek::isbGeenLogoPlaatsen, (x,y)-> x.setbGeenLogoPlaatsen(y));
        detailTicketBinder.forField(checkbDemontageAfgewerkt)
                .bind(DetailTicket::isbTeDemonteren, (x,y)-> x.setbTeDemonteren(y));
        detailTicketBinder.forField(checkbDirectTeHerstellen)
                .bind(DetailTicket::isbDirectTeHerstellen, (x,y)-> x.setbDirectTeHerstellen(y));


        checkListBestekBinder.addValueChangeListener( x -> {
            mainTicketRepository.save( geselecteerdMainTicket );
        } );
        detailTicketBinder.addValueChangeListener( x -> {
            mainTicketRepository.save( geselecteerdMainTicket );
        } );
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
        rbgMotorElektrisch.addValueChangeListener(x -> {

            if(rbgMotorElektrisch.getValue().isEmpty()){
                rbgMotorElektrisch.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                rbgMotorElektrisch.setStyleName("horizontal optionGroupGreenStyle");
            }
        });
        rbgMotorMechanisch.addValueChangeListener(x -> {

            if(rbgMotorMechanisch.getValue().isEmpty()){
                rbgMotorMechanisch.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                rbgMotorMechanisch.setStyleName("horizontal optionGroupGreenStyle");
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
        cbgWikkelingswaardes.addValueChangeListener(x -> {
            if(cbgWikkelingswaardes.getValue().isEmpty()){
                cbgWikkelingswaardes.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgWikkelingswaardes.setStyleName("horizontal optionGroupGreenStyle");
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

        cbgGarantie.addValueChangeListener(x -> {
            if(cbgGarantie.getValue().isEmpty()){
                cbgGarantie.setStyleName("horizontal optionGroupRedStyle");
            }
            else{
                cbgGarantie.setStyleName("horizontal optionGroupGreenStyle");
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
        checkbGeenNabehandeling.addValueChangeListener(x -> {
            checkIfNabehandelingIsOk();
        });
                checkbHerschilderen.addValueChangeListener(x -> {
                    checkIfNabehandelingIsOk();
                });
        checkbZandstralen.addValueChangeListener(x -> {
            checkIfNabehandelingIsOk();
        });
                checkbGeenLogo.addValueChangeListener(x -> {
                    checkIfNabehandelingIsOk();
                });
    }

    private void checkIfNabehandelingIsOk() {
        if(checkbGeenNabehandeling.getValue() || checkbHerschilderen.getValue() || checkbZandstralen.getValue() || checkbGeenLogo.getValue()) {
            hLayoutNabehandeling.setStyleName("hLayoutGreenStyle");
        }
        else{
            hLayoutNabehandeling.setStyleName("hLayoutRedStyle");
        }
    }


}
