package com.biprom.bram.ui.views.inoxSubView;

import com.biprom.bram.app.security.SecurityUtils;
import com.biprom.bram.backend.Upload.LineBreakCounter;
import com.biprom.bram.backend.data.entity.mongodbEntities.*;
import com.biprom.bram.backend.mongoRepositories.MainTicketRepository;
import com.biprom.bram.backend.mongoRepositories.PersoneelRepository;
import com.biprom.bram.backend.service.GridFS.GridFSService;
import com.biprom.bram.backend.service.UserService;
import com.mongodb.gridfs.GridFSDBFile;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
@SpringView(name = InoxSubView.VIEW_NAME)
public class InoxSubView extends InoxSubDesign implements View {

    public static final String VIEW_NAME = "inoxPagina";

    @Value("${root}")
    String root;

    @Value("${inter}")
    String inter;

    PersoneelRepository personeelRepository;
    MainTicketRepository mainTicketRepository;
    UserService userService;
    GridFSService gridFSService;

    File fileToDelete;


    LineBreakCounter lineBreakCounter = new LineBreakCounter();

    Binder<DetailTicket>projectBinder = new Binder<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    List<ImageEntity>imageEntityList = new ArrayList<>(  );

    DetailTicket geselecteerdDatail;
    MainTicket geselecteerdMainTicket;

    List<Product> productList = new ArrayList<>();

    @Autowired
    public InoxSubView(PersoneelRepository personeelRepository,
                       MainTicketRepository mainTicketRepository,
                       UserService userService,
                       GridFSService gridFSService) {

        this.personeelRepository = personeelRepository;
        this.mainTicketRepository = mainTicketRepository;
        this.userService = userService;
        this.gridFSService = gridFSService;

        System.out.println("Ingelogde user is : "+SecurityUtils.getUsername());

        setUpBinder();
        setUpCbPersoneel();
        setUpProductGrid();
        setUpUplFoto();
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
                Thumbnails.of( new File( root + inter +"uplPicToDB"+SecurityUtils.getUsername()+inter +event.getFilename() ) )
                        .size( 350,350 )
                        .toFile( root + inter + "uploadThumbnail"+SecurityUtils.getUsername()+inter +event.getFilename() );
                Thumbnails.of( new File( root + inter + "uplPicToDB"+SecurityUtils.getUsername()+inter +event.getFilename() ) )
                        .size( 350,350 )
                        .toFile( root + inter + "recPicFromDB"+SecurityUtils.getUsername()+inter +event.getFilename() );
            } catch (IOException e) {
                e.printStackTrace();
            }

            gridFSService.storeFileToMongoDB( root + inter +"uploadThumbnail"+SecurityUtils.getUsername()+inter +event.getFilename(),"meta1", " ", event.getFilename(),geselecteerdDatail , true );
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


    private void setUpCbPersoneel() {
        lsPersoneel.setItems( personeelRepository.findAll() );
        lsPersoneel.setItemCaptionGenerator( e -> {
            return e.getVoorNaam() + " " + e.getAchterNaam();
        } );
    }

    private void setUpBinder() {
        projectBinder.forField(lsPersoneel).bind(x -> x.getToegewezenTechniekers(), DetailTicket::setToegewezenTechniekers);
        projectBinder.forField(cbAantalPersonen).bind(x -> x.getAantalTechniekers(), DetailTicket::setAantalTechniekers);
        projectBinder.forField(checkbVolledigUitgevoerd).bind(x -> x.isOpdrachtAfgewerkt(), DetailTicket::setOpdrachtAfgewerkt);

        projectBinder.addValueChangeListener(x -> {
            mainTicketRepository.save(geselecteerdMainTicket);
        });

    }

    private void setUpProductGrid() {

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
                geselecteerdDatail.setBenodigdheden((ArrayList<Product>) productList);
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
                    geselecteerdDatail.setBenodigdheden((ArrayList<Product>) productList);
                    mainTicketRepository.save( geselecteerdMainTicket );
                });
                button.setStyleName("danger");
                return button;
            });
    }


    private void setUpBHaalFotosOp() {

        imageGird.removeAllComponents();
        if(geselecteerdDatail.getPictureList() != null){
            gridFSService.findFilesForDetailTicket( geselecteerdDatail, null );


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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String[] parameters = event.getParameters().split( "/" );
        try{
            geselecteerdMainTicket = mainTicketRepository.findByDetails_AmNummerContains( parameters[0] ).get( 0 );
            geselecteerdDatail = geselecteerdMainTicket.getDetails().stream().filter( x -> x.getamNummer().matches( parameters[0] ) ).findFirst().get();
        }
        catch (Exception e){
            Notification.show( "Ticket voor dit detail is niet gevonden" );
        }
        try{
            infoLabel.setValue(geselecteerdDatail.getamNummer() + " aangemaakt op : " + geselecteerdDatail.getDetailAanmaakDatum().format( formatter ));
        }
        catch (Exception e){
            infoLabel.setValue(geselecteerdDatail.getamNummer() );
        }
        infoLabelOpdracht.setValue( geselecteerdDatail.getOmschrijvingVraagKlacht() + " " + geselecteerdMainTicket.getVraagKlant() );
        if(geselecteerdMainTicket.getOpdrachtgever().getBedrijfsNaam() != null){
            try{
                String klantGegevens = geselecteerdMainTicket.getOpdrachtgever().getBedrijfsNaam() + "\n" +
                        geselecteerdMainTicket.getContactPersoonKlant().getVoorNaam() + " " +
                        geselecteerdMainTicket.getContactPersoonKlant().getNaam() + "\n" +
                        geselecteerdMainTicket.getContactPersoonKlant().getGsmNummer();
                taKlant.setValue( klantGegevens );
            }
            catch (Exception e ){
                Notification.show( "Geen Opdrachtgever geselecteerd" );
            }

        }

            taCommentaar.setValue( geselecteerdDatail.getInterneOpmerkingen() + "\n" + geselecteerdDatail.getOmschrijvingVraagKlacht() );

        projectBinder.setBean( geselecteerdDatail );
        if((geselecteerdDatail.getBenodigdheden() != null)&&(geselecteerdDatail.getBenodigdheden().size() > 0)){
            productList = geselecteerdDatail.getBenodigdheden();
            productGrid.setItems(productList);
        }
        else{
            productList.clear();
            productList.add(new Product());
            productGrid.setItems(productList);
        }

        //get images from detail and show it on the imageGrid

        setUpBHaalFotosOp();




    }
}
