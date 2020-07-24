package com.biprom.bram.ui.views.inoxSubView;

import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.MainTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.Personeel;
import com.biprom.bram.backend.data.entity.mongodbEntities.Product;
import com.biprom.bram.backend.mongoRepositories.MainTicketRepository;
import com.biprom.bram.backend.mongoRepositories.PersoneelRepository;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringComponent
@UIScope
@SpringView(name = InoxSubView.VIEW_NAME)
public class InoxSubView extends InoxSubDesign implements View {

    public static final String VIEW_NAME = "inoxPagina";

    PersoneelRepository personeelRepository;
    MainTicketRepository mainTicketRepository;

    Binder<DetailTicket>projectBinder = new Binder<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    DetailTicket geselecteerdDatail;
    MainTicket geselecteerdMainTicket;

    List<Product> productList = new ArrayList<>();

    @Autowired
    public InoxSubView(PersoneelRepository personeelRepository,
                       MainTicketRepository mainTicketRepository) {

        this.personeelRepository = personeelRepository;
        this.mainTicketRepository = mainTicketRepository;

        setUpBinder();
        setUpCbPersoneel();
        setUpProductGrid();



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
        if(geselecteerdMainTicket.getVraagKlant() != null){
            taCommentaar.setValue( geselecteerdDatail.getInterneOpmerkingen() );
        }
        projectBinder.setBean( geselecteerdDatail );
        if(geselecteerdDatail.getBenodigdheden() != null){
            productList = geselecteerdDatail.getBenodigdheden();
        }
        productGrid.setItems(productList);
    }
}
