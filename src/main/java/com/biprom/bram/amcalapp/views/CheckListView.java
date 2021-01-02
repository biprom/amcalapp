package com.biprom.bram.amcalapp.views;


import com.biprom.bram.amcalapp.Designs.CheckListDesign;
import com.biprom.bram.amcalapp.data.entity.mongodbEntities.CheckListBestek;
import com.biprom.bram.amcalapp.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.amcalapp.data.entity.mongodbEntities.MainTicket;
import com.biprom.bram.amcalapp.data.entity.mongodbEntities.Product;
import com.biprom.bram.amcalapp.mongoRepositories.PersoneelRepository;
import com.biprom.bram.amcalapp.security.SecurityUtils;
import com.biprom.bram.amcalapp.Upload.LineBreakCounter;
import com.biprom.bram.amcalapp.mongoRepositories.MainTicketRepository;
import com.biprom.bram.amcalapp.service.GridFS.GridFSService;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpringView(name = "checkList")
public class CheckListView extends CheckListDesign implements View {

    @Value("${root}")
    String root;

    @Value("${inter}")
    String inter;

    MainTicketRepository mainTicketRepository;
    PersoneelRepository personeelRepository;
    GridFSService gridFSService;

    File fileToDelete;

    LineBreakCounter lineBreakCounter = new LineBreakCounter();

    MainTicket geselecteerdMainTicket;
    DetailTicket geselecteerdDetail;

    Binder<CheckListBestek>checkListBestekBinder = new Binder<>(  );
    Binder<DetailTicket>detailTicketBinder = new Binder<>(  );
    
