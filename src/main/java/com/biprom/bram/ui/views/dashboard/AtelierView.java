package com.biprom.bram.ui.views.dashboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.MainTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.Personeel;
import com.biprom.bram.backend.MainTicketRepository;
import com.biprom.bram.backend.PersoneelRepository;
import com.biprom.bram.ui.components.HerstellingDataProvider;
import com.biprom.bram.ui.components.ProjectGrid;
import com.biprom.bram.ui.views.checkList.CheckListView;
import com.biprom.bram.ui.views.inoxSubView.InoxSubView;
import com.vaadin.navigator.Navigator;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.board.Row;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.biprom.bram.backend.data.DeliveryStats;
import com.biprom.bram.ui.components.BatchGrid;
import com.biprom.bram.ui.navigation.NavigationManager;

/**
 * The dashboard view showing statistics about sales and deliveries.
 * <p>
 * Created as a single View class because the logic is so simple that using a
 * pattern like MVP would add much overhead for little gain. If more complexity
 * is added to the class, you should consider splitting out a presenter.
 */
@SpringView
public class AtelierView extends AtelierDesign implements View {

	PersoneelRepository personeelRepository;
	InoxSubView inoxSubView;
	MainTicketRepository mainTicketRepository;

	Personeel geslecteerdPersoneel;

	private final NavigationManager navigationManager;
	private final HerstellingDataProvider herstellingDataProvider;

	private final BoardLabel demontageLabel = new BoardLabel("Demontage", "3", "today");
	private final BoardBox demontageBox = new BoardBox( demontageLabel );

	private final BoardLabel voorbereidingLabel = new BoardLabel("Voorbereiding", "1", "na");
	private final BoardBox voorbereidingBox = new BoardBox( voorbereidingLabel );

	private final BoardLabel herstellingLabel = new BoardLabel("Herstelling", "2", "new");
	private final BoardBox herstellingBox = new BoardBox( herstellingLabel );

	private final BoardButton syncButton = new BoardButton("SYNC");
	private final BoardBox syncBox = new BoardBox( syncButton );

	private final BoardLabel syncInfo = new BoardLabel("Update", "1", "tomorrow");
	private final BoardBox syncInfoBox = new BoardBox( syncInfo );

	private final BatchGrid teHerstellenGrid;
	private final BatchGrid voorbereidingGrid;
	private final BatchGrid demontageGrid;
	private final ProjectGrid inoxGrid;

	Navigator subNavigator;

	List<Label>labelList = new ArrayList<>(  );
	List<Button>techniekerButtonList = new ArrayList<>(  );

	@Autowired
	public AtelierView(NavigationManager navigationManager,
					   HerstellingDataProvider herstellingDataProvider,
					   BatchGrid teHerstellenGrid,
					   BatchGrid voorbereidingGrid,
					   BatchGrid demontageGrid,
					   ProjectGrid inoxGrid,
					   PersoneelRepository personeelRepository,
					   InoxSubView inoxSubView,
					   MainTicketRepository mainTicketRepository) {

		this.navigationManager = navigationManager;
		this.herstellingDataProvider = herstellingDataProvider;
		this.personeelRepository = personeelRepository;
		this.teHerstellenGrid = teHerstellenGrid;
		this.demontageGrid = demontageGrid;
		this.voorbereidingGrid = voorbereidingGrid;
		this.inoxGrid = inoxGrid;
		this.inoxSubView = inoxSubView;
		this.mainTicketRepository = mainTicketRepository;


		teHerstellenGrid.setHeight( "600px" );
		demontageGrid.setHeight( "600px" );
		voorbereidingGrid.setHeight( "600px" );
		inoxGrid.setHeight("600px");

		Label labelDemontage = new Label( "Demontage" );
		labelDemontage.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelDemontage );
		vLayoutGrid.addComponent( demontageGrid );

		Label labelHerstellen = new Label( "Te Herstellen" );
		labelHerstellen.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelHerstellen);
		vLayoutGrid.addComponent(teHerstellenGrid);

		Label labelVoorbereiding = new Label( "Voorbereidig" );
		labelVoorbereiding.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelVoorbereiding );
		vLayoutGrid.addComponent( voorbereidingGrid );

		Label labelInox = new Label( "Inoxwerk" );
		labelVoorbereiding.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelInox );
		vLayoutGrid.addComponent( inoxGrid );

		setUpButtonsTechniekers();

		subNavigator = UI.getCurrent().getNavigator();

	}

	@PostConstruct
	public void init() {
		setResponsive(true);

		Row rowStats = board.addRow(demontageBox, herstellingBox, voorbereidingBox);
		rowStats.addStyleName("board-row-group");

		Row rowUpdate = boardUpdate.addRow(syncBox, syncInfoBox);
		rowUpdate.addStyleName("board-row-group");

		teHerstellenGrid.setGridType("HERSTELLING");
		teHerstellenGrid.setId("dueGrid");
		teHerstellenGrid.setSizeFull();
		List<DetailTicket>teHerstellen = herstellingDataProvider.getAllDetailsToHerstel();
		teHerstellenGrid.setData( teHerstellen );
		herstellingLabel.setContent(String.valueOf(teHerstellen.size()));
		teHerstellenGrid.setHeightMode(HeightMode.UNDEFINED);
		teHerstellenGrid.sortByDeadLine();

		demontageGrid.setGridType("DEMONTAGE");
		demontageGrid.setId("dueGrid");
		demontageGrid.setSizeFull();
		List<DetailTicket>teDemonteren = herstellingDataProvider.getAllDetailsToDemontage();
		demontageGrid.setData( teDemonteren );
		demontageLabel.setContent(String.valueOf(teDemonteren.size()));
		demontageGrid.setHeightMode(HeightMode.UNDEFINED);
		demontageGrid.sortByAMNumber();

		voorbereidingGrid.setGridType("VOORBEREIDING");
		voorbereidingGrid.setId("dueGrid");
		voorbereidingGrid.setSizeFull();
		List<DetailTicket>voorTeBereiden = herstellingDataProvider.getAllDetailsToVoorbereiding();
		voorbereidingGrid.setData( voorTeBereiden );
		voorbereidingLabel.setContent(String.valueOf(voorTeBereiden.size()));
		voorbereidingGrid.setHeightMode(HeightMode.UNDEFINED);
		voorbereidingGrid.sortByDeadLine();

		inoxGrid.setGridType("INOX");
		inoxGrid.setId("dueGrid");
		inoxGrid.setSizeFull();
		List<DetailTicket>projectList = herstellingDataProvider.getAllDetailsToInox();
		inoxGrid.setData( projectList );
		inoxGrid.setHeightMode(HeightMode.UNDEFINED);
		inoxGrid.sortByAMNumber();


		voorbereidingGrid.addSelectionListener( e -> selectedOrder(e.getFirstSelectedItem().get(), "CheckListFase.VOORBEREIDING") );
		demontageGrid.addSelectionListener( e -> selectedOrder(e.getFirstSelectedItem().get(), "CheckListFase.DEMONTAGE"));
		teHerstellenGrid.addSelectionListener(e -> selectedOrder(e.getFirstSelectedItem().get(), "CheckListFase.HERSTELLING"));
		inoxGrid.addSelectionListener(e -> selectedProject(e.getFirstSelectedItem().get()));

		bipromButton.addClickListener(x -> {
			List<MainTicket>mainTicketList = mainTicketRepository.findByDetails_BHerstellingBestek(true);
			for(MainTicket mainTicket : mainTicketList){
				for(DetailTicket detailTicketToCheck : mainTicket.getDetails()){
					if(detailTicketToCheck.isbHerstellingBestek() == true){
						detailTicketToCheck.getCheckListBestek().setAantastingen(formatString(detailTicketToCheck.getCheckListBestek().getAantastingen()));
						detailTicketToCheck.getCheckListBestek().setAsafdichting(formatString(detailTicketToCheck.getCheckListBestek().getAsafdichting()));
						detailTicketToCheck.getCheckListBestek().setBinnenwerk(formatString(detailTicketToCheck.getCheckListBestek().getBinnenwerk()));
						detailTicketToCheck.getCheckListBestek().setContaminatie(formatString(detailTicketToCheck.getCheckListBestek().getContaminatie()));
						detailTicketToCheck.getCheckListBestek().setControlBox(formatString(detailTicketToCheck.getCheckListBestek().getControlBox()));
						detailTicketToCheck.getCheckListBestek().setDichtingen(formatString(detailTicketToCheck.getCheckListBestek().getDichtingen()));
						detailTicketToCheck.getCheckListBestek().setGarantie(formatString(detailTicketToCheck.getCheckListBestek().getGarantie()));
						detailTicketToCheck.getCheckListBestek().setIsolatieWeerstand(formatString(detailTicketToCheck.getCheckListBestek().getIsolatieWeerstand()));
						detailTicketToCheck.getCheckListBestek().setKamers(formatString(detailTicketToCheck.getCheckListBestek().getKamers()));
						detailTicketToCheck.getCheckListBestek().setLagers(formatString(detailTicketToCheck.getCheckListBestek().getLagers()));
						detailTicketToCheck.getCheckListBestek().setMotorDichtingen(formatString(detailTicketToCheck.getCheckListBestek().getMotorDichtingen()));
						detailTicketToCheck.getCheckListBestek().setMotorKabel(formatString(detailTicketToCheck.getCheckListBestek().getMotorKabel()));
						detailTicketToCheck.getCheckListBestek().setMotorLagers(formatString(detailTicketToCheck.getCheckListBestek().getMotorLagers()));
						detailTicketToCheck.getCheckListBestek().setMotorVerbrand(formatString(detailTicketToCheck.getCheckListBestek().getMotorVerbrand()));
						detailTicketToCheck.getCheckListBestek().setPompas(formatString(detailTicketToCheck.getCheckListBestek().getPompas()));
						detailTicketToCheck.getCheckListBestek().setPompBinnengebracht(formatString(detailTicketToCheck.getCheckListBestek().getPompBinnengebracht()));
						detailTicketToCheck.getCheckListBestek().setPompstaat(formatString(detailTicketToCheck.getCheckListBestek().getPompstaat()));
						detailTicketToCheck.getCheckListBestek().setPompStatus(formatString(detailTicketToCheck.getCheckListBestek().getPompStatus()));
						detailTicketToCheck.getCheckListBestek().setRotorStatorFlens(formatString(detailTicketToCheck.getCheckListBestek().getRotorStatorFlens()));

						detailTicketToCheck.getCheckListBestek().setSecundaireAsafdichting(formatString(detailTicketToCheck.getCheckListBestek().getSecundaireAsafdichting()));
						detailTicketToCheck.getCheckListBestek().setSpaltringen(formatString(detailTicketToCheck.getCheckListBestek().getSpaltringen()));
						detailTicketToCheck.getCheckListBestek().setVentilator(formatString(detailTicketToCheck.getCheckListBestek().getVentilator()));
						detailTicketToCheck.getCheckListBestek().setVochtInMotor(formatString(detailTicketToCheck.getCheckListBestek().getVochtInMotor()));
						detailTicketToCheck.getCheckListBestek().setWaaiers(formatString(detailTicketToCheck.getCheckListBestek().getWaaiers()));
						detailTicketToCheck.getCheckListBestek().setWaterInMotor(formatString(detailTicketToCheck.getCheckListBestek().getWaterInMotor()));
						detailTicketToCheck.getCheckListBestek().setWikkelingsWaardes(formatString(detailTicketToCheck.getCheckListBestek().getWikkelingsWaardes()));

					}
				}
				mainTicketRepository.save(mainTicket);
				System.out.println(mainTicket.getTicketNummer() + " is aangepast!!!");
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



	private void updateLabels(DeliveryStats deliveryStats) {
		demontageLabel.setContent(deliveryStats.getDeliveredToday() + "/" + deliveryStats.getDueToday());
		voorbereidingLabel.setContent(Integer.toString(deliveryStats.getNotAvailableToday()));
		voorbereidingBox.setNeedsAttention(deliveryStats.getNotAvailableToday() > 0);
		herstellingLabel.setContent(Integer.toString(deliveryStats.getNewOrders()));
	}

	public void selectedOrder(DetailTicket selectedDetail, String checkListFase) {
		switch (checkListFase){
			case "CheckListFase.VOORBEREIDING":
				navigationManager.navigateTo( CheckListView.class, selectedDetail.getamNummer(), selectedDetail.getOpdrachtgever(),"CheckListFase.VOORBEREIDING");
				break;
			case "CheckListFase.DEMONTAGE":
				navigationManager.navigateTo( CheckListView.class, selectedDetail.getamNummer(), selectedDetail.getOpdrachtgever(),"CheckListFase.DEMONTAGE");
				break;
			case "CheckListFase.HERSTELLING":
				navigationManager.navigateTo( CheckListView.class, selectedDetail.getamNummer(), selectedDetail.getOpdrachtgever(),"CheckListFase.HERSTELLING");
				break;
		}

	}

	private void selectedProject(DetailTicket selectedDetail) {
		navigationManager.navigateTo(InoxSubView.class,selectedDetail.getamNummer(), selectedDetail.getOpdrachtgever());
	}

	private void setUpButtonsTechniekers() {

		labelList.clear();
		techniekerButtonList.clear();
		List<Personeel>personeelList = (List<Personeel>) personeelRepository.findAll();
		for(Personeel personeel : personeelList.stream().filter( x -> x.getFunctie().matches( "Technieker" ) ).collect( Collectors.toList())){
			Button button = new Button( personeel.getInlogNaam() );
			button.setStyleName( "primary" );
			button.setId( "b"+personeel.getInlogNaam() );
			button.setWidth( "100%" );
			button.setHeight( "50px" );
			button.addClickListener( e -> {
				geslecteerdPersoneel = personeel;
				demontageGrid.setGeslecteerdPersoneel(personeel);
				voorbereidingGrid.setGeslecteerdPersoneel(personeel);
				teHerstellenGrid.setGeslecteerdPersoneel(personeel);
				inoxGrid.setGeslecteerdPersoneel(personeel);
				button.setStyleName( "friendly" );
			} );

			Label label = new Label("Status van " + personeel.getInlogNaam());
			label.setId( "l"+personeel.getInlogNaam() );
			label.setStyleName( "h3, colored" );

			labelList.add( label );
			techniekerButtonList.add( button );

			VerticalLayout verticalLayout = new VerticalLayout(  );

			verticalLayout.addComponents( button, label );

			verticalLayout.setSpacing( false );
			verticalLayout.setMargin( false );
			verticalLayout.setComponentAlignment( label, Alignment.MIDDLE_CENTER );
			verticalLayout.setComponentAlignment( button, Alignment.MIDDLE_CENTER );

			hLayoutTechniekers.addComponent( verticalLayout );
		}

		teHerstellenGrid.setPersoneelButtons(techniekerButtonList);
		teHerstellenGrid.setPersoneelLabels(labelList);

		demontageGrid.setPersoneelButtons(techniekerButtonList);
		demontageGrid.setPersoneelLabels(labelList);

		voorbereidingGrid.setPersoneelButtons(techniekerButtonList);
		voorbereidingGrid.setPersoneelLabels(labelList);

		inoxGrid.setPersoneelButtons(techniekerButtonList);
		inoxGrid.setPersoneelLabels(labelList);

	}

	}