    List<Product>productList = new ArrayList<>();

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
                         PersoneelRepository personeelRepository,
                         GridFSService gridFSService) {

        this.mainTicketRepository = mainTicketRepository;
        this.personeelRepository = personeelRepository;
        this.gridFSService = gridFSService;

        setUpCheckBoxGroupTechniekers();
        setUpCBPositieMagazijn();
        setUpPositieAansluitingen();
        setUpRadioButtonsPompElktrMechOk();
        setUpBinder();
        setUpCBAansluitingen();
        setUpCheckBoxGroupValueChangeListeners();
        setUpGrid();
        setUpUplFoto();
        setUpAutoMateriaalButton();
    }

    private void setUpAutoMateriaalButton() {
        bGenereerMateriaalLijst.addClickListener(x -> {
            if(cbgWikkelingswaardes.getValue().toString().contains("NL_Wikkelingswaardes NOK")){
                productList.add(new Product("Herwikkelen"));
            }
            if(cbgMotorVerbrand.getValue().toString().contains("NL_Motor verbrand")){
                productList.add(new Product("Herwikkelen"));
            }
            if(cbgControlBox.getValue().toString().contains("NL_Controlbox Defect")){
                productList.add(new Product("Controlbox"));
            }
            if(cbgWaterInMotor.getValue().toString().contains("NL_water in motor_NL")){
                productList.add(new Product("Uitdrogen/Nieuwe motor"));
            }
            if(cbgControlBox.getValue().toString().contains("NL_Water in Controlbox_NL")){
                productList.add(new Product("Uitdrogen/Nieuwe Controlbox"));
            }
            if(cbgWaterInMotor.getValue().toString().contains("NL_Water in motorkabel_NL")){
                productList.add(new Product("Uitdrogen/Nieuwe motorkabel"));
            }
            if(cbgWaterInMotor.getValue().toString().contains("NL_Water in krimpmof_NL")){
                productList.add(new Product("Uitdrogen/Nieuwe krimpmof"));
            }
            if(cbgWaterInMotor.getValue().toString().contains("NL_Water in onderwaterkabel_NL")){
                productList.add(new Product("Uitdrogen/Nieuwe onderwaterkabel"));
            }
            if((cbgMotorLagers.getValue().toString().contains("NL_Lagers lawaai_NL"))||(cbgMotorLagers.getValue().toString().contains("NL_Lagers defect_NL"))){
                productList.add(new Product("Motorlagers " + tfLagerAsMotorCommentaar.getValue() + " " + tfLagerVentMotorCommentaar1.getValue()));
            }
            if(cbgBeschadingMotor.getValue().toString().contains("NL_Stator defect_NL")){
                productList.add(new Product("Stator"));
            }
            if(cbgBeschadingMotor.getValue().toString().contains("NL_Rotor beschadigd_NL")){
                productList.add(new Product("Rotor"));
            }
            if(cbgBeschadingMotor.getValue().toString().contains("NL_Flens NDE defect_NL")){
                productList.add(new Product("Motorflens NDE"));
            }
            if(cbgBeschadingMotor.getValue().toString().contains("NL_Flens DE defect_NL")){
                productList.add(new Product("Motorflens DE"));
            }
            if((cbgMotorDichtingen.getValue().toString().contains("NL_Motordichtingen uitgezet_NL"))
                    ||(cbgMotorDichtingen.getValue().toString().contains("NL_Motordichtingen beschadigd_NL"))
                    ||(cbgMotorDichtingen.getValue().toString().contains("NL_Motordichtingen gesmolten_NL"))
                    ||(cbgMotorDichtingen.getValue().toString().contains("NL_Motordichtingen gescheurd_NL"))){
                productList.add(new Product("Motordichtingen "));
            }
            if(cbgVentilator.getValue().toString().contains("NL_Ventilatorkap defect_NL")){
                productList.add(new Product("Ventilatorkap"));
            }
            if(cbgVentilator.getValue().toString().contains("NL_Ventilator defect_NL")){
                productList.add(new Product("Ventilator"));
            }
            if(cbgVentilator.getValue().toString().contains("NL_Klemmenblok defect_NL")){
                productList.add(new Product("Klemmenblok"));
            }
            if(cbgVentilator.getValue().toString().contains("NL_Klemmendeksel defect_NL")){
                productList.add(new Product("Klemmendeksel"));
            }
            if(cbgMotorkabel.getValue().toString().contains("NL_Motorkabel beschadigd_NL")){
                productList.add(new Product("Motorkabel"));
            }
            if((cbgPrimaireAsafdichtingen.getValue().toString().contains("NL_Asafdichting ingelopen_NL"))
                    ||(cbgPrimaireAsafdichtingen.getValue().toString().contains("NL_Asafdichting beschadigd_NL"))
                    ||(cbgPrimaireAsafdichtingen.getValue().toString().contains("NL_Asafdichting versleten_NL"))
                    ||(cbgPrimaireAsafdichtingen.getValue().toString().contains("NL_Asafdichting uitgezet_NL"))
                    ||(cbgPrimaireAsafdichtingen.getValue().toString().contains("NL_Asafdichting gebarsten_NL"))){
                productList.add(new Product("Asafdichting "));
            }
            if((cbgSecundaireAsafdichtingen.getValue().toString().contains("NL_Secundaire asafdichting ingelopen_NL"))
                    ||(cbgSecundaireAsafdichtingen.getValue().toString().contains("NL_Secundaire asafdichting beschadigd_NL"))
                    ||(cbgSecundaireAsafdichtingen.getValue().toString().contains("NL_Secundaire asafdichting versleten_NL"))
                    ||(cbgSecundaireAsafdichtingen.getValue().toString().contains("NL_Secundaire asafdichting uitgezet_NL"))
                    ||(cbgSecundaireAsafdichtingen.getValue().toString().contains("NL_Secundaire asafdichting gebarsten_NL"))){
                productList.add(new Product("Secundaire asafdichting "));
            }
            if((cbgPompas.getValue().toString().contains("NL_Pompas ingelopen_NL"))
                    ||(cbgPompas.getValue().toString().contains("NL_Pompas Putcorrosie_NL"))
                    ||(cbgPompas.getValue().toString().contains("NL_Pompas Beschadigd_NL"))
                    ||(cbgPompas.getValue().toString().contains("NL_Pompas Versleten_NL"))
                    ||(cbgPompas.getValue().toString().contains("NL_Pompas Gebroken_NL"))){
                productList.add(new Product("Secundaire asafdichting "));
            }
            if((cbgWaaiers.getValue().toString().contains("NL_Waaiers Ingelopen_NL"))
                    ||(cbgWaaiers.getValue().toString().contains("NL_Waaiers Beschadigd_NL"))
                    ||(cbgWaaiers.getValue().toString().contains("NL_Waaiers Versleten_NL"))
                    ||(cbgWaaiers.getValue().toString().contains("NL_Waaiers Gebroken_NL"))){
                productList.add(new Product("Waaiers "));
            }
            if((cbgKamers.getValue().toString().contains("NL_Kamers beschadigd_NL"))
                    ||(cbgKamers.getValue().toString().contains("NL_Kamers Gebroken_NL"))
                    ||(cbgKamers.getValue().toString().contains("NL_Kamers Versleten_NL"))){
                productList.add(new Product("Kamer "));
            }
            if((cbgDichtingen.getValue().toString().contains("NL_Dichtingen Uitgezet_NL"))
                    ||(cbgDichtingen.getValue().toString().contains("NL_Dichtingen Beschadigd_NL"))
                    ||(cbgDichtingen.getValue().toString().contains("NL_Dichtingen Gesmolten_NL"))
                    ||(cbgDichtingen.getValue().toString().contains("NL_Dichtingen Gescheurd_NL"))){
                productList.add(new Product("Dichtingen "));
            }
            if(cbgAantasting.getValue().toString().contains("NL_Aantasting Kopstuk_NL")){
                productList.add(new Product("Kopstuk"));
            }
            if(cbgAantasting.getValue().toString().contains("NL_Aantasting Voetstuk_NL")){
                productList.add(new Product("Voetstuk"));
            }
            if(cbgAantasting.getValue().toString().contains("NL_Aantasting mantel_NL")){
                productList.add(new Product("mantel"));
            }
            if(cbgAantasting.getValue().toString().contains("NL_Aantasting kamers_NL")){
                productList.add(new Product("kamer"));
            }
            if((cbgLagers.getValue().toString().contains("NL_Lagers ingelopen_NL"))
                    ||(cbgLagers.getValue().toString().contains("NL_Lagers beschadigd_NL"))
                    ||(cbgLagers.getValue().toString().contains("NL_Lagers gebarsten_NL"))){
                productList.add(new Product("Lagers "));
            }
            if((cbgSpaltringen.getValue().toString().contains("NL_Spaltringen ingelopen_NL"))
                    ||(cbgSpaltringen.getValue().toString().contains("NL_Spaltringen beschadigd_NL"))
                    ||(cbgSpaltringen.getValue().toString().contains("NL_Spaltringen gebarsten_NL"))){
                productList.add(new Product("Spaltringen "));
            }
            if(cbgBinnenwerk.getValue().toString().contains("NL_Binnenwerk volledig stukgedraaid_NL")){
                productList.add(new Product("Volledig binnenwerk"));
            }



        });
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
            geselecteerdDetail.setBenodigdheden((ArrayList<Product>) productList);
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
                geselecteerdDetail.setBenodigdheden((ArrayList<Product>) productList);
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

    private Set<String> formatString(Set<String> collect) {

            Set<String> set = new HashSet();

        try {

           List<String> receivedList = new ArrayList(collect);

           if(receivedList.size() > 1) {
               while (receivedList.size() > 0) {

                       String string1 = receivedList.stream().filter(x -> x.contains("NL")).findFirst().get();
                       String string2 = receivedList.stream().filter(x -> x.contains("FR")).reduce((first, second) -> second).get();
                       set.add(string1 + string2);
                       receivedList.remove(string1);
                       receivedList.remove(string2);

               }
           }
           else{
               try {
                   String string1 = "NL_" + receivedList.get(0) + "_NL";
                   set.add(string1);
               }
               catch (Exception e){

               }
           }
        }
        catch (Exception e){

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

    private void setUpBHaalFotosOp() {

        imageGird.removeAllComponents();
        if(geselecteerdDetail.getPictureList() != null){
            gridFSService.findFilesForDetailTicket(geselecteerdDetail, null );


            List<FileResource>resourceList = gridFSService.getResourcesPics();
            List<String>metaList = gridFSService.getMetaList();
            int i = 0;
            for (FileResource resource : resourceList){

                Image image = new Image(  );
                image.setSource( resource );
                image.addContextClickListener( f -> {
                    resourceList.remove( f.getComponent() );
                    imageGird.removeComponent( f.getComponent() );
                    gridFSService.deleteImage( resource.getFilename() );

                } );

                imageGird.addComponent( image );

                TextArea textArea = new TextArea( "Toegevoegde Metadata" );
                textArea.setStyleName( ""+ i );
                textArea.setWidth( "400px" );
                textArea.setValue( metaList.get( i ) );
                textArea.addBlurListener( f -> {
                    gridFSService.getImageEntityList().stream().filter(x -> x.getName().matches(resource.getFilename())).findFirst().get().setComment(textArea.getValue());
                    gridFSService.changeMetaData( gridFSService.getImageEntityList().stream().filter(x -> x.getName().matches(resource.getFilename())).findFirst().get()) ;

                } );
                imageGird.addComponent( textArea );

                i++;
            }
        }

    }

    private void setUpUplFoto() {
        uploadFoto.setReceiver( lineBreakCounter );

        uploadFoto.setImmediateMode( true );
        uploadFoto.setButtonCaption( "Koppel foto in dit datail" );

        uploadFoto.addStartedListener( event -> {
            Notification.show( "Upload foto gestart" );
        });

        uploadFoto.addFinishedListener( event -> {

            try {
                Thumbnails.of( new File( root + inter +"uplPicToDB"+ SecurityUtils.getUsername()+inter +event.getFilename() ) )
                        .size( 700,700 )
                        .toFile( root + inter + "uploadThumbnail"+SecurityUtils.getUsername()+inter +event.getFilename() );
                Thumbnails.of( new File( root + inter + "uplPicToDB"+SecurityUtils.getUsername()+inter +event.getFilename() ) )
                        .size( 700,700 )
                        .toFile( root + inter + "recPicFromDB"+SecurityUtils.getUsername()+inter +event.getFilename() );
            } catch (IOException e) {
                e.printStackTrace();
            }

            gridFSService.storeFileToMongoDB( root + inter +"uploadThumbnail"+SecurityUtils.getUsername()+inter +event.getFilename(),"meta1", " ", event.getFilename(), geselecteerdDetail, true );
            Notification.show( "Upload foto voltooid" );
            try{


                fileToDelete = new File(root + inter + "uplPicToDB"+SecurityUtils.getUsername()+ inter + event.getFilename());
                fileToDelete.delete();
                fileToDelete = new File(root + inter +"amcal/uploadThumbnail"+SecurityUtils.getUsername()+ inter + event.getFilename());
                fileToDelete.delete();

                imageGird.addComponent(new Image("foto geuploaded", new FileResource( new File( root + inter + "recPicFromDB"+SecurityUtils.getUsername()+ inter + event.getFilename() ) )));


            }catch(Exception e){

                e.printStackTrace();
                Notification.show( "Upload foto mislukt : " + e.getMessage() );
            }

        });
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
        geselecteerdDetail = geselecteerdMainTicket.getDetails().stream().filter(x -> x.getamNummer().matches( parameters[0] ) ).findFirst().get();
        checkListBestekBinder.setBean( geselecteerdDetail.getCheckListBestek() );
        detailTicketBinder.setBean(geselecteerdDetail);
        if(geselecteerdDetail.getBenodigdheden() != null){
            productList = geselecteerdDetail.getBenodigdheden();
        }
        else{
            productList = new ArrayList<>();
            productList.add(new Product());
        }
        productGrid.setItems(productList);

        setUpBHaalFotosOp();

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
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getGedemonteerdDoorList().stream().collect( Collectors.toSet()),(x, y)-> x.setGedemonteerdDoorList( y.stream().collect( Collectors.toList()) )  );
        checkListBestekBinder.forField( checkbGroupTechniekersHersteld )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> {
                    if(x.getHersteldDoorList() != null){
                        return x.getHersteldDoorList().stream().collect( Collectors.toSet());
                    }
                    else{
                        return new HashSet<>();
                    }
                },(x, y)-> x.setHersteldDoorList( y.stream().collect( Collectors.toList()) )  ); checkListBestekBinder.forField( checkbGroupTechniekersHersteld )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> {
                    if(x.getHersteldDoorList() != null){
                        return x.getHersteldDoorList().stream().collect( Collectors.toSet());
                    }
                    else{
                        return new HashSet<>();
                    }
                },(x, y)-> x.setHersteldDoorList( y.stream().collect( Collectors.toList()) )  );
        checkListBestekBinder.forField( cbgEUPallet )
                .withNullRepresentation(new HashSet<>())
                .bind(x -> x.getBinnengebrachtOp(),(x,y) -> x.setBinnengebrachtOp( y ) );
        checkListBestekBinder.forField( tfBinnengebrachtAndereManier )
                .withNullRepresentation("")
                .bind( CheckListBestek::getBinnengebrachtOpAndere, CheckListBestek::setBinnengebrachtOpAndere  );
        checkListBestekBinder.forField( cbPositieMagazijn )
                .withNullRepresentation("")
                .bind( CheckListBestek::getPositieMagazijn, CheckListBestek::setPositieMagazijn );
        checkListBestekBinder.forField( tfAnderePositie )
                .withNullRepresentation("")
                .bind( CheckListBestek::getAnderPositie, CheckListBestek::setAnderPositie );
        checkListBestekBinder.forField( cbElektrischeAansluiting )
                .withNullRepresentation("")
                .bind( x -> String.valueOf(x.getZijdeElektrischeAansluiting()), (x,y) -> x.setZijdeElektrischeAansluiting(getImageNumber(y)) );
        checkListBestekBinder.forField( cbZijdeOntluchting )
                .withNullRepresentation("")
                .bind( x -> String.valueOf(x.getZijdeOntluchting()), (x,y) -> x.setZijdeOntluchting(getImageNumber(y)) );
        checkListBestekBinder.forField( cbMontagePlaten )
                .withNullRepresentation("")
                .bind( x -> String.valueOf(x.getZijdeMontagePlaten()), (x,y) -> x.setZijdeMontagePlaten(getImageNumber(y)) );
        checkListBestekBinder.forField( taBijkomendCommentaarPomp )
                .withNullRepresentation("")
                .bind( CheckListBestek::getCommentaarPomp, CheckListBestek::setCommentaarPomp );
        checkListBestekBinder.forField( tfUitGebreidPompType )
                .withNullRepresentation("")
                .bind( CheckListBestek::getUitgebreidPompType, CheckListBestek::setUitgebreidPompType );
        checkListBestekBinder.forField( tfArtikelNummerPomp )
                .withNullRepresentation("")
                .bind( CheckListBestek::getArtikelNummerPomp, CheckListBestek::setArtikelNummerPomp );
        checkListBestekBinder.forField(tfPompVermogen)
                .withNullRepresentation("")
                .bind(CheckListBestek::getVermogenPomp, CheckListBestek::setVermogenPomp );
        checkListBestekBinder.forField( tfProductDatumPomp )
                .withNullRepresentation("")
                .bind( CheckListBestek::getProductieDatumPomp, CheckListBestek::setProductieDatumPomp );
        checkListBestekBinder.forField( tfSerieNummerPomp )
                .withNullRepresentation("")
                .bind( CheckListBestek::getSerieNummerPomp, CheckListBestek::setSerieNummerPomp );
        checkListBestekBinder.forField( tfQnom )
                .withNullRepresentation("")
                .bind( CheckListBestek::getqNomPomp, CheckListBestek::setqNomPomp );
        checkListBestekBinder.forField( tfHnom )
                .withNullRepresentation("")
                .bind( CheckListBestek::gethNomPomp, CheckListBestek::sethNomPomp );
        checkListBestekBinder.forField( tfLandPomp )
                .withNullRepresentation("")
                .bind( CheckListBestek::getPompLand, CheckListBestek::setPompLand );
        checkListBestekBinder.forField( tfMotorType )
                .withNullRepresentation("")
                .bind( CheckListBestek::getMotorType, CheckListBestek::setMotorType );
        checkListBestekBinder.forField( tfNominaalVermogen )
                .withNullRepresentation("")
                .bind( CheckListBestek::getNominaalVermogen, CheckListBestek::setNominaalVermogen );
        checkListBestekBinder.forField( tfToerental )
                .withNullRepresentation("")
                .bind( CheckListBestek::getToerental, CheckListBestek::setToerental );
        checkListBestekBinder.forField( tfArtikelNummerMotor )
                .withNullRepresentation("")
                .bind( CheckListBestek::getArtikelNummerMotor, CheckListBestek::setArtikelNummerMotor );
        checkListBestekBinder.forField( tfUDriehoek )
                .withNullRepresentation("")
                .bind( CheckListBestek::getUdriehoek, CheckListBestek::setUdriehoek );
        checkListBestekBinder.forField( tfUSter )
                .withNullRepresentation("")
                .bind( CheckListBestek::getUster, CheckListBestek::setUster );
        checkListBestekBinder.forField( cbFreqController )
                .withNullRepresentation(false)
                .bind( CheckListBestek::isFreqRegelaar, CheckListBestek::setFreqRegelaar );
        checkListBestekBinder.forField( cbNietStandardPomp )
                .withNullRepresentation(false)
                .bind( CheckListBestek::isNietStandaardPomp, CheckListBestek::setNietStandaardPomp );
        checkListBestekBinder.forField( tfProductieDatumMotor )
                .withNullRepresentation("")
                .bind( CheckListBestek::getProductieDatumMotor, CheckListBestek::setProductieDatumMotor );
        checkListBestekBinder.forField( tfIsolatieWeerstand )
                .withNullRepresentation("")
                .bind( x -> x.getRisolatie(), (x,y)-> x.setRisolatie( y ) );
        checkListBestekBinder.forField( tfRfase1 )
                .withNullRepresentation("")
                .bind( CheckListBestek::getRfase1, CheckListBestek::setRfase1 );
        checkListBestekBinder.forField( tfRfase2 )
                .withNullRepresentation("")
                .bind( CheckListBestek::getRfase2, CheckListBestek::setRfase2 );
        checkListBestekBinder.forField( tfRfase3 )
                .withNullRepresentation("")
                .bind( CheckListBestek::getRfase3, CheckListBestek::setRfase3 );
        checkListBestekBinder.forField( rbgMotorElektrisch )
                .withNullRepresentation("")
                .bind(( x -> x.getMotorElekrischInOrde()),(x, y)-> x.setMotorElekrischInOrde( y ) );
        checkListBestekBinder.forField( rbgMotorMechanisch )
                .withNullRepresentation("")
                .bind( x -> x.getMotorMechanischInOrde(),(x, y)-> x.setMotorMechanischInOrde( y ) );
        checkListBestekBinder.forField( cbgIsolatieweerstand )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getIsolatieWeerstand(),(x, y)-> x.setIsolatieWeerstand( y )  );
        checkListBestekBinder.forField( cbgWikkelingswaardes )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getWikkelingsWaardes(),(x, y)-> x.setWikkelingsWaardes( y )  );

        checkListBestekBinder.forField( cbgMotorVerbrand )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getMotorVerbrand(),(x, y)-> x.setMotorVerbrand( y )  );
        checkListBestekBinder.forField( cbgControlBox )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getControlBox(),(x, y)-> x.setControlBox( y)  );
        checkListBestekBinder.forField( cbgWaterInMotor )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getWaterInMotor(),(x, y)-> x.setWaterInMotor( y )  );
        checkListBestekBinder.forField( cbgMotorkabel )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getMotorKabel(),(x, y)-> x.setMotorKabel( y )  );
        checkListBestekBinder.forField( cbgVochtInMotor )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getVochtInMotor(),(x, y)-> x.setVochtInMotor( y )  );
        checkListBestekBinder.forField( cbgMotorLagers )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getMotorLagers(),(x, y)-> x.setMotorLagers( y )  );
        checkListBestekBinder.forField( cbgMotorDichtingen )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getMotorDichtingen(),(x, y)-> x.setMotorDichtingen( y )  );
        checkListBestekBinder.forField( cbgVentilator )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getVentilator(),(x, y)-> x.setVentilator( y )  );
        checkListBestekBinder.forField( cbgBeschadingMotor )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getRotorStatorFlens(),(x, y)-> x.setRotorStatorFlens( y )  );

        checkListBestekBinder.forField( cbgGarantie )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getGarantie(),(x, y)-> x.setGarantie( y )  );
        checkListBestekBinder.forField( cbgBinnengebrachtPomp )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getPompBinnengebracht(),(x, y)-> x.setPompBinnengebracht( y )  );
        checkListBestekBinder.forField( cbgStaatPomp )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getPompstaat(),(x, y)-> x.setPompstaat( y )  );
        checkListBestekBinder.forField( cbgPrimaireAsafdichtingen )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getAsafdichting(),(x, y)-> x.setAsafdichting( y )  );
        checkListBestekBinder.forField( cbgSecundaireAsafdichtingen )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getSecundaireAsafdichting(),(x, y)-> x.setSecundaireAsafdichting( y )  );

        checkListBestekBinder.forField( cbgPompas )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getPompas(),(x, y)-> x.setPompas( y )  );
        checkListBestekBinder.forField( cbgWaaiers )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getWaaiers(),(x, y)-> x.setWaaiers( y )  );
        checkListBestekBinder.forField( cbgKamers )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getKamers(),(x, y)-> x.setKamers( y )  );
        checkListBestekBinder.forField( cbgDichtingen )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getDichtingen(),(x, y)-> x.setDichtingen( y )  );
        checkListBestekBinder.forField( cbgAantasting )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getAantastingen(),(x, y)-> x.setAantastingen( y )  );
        checkListBestekBinder.forField( cbgLagers )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getLagers(),(x, y)-> x.setLagers( y )  );
        checkListBestekBinder.forField( cbgSpaltringen )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getSpaltringen(),(x, y)-> x.setSpaltringen( y )  );
        checkListBestekBinder.forField( cbgBinnenwerk )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getBinnenwerk(),(x, y)-> x.setBinnenwerk( y )  );
        checkListBestekBinder.forField( cbgVuilInPomp )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getContaminatie(),(x, y)-> x.setContaminatie( y )  );
        checkListBestekBinder.forField( cbgStatusPomp )
                .withNullRepresentation(new HashSet<>())
                .bind( x -> x.getPompStatus(),(x, y)-> x.setPompStatus( y )  );

        checkListBestekBinder.forField( tfExtraCommentaarWaterInMotor )
                .withNullRepresentation("")
                .bind( CheckListBestek::getWaterInOnderdeelCommentaar, CheckListBestek::setWaterInOnderdeelCommentaar );
        checkListBestekBinder.forField( tfCommentaarMotorkabel )
                .withNullRepresentation("")
                .bind( CheckListBestek::getMotorKabelCommentaar, CheckListBestek::setMotorKabelCommentaar );
        checkListBestekBinder.forField( tfLagerAsMotorCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getLagersMotorCommentaar, CheckListBestek::setLagersMotorCommentaar );
        checkListBestekBinder.forField( tfLagerVentMotorCommentaar1 )
                .withNullRepresentation("")
                .bind( CheckListBestek::getLagersMotorVentilatorCommentaar, CheckListBestek::setLagersMotorVentilatorCommentaar );
        checkListBestekBinder.forField( tfCommentaarPompBinnengebracht )
                .withNullRepresentation("")
                .bind( CheckListBestek::getPompBinnengebrachtCommentaar, CheckListBestek::setPompBinnengebrachtCommentaar );
        checkListBestekBinder.forField( tfCommentaarPompDemonteerbaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getStaatPompCommentaar, CheckListBestek::setStaatPompCommentaar );
        checkListBestekBinder.forField( tfPrimaireAsafdichtingCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getAsafdichtingCommentaar, CheckListBestek::setAsafdichtingCommentaar );
        checkListBestekBinder.forField( tfSecundaireAsafdichtingCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getSecundaireAsafdichtingCommentaar, CheckListBestek::setSecundaireAsafdichtingCommentaar );
        checkListBestekBinder.forField( tfPompasCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getPompasCommentaar, CheckListBestek::setPompasCommentaar );
        checkListBestekBinder.forField( tfWaaiersCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getWaaiersCommentaar, CheckListBestek::setWaaiersCommentaar );
        checkListBestekBinder.forField( tfKamersCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getKamersCommentaar, CheckListBestek::setKamersCommentaar );
        checkListBestekBinder.forField( tfDichtingenCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getDichtingenCommentaar, CheckListBestek::setDichtingenCommentaar );
        checkListBestekBinder.forField( tfAantastingCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getAantastingCommentaar, CheckListBestek::setAantastingCommentaar );
        checkListBestekBinder.forField( tfLagersCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getLagersPompCommentaar, CheckListBestek::setLagersPompCommentaar );
        checkListBestekBinder.forField( tfSpaltRingenCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getSpaltRingenCommentaar, CheckListBestek::setSpaltRingenCommentaar );
        checkListBestekBinder.forField( tfPompStatusCommentaar )
                .withNullRepresentation("")
                .bind( CheckListBestek::getPompStatusCommentaar, CheckListBestek::setPompStatusCommentaar );

        checkListBestekBinder.forField( dateDemontage )
                .bind( x -> x.getDatumDemontage(), (x,y) -> x.setDatumDemontage( y ) );
        checkListBestekBinder.forField( tfUrenDemontage )
                .withNullRepresentation("")
                .bind( x -> x.getAantalUrenDemontage(), (x,y) -> x.setAantalUrenDemontage( y ) );
        checkListBestekBinder.forField( checkbBestekkostenAanrekenen )
                .withNullRepresentation(false)
                .bind( x -> x.isBestekKostenAanrekenen(), (x,y) -> x.setBestekKostenAanrekenen( y ) );

        checkListBestekBinder.forField( datefHerstelDatum )
                .bind( x -> x.getDatumHerstel(), (x,y) -> x.setDatumHerstel( y ) );
        checkListBestekBinder.forField( dtUitersteHerstelDatum )
                .bind( x -> x.getUitersteHerstelDatum(), (x,y) -> x.setUitersteHerstelDatum( y ) );
        checkListBestekBinder.forField( tfUrenHerstelling )
                .withNullRepresentation("")
                .bind( x -> x.getAantalUrenHerstelling(), (x,y) -> x.setAantalUrenHerstelling( y ) );
        checkListBestekBinder.forField( tfUrenReinigen )
                .withNullRepresentation("")
                .bind( x -> x.getAantalUrenReiniging(), (x,y) -> x.setAantalUrenReiniging( y ) );
        detailTicketBinder.forField( checkbHerstellingAfgewerkt )
                .withNullRepresentation(false)
                .bind( x -> x.isbPompHersteld(), (x,y) -> x.setbPompHersteld( y ) );

        checkListBestekBinder.forField( tfDebiet )
                .withNullRepresentation("")
                .bind( x -> x.getMeetresultaatDebiet(), (x,y) -> x.setMeetresultaatDebiet( y ) );
        checkListBestekBinder.forField( tfDruk )
                .withNullRepresentation("")
                .bind( x -> x.getMeetresultaatDruk(), (x,y) -> x.setMeetresultaatDruk( y ) );
        checkListBestekBinder.forField( tfDebietVuilWater )
                .withNullRepresentation("")
                .bind( x -> x.getDrukTestVuilwaterDruk(), (x,y) -> x.setDrukTestVuilwaterDruk( y ) );
        checkListBestekBinder.forField(checkbGeenNabehandeling)
                .withNullRepresentation(false)
                .bind(CheckListBestek::isGnNabehandeling, (x,y)-> x.setGnNabehandeling(y));
        checkListBestekBinder.forField(checkbHerschilderen)
                .withNullRepresentation(false)
                .bind(CheckListBestek::isHerschilderen, (x,y)-> x.setHerschilderen(y));
        checkListBestekBinder.forField(checkbZandstralen)
                .withNullRepresentation(false)
                .bind(CheckListBestek::isZandstralenEnHerschilderen, (x,y)-> x.setZandstralenEnHerschilderen(y));
        checkListBestekBinder.forField(checkbGeenLogo)
                .withNullRepresentation(false)
                .bind(CheckListBestek::isbGeenLogoPlaatsen, (x,y)-> x.setbGeenLogoPlaatsen(y));
        detailTicketBinder.forField(checkbDemontageAfgewerkt)
                .withNullRepresentation(false)
                .bind(DetailTicket::isbTeDemonteren, (x,y)-> x.setbTeDemonteren(y));
        detailTicketBinder.forField(checkbDirectTeHerstellen)
                .withNullRepresentation(false)
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
