package com.biprom.bram.amcalapp.ui.components;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.biprom.bram.amcalapp.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.amcalapp.data.entity.mongodbEntities.MainTicket;
import com.biprom.bram.amcalapp.data.entity.mongodbEntities.Personeel;
import com.biprom.bram.amcalapp.data.entity.mongodbEntities.WerkUren;
import com.biprom.bram.amcalapp.mongoRepositories.PersoneelRepository;
import com.biprom.bram.amcalapp.mongoRepositories.WerkUrenRepository;
import com.biprom.bram.amcalapp.data.entity.CustomPair;
import com.biprom.bram.amcalapp.mongoRepositories.MainTicketRepository;
import com.biprom.bram.amcalapp.ui.subWindows.CheckPauzeSubWindow;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.renderers.HtmlRenderer;

@SpringComponent
@PrototypeScope
public class BatchGrid extends Grid<DetailTicket> {

	@Autowired
	private MainTicketRepository mainTicketRepository;

	@Autowired
	private WerkUrenRepository werkUrenRepository;

	@Autowired
	private PersoneelRepository personeelRepository;

	@Autowired
	CheckPauzeSubWindow checkPauzeSubWindow;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	Column<DetailTicket, String> positieColumn;
	Column<DetailTicket, CheckBox> zuurbehColumn;
	Column<DetailTicket, CheckBox> zuurbehOKColumn;
	Column<DetailTicket, CheckBox> zandstrColumn;
	Column<DetailTicket, CheckBox> zandstrOKColumn;
	Column<DetailTicket, CheckBox> herschColumn;
	Column<DetailTicket, CheckBox> herschOKColumn;
	Column<DetailTicket, TextField> leverdataColumn;
	Column<DetailTicket, CheckBox> alleOnderdelenBinnenColumn;
	Column<DetailTicket, String> herstelDatumColumn;

	String gridType = "NA";

	List<WerkUren> laatsteStatusElkeTechniekerSTART = new ArrayList<>(  );
	List<WerkUren> laatsteStatusElkeTechniekerSTOP = new ArrayList<>(  );
	List<Button>techniekerButtonList = new ArrayList<>(  );

	List<Label>labelList;
	List<Button>buttonList;

	Personeel geslecteerdPersoneel;

	WerkUren vorigWerkUur;

	public BatchGrid() {

		addStyleName("orders-grid");
		setSizeFull();

		// Add stylenames to rows
		setStyleGenerator( BatchGrid::getRowStyle);

		addColumn(detailTicket -> {
			return detailTicket.getamNummer();
		}).setId("amSortColumn").setHidden(true);

		addColumn(detailTicket -> {
			try {
				return detailTicket.getCheckListBestek().getUitersteHerstelDatum().toLocalDate();
			}
			catch (Exception e){
				return "GN deadline";
			}
		}).setId("deadLineSortColumn").setHidden(true);

		// Deadline


			Column<DetailTicket, String> dueColumn = addColumn(
					detailTicket -> twoRowCell(getTimeHeader(detailTicket.getCheckListBestek().getUitersteHerstelDatum().toLocalDate()), String.valueOf(detailTicket.getCheckListBestek().getUitersteHerstelDatum().format(formatter))),
					new HtmlRenderer());
			dueColumn.setSortProperty("dueDate", "dueTime");
			dueColumn.setStyleGenerator(order -> "summary");


		addComponentColumn(detailTicket -> {
			TextField tf = new TextField(detailTicket.getamNummer());
			tf.setReadOnly(true);
			tf.setValue(detailTicket.getamNummer());
			if(gridType.matches("DEMONTAGE")){
				if(detailTicket.getDetailAanmaakDatum().isAfter(LocalDateTime.now().minusDays(1))) {
					tf.setStyleName("my_green_style");
				}
				if(detailTicket.getDetailAanmaakDatum().isBefore(LocalDateTime.now().minusDays(1))) {
					tf.setStyleName("my_green_style");
				}
				if(detailTicket.getDetailAanmaakDatum().isBefore(LocalDateTime.now().minusDays(2))) {
					tf.setStyleName("my_yellow_style");
				}
				if(detailTicket.getDetailAanmaakDatum().isBefore(LocalDateTime.now().minusDays(3))) {
					tf.setStyleName("my_red_style");
				}
			}
			else{
				if(detailTicket.getCheckListBestek().getUitersteHerstelDatum().isAfter(LocalDateTime.now().minusDays(1))) {
					tf.setStyleName("my_green_style");
				}
				if(detailTicket.getCheckListBestek().getUitersteHerstelDatum().isBefore(LocalDateTime.now().minusDays(1))) {
					tf.setStyleName("my_green_style");
				}
				if(detailTicket.getCheckListBestek().getUitersteHerstelDatum().isBefore(LocalDateTime.now().minusDays(2))) {
					tf.setStyleName("my_yellow_style");
				}
				if(detailTicket.getCheckListBestek().getUitersteHerstelDatum().isBefore(LocalDateTime.now().minusDays(3))) {
					tf.setStyleName("my_red_style");
				}
			}

			return tf;
		});

		addComponentColumn(detailTicket -> {
			Label label = new Label(detailTicket.getPomp());
			label.setStyleName("h3");
			return label;
		});


		positieColumn = addColumn(x -> {
				if(x.getCheckListBestek().getPositieMagazijn() != null){
					return x.getCheckListBestek().getPositieMagazijn();
				}
				else{
					return "N/A";
				}
			}).setCaption("positie");

		zuurbehColumn = addComponentColumn(x -> {
			CheckBox checkbox = new CheckBox(  );
			checkbox.setEnabled( false );
			checkbox.setValue( x.getCheckListBestek().isZuurbehandeling() );
			return checkbox;
		}).setCaption("Zuurbeh");

		zuurbehOKColumn = addComponentColumn( e ->  {
			CheckBox checkbox = new CheckBox(  );
			checkbox.setValue( e.getCheckListBestek().isZuurbehandelingUitgevoerd() );
			checkbox.addValueChangeListener( f -> {
				MainTicket mainTicket = mainTicketRepository.findByDetails_AmNummerContains( e.getamNummer() ).get( 0 );
				DetailTicket detailTicket = mainTicket.getDetails().stream().filter( filter -> filter.getamNummer().matches( e.getamNummer() ) ).findFirst().get();
				detailTicket.getCheckListBestek().setZuurbehandelingUitgevoerd( f.getValue() );
				mainTicketRepository.save( mainTicket );
			} );
			return checkbox;
		}).setCaption( "OK" );

		zandstrColumn = addComponentColumn( e ->  {
			CheckBox checkbox = new CheckBox(  );
			checkbox.setEnabled( false );
			checkbox.setValue( e.getCheckListBestek().isZandstralenEnHerschilderen() );
			return checkbox;
		}).setCaption( "Zandstr" );

		zandstrOKColumn = addComponentColumn( e ->  {
			CheckBox checkbox = new CheckBox(  );
			checkbox.setValue( e.getCheckListBestek().isPompMWGezandstraald() );
			checkbox.addValueChangeListener( f -> {
				MainTicket mainTicket = mainTicketRepository.findByDetails_AmNummerContains( e.getamNummer() ).get( 0 );
				DetailTicket detailTicket = mainTicket.getDetails().stream().filter( filter -> filter.getamNummer().matches( e.getamNummer() ) ).findFirst().get();
				detailTicket.getCheckListBestek().setPompMWGezandstraald( f.getValue() );
				mainTicketRepository.save( mainTicket );

			} );
			return checkbox;
		}).setCaption( "OK" );

		herschColumn = addComponentColumn( e ->  {
			CheckBox checkbox = new CheckBox(  );
			checkbox.setEnabled( false );
			checkbox.setValue( e.getCheckListBestek().isHerschilderen() );
			return checkbox;
		}).setCaption( "Hersch" );

		herschOKColumn = addComponentColumn( e ->  {
			CheckBox checkbox = new CheckBox(  );
			checkbox.setValue( e.getCheckListBestek().isPompMWGespoten() );
			checkbox.addValueChangeListener( f -> {
				MainTicket mainTicket = mainTicketRepository.findByDetails_AmNummerContains( e.getamNummer() ).get( 0 );
				DetailTicket detailTicket = mainTicket.getDetails().stream().filter( filter -> filter.getamNummer().matches( e.getamNummer() ) ).findFirst().get();
				detailTicket.getCheckListBestek().setPompMWGespoten( f.getValue() );
				mainTicketRepository.save( mainTicket );
			} );
			return checkbox;
		}).setCaption( "OK" );


		addComponentColumn( x -> {
			Button button = new Button( "START" );
			button.setStyleName( "primary" );
			button.setWidth( "100%" );
			button.setHeight( "100%" );
			CustomPair lastStatusOfStart =  getLastStatusOfSNStart(x);
			if((lastStatusOfStart.getKey() == true)&&(!lastStatusOfStart.getValue().matches( "Gestopt" ))){
				button.setStyleName( "friendly" );
			}
			else if((lastStatusOfStart.getKey() == false)&&(lastStatusOfStart.getValue().matches( "Niet Gestart" ))){
				button.setStyleName( "primary" );
			}
			else if((lastStatusOfStart.getKey() == true)&&(lastStatusOfStart.getValue().matches( "Gestopt" ))){
				button.setStyleName( "alGestart-button" );
			}
			button.addClickListener( e -> {
				try {
					WerkUren werkUren = new WerkUren();
					switch (gridType){
						case "HERSTELLING" : werkUren.setOmschrijving( "Start Herstelling : " + x.getamNummer() );
						case "DEMONTAGE" : werkUren.setOmschrijving( "Start Demontage : " + x.getamNummer() );
						case "VOORBEREIDING" : werkUren.setOmschrijving( "Start Voorbereiding : " + x.getamNummer() );
					}

					werkUren.setInlogNaamTechnieker( geslecteerdPersoneel.getInlogNaam() );
					werkUren.setStartDatumTijd( LocalDateTime.now() );
					werkUrenRepository.save( werkUren );
					Notification.show( "Start :  "  + " " + geslecteerdPersoneel.getInlogNaam() + " om : " + LocalDateTime.now().format( formatter ) );
					button.setStyleName( "friendly" );
					getLastStatusOfSNStart( x );
					buttonList.stream().forEach(y -> y.setStyleName("primary"));
					labelList.stream().filter(filter -> filter.getId().matches("l"+geslecteerdPersoneel.getInlogNaam())).findFirst().get().setValue(x.getamNummer() + " GESTART");
					this.getDataProvider().refreshAll();
					// deselecteer de technieker
					geslecteerdPersoneel = null;
				}
				catch (Exception e1){
					Notification.show( "Gelieve eerst een technieker aan te duiden" );
				}
			} );
			return button;
		} );

		addComponentColumn( x -> {
			getLastStatusOfSNStart( x );
			Label label = new Label( x.getLaatsteStatus());
			label.setStyleName( "h3 colored" );
			return label;
		} );

		addComponentColumn( x -> {
			Button button = new Button( "STOP" );
			button.setStyleName( "primary" );
			button.setWidth( "100%" );
			button.setHeight( "100%" );
			CustomPair lastStatusOfStart =  getLastStatusOfSNStart(x);
			button.setStyleName( "primary" );
//            if(lastStatusOfStart.getKey() == false){
//                button.setStyleName( "friendly" );
//            }
//            else{
//                button.setStyleName( "primary" );
//            }
			button.addClickListener( e -> {
				try {
					LocalDateTime middag = LocalDateTime.now().withHour(12).withMinute(0);;
					WerkUren werkUren = new WerkUren();
					werkUren.setInlogNaamTechnieker(geslecteerdPersoneel.getInlogNaam());
						switch (gridType) {
							case "HERSTELLING":
								werkUren.setOmschrijving("Stop Herstelling : " + x.getamNummer());
								vorigWerkUur = werkUrenRepository.findByInlogNaamTechnieker(geslecteerdPersoneel.getInlogNaam()).stream().filter(filter -> filter.getStartDatumTijd() != null).filter(filter -> filter.getOmschrijving().contains("Start Herstelling : " + x.getamNummer())).max(Comparator.comparing(WerkUren::getStartDatumTijd)).get();
								break;
							case "DEMONTAGE":
								werkUren.setOmschrijving("Stop Demontage : " + x.getamNummer());
								vorigWerkUur = werkUrenRepository.findByInlogNaamTechnieker(geslecteerdPersoneel.getInlogNaam()).stream().filter(filter -> filter.getStartDatumTijd() != null).filter(filter -> filter.getOmschrijving().contains("Start Demontage : " + x.getamNummer())).max(Comparator.comparing(WerkUren::getStartDatumTijd)).get();
								break;
							case "VOORBEREIDING":
								werkUren.setOmschrijving("Stop Voorbereiding : " + x.getamNummer());
								vorigWerkUur = werkUrenRepository.findByInlogNaamTechnieker(geslecteerdPersoneel.getInlogNaam()).stream().filter(filter -> filter.getStartDatumTijd() != null).filter(filter -> filter.getOmschrijving().contains("Start VOORBEREIDING : " + x.getamNummer())).max(Comparator.comparing(WerkUren::getStartDatumTijd)).get();
								break;
						}



					if ((vorigWerkUur.getStartDatumTijd().isBefore(middag)) && (LocalDateTime.now().isAfter(middag))) {
						//Show pauzecheckerWindow
						checkPauzeSubWindow.getCheckPauzeView().setLbTijdslot("Van : " + vorigWerkUur.getStartDatumTijd().format(dateTimeFormatter) + "  ->  Tot = " + LocalDateTime.now().format(dateTimeFormatter));
						checkPauzeSubWindow.getCheckPauzeView().setLbTotaalTijdSlot("Totaaltijd = " + Duration.between(vorigWerkUur.getStartDatumTijd(), LocalDateTime.now()).toMinutes() + " minuten");
						UI.getCurrent().addWindow(checkPauzeSubWindow);
						checkPauzeSubWindow.setHeight("600px");
						checkPauzeSubWindow.setWidth("800");
						checkPauzeSubWindow.setModal(true);
						checkPauzeSubWindow.addCloseListener(y -> {
							if (checkPauzeSubWindow.getCheckPauzeView().getAangepasteEindTijd() != null) {
								werkUren.setStartDatumTijd(checkPauzeSubWindow.getCheckPauzeView().getAangepasteEindTijd());
								werkUrenRepository.save(werkUren);
								button.setStyleName( "friendly" );
								getLastStatusOfSNStart( x );
								buttonList.stream().forEach(z -> z.setStyleName("primary"));
								labelList.stream().filter(filter -> filter.getId().matches("l"+geslecteerdPersoneel.getInlogNaam())).findFirst().get().setValue(x.getamNummer() + " GESTOPT");
								this.getDataProvider().refreshAll();
								geslecteerdPersoneel = null;
							}
							else {
								werkUren.setStartDatumTijd(LocalDateTime.now());
								werkUrenRepository.save(werkUren);
								button.setStyleName( "friendly" );
								getLastStatusOfSNStart( x );
								buttonList.stream().forEach(z -> z.setStyleName("primary"));
								labelList.stream().filter(filter -> filter.getId().matches("l"+geslecteerdPersoneel.getInlogNaam())).findFirst().get().setValue(x.getamNummer() + " GESTOPT");
								this.getDataProvider().refreshAll();
								geslecteerdPersoneel = null;
							}
						});


					}
					else{
						werkUren.setInlogNaamTechnieker( geslecteerdPersoneel.getInlogNaam() );
						werkUren.setStartDatumTijd( LocalDateTime.now() );
						werkUrenRepository.save( werkUren );
						Notification.show( "Stop :  " + " " + geslecteerdPersoneel.getInlogNaam() + " om : " + LocalDateTime.now().format( formatter ) );
						button.setStyleName( "friendly" );
						getLastStatusOfSNStart( x );
						buttonList.stream().forEach(y -> y.setStyleName("primary"));
						labelList.stream().filter(filter -> filter.getId().matches("l"+geslecteerdPersoneel.getInlogNaam())).findFirst().get().setValue(x.getamNummer() + " GESTOPT");
						this.getDataProvider().refreshAll();

						// deselecteer de technieker
						geslecteerdPersoneel = null;
					}

				}
				catch (Exception e1){
					Notification.show( "Gelieve eerst een technieker aan te duiden, of AM te starten" );
				}
			} );
			return button;
		} );

		addComponentColumn( x -> {
			Button button = new Button( "VOLLEDIG AF" );
			button.setStyleName( "primary" );
			button.setWidth( "100%" );
			button.setHeight( "100%" );
			button.addClickListener( e -> {
				try {

					WerkUren werkUren = new WerkUren();
					switch (gridType) {
						case "HERSTELLING":
							werkUren.setOmschrijving("STOP Herstelling : " + x.getamNummer());
						case "DEMONTAGE":
							werkUren.setOmschrijving("STOP Demontage : " + x.getamNummer());
						case "VOORBEREIDING":
							werkUren.setOmschrijving("STOP Voorbereiding : " + x.getamNummer());
					}

					werkUren.setInlogNaamTechnieker( geslecteerdPersoneel.getInlogNaam() );
					werkUren.setStartDatumTijd( LocalDateTime.now() );
					werkUrenRepository.save( werkUren );
					Notification.show( "Afgewerkt :  " + " " + geslecteerdPersoneel.getInlogNaam() + " om : " + LocalDateTime.now().format( formatter ) );
					button.setStyleName( "friendly" );
					getLastStatusOfSNStart( x );
					buttonList.stream().forEach(y -> y.setStyleName("primary"));
					labelList.stream().filter(filter -> filter.getId().matches("l"+geslecteerdPersoneel.getInlogNaam())).findFirst().get().setValue(x.getamNummer() + " GESTOPT");
					this.getDataProvider().refreshAll();

					// deselecteer de technieker
					geslecteerdPersoneel = null;
				}
				catch (Exception e1){
					Notification.show( "Gelieve eerst een technieker aan te duiden" );
				}
			} );
			return button;
		} );



		leverdataColumn = addComponentColumn( e -> {
			TextField textField = new TextField(  );
			textField.setWidth( "100%" );
			try {
				textField.setValue( String.valueOf( e.getBenodigdheden().stream().filter( filter -> filter.isbProductBesteld() && !filter.isbProductGeleverd() ).map( map -> String.valueOf( ChronoUnit.DAYS.between( LocalDate.now(), map.getDateProductLeverdatum() ) )).collect( Collectors.toList() ) ) );
			}
			catch (Exception f ){

			}
			if((e.getBenodigdheden() != null)&&(e.getBenodigdheden().stream().filter( filter -> (filter.getDateProductLeverdatum() != null)&&(filter.getDateProductLeverdatum().isBefore( LocalDate.now()))&&(!filter.isbProductGeleverd())).count() > 0)){
				textField.setStyleName( "my_red_style" );
			}
			else{
				textField.setStyleName( "" );
			}

			return textField;
		} ).setCaption( "Leverdata");

		alleOnderdelenBinnenColumn = addComponentColumn( e -> {
			CheckBox checkbox = new CheckBox(  );
			checkbox.setEnabled( false );
			checkbox.setValue( e.isbAlleOnderdelenBinnen() );
			return checkbox;
		} ).setCaption( "Alle onderdelen binnen" );

		herstelDatumColumn = addColumn( e -> {
			if(e.getCheckListBestek().getUitersteHerstelDatum() != null){
				return  String.valueOf(Period.between(LocalDate.now(),e.getCheckListBestek().getUitersteHerstelDatum().toLocalDate()).getDays());
			}
			else{
				return "geen datum";
			}
		} ).setCaption( "Hersteldatum");


		// Naam klant
		Column<DetailTicket, String> summaryColumn = addColumn(detailTicket -> {
			String klant = detailTicket.getOpdrachtgever();
			return twoRowCell(klant, getOrderSummary(detailTicket));
		}, new HtmlRenderer()).setExpandRatio(3).setSortProperty("customer.fullName").setMinimumWidthFromContent(false);
		summaryColumn.setStyleGenerator(order -> "summary");

		this.setHeight( "100%");
	}


	@PostConstruct
	protected void init() {

	}

	/**
	 * Makes date into a more readable form; "Today", "Mon 7", "12 Jun"
	 * 
	 * @param dueDate
	 *            The date to make into a string
	 * @return A formatted string depending on how far in the future the date
	 *         is.
	 */
	private static String getTimeHeader(LocalDate dueDate) {

			LocalDate today = LocalDate.now();
			if (dueDate.isEqual(today)) {
				return "Today";
			} else {
				// Show weekday for upcoming days
				LocalDate todayNextWeek = today.plusDays(7);
				if (dueDate.isAfter(today) && dueDate.isBefore(todayNextWeek)) {
					// "Mon 7"
					return dueDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.GERMAN) + " "
							+ dueDate.getDayOfMonth();
				} else {
					// In the past or more than a week in the future
					return dueDate.getDayOfMonth() + " " + dueDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.US);
				}
			}
	}

	private static String getRowStyle(DetailTicket detailTicket) {
//		String style = detailTicket.getState().name().toLowerCase();
//
//		long days = LocalDate.now().until(order.getDueDate(), ChronoUnit.DAYS);
//		if (days == 0) {
//			style += " today";
//		} else if (days == 1) {
//			style += " tomorrow";
//		}
//
//		return style;

		return "";
	}

	private static String getOrderSummary(DetailTicket detailTicket) {
		return detailTicket.getOmschrijvingVraagKlacht();
	}

	private static String twoRowCell(String header, String content) {
		try {
			return "<div class=\"header\">" + HtmlUtils.htmlEscape( header ) + "</div><div class=\"content\">"
					+ HtmlUtils.htmlEscape( content ) + "</div>";
		}
		catch (Exception e){
			return "<div class=\"header\">" + HtmlUtils.htmlEscape( "N/A" ) + "</div><div class=\"content\">"
					+ HtmlUtils.htmlEscape( "N/A" ) + "</div>";
		}
	}

	public void setData(List<DetailTicket> receivedDetails){
		this.setItems( receivedDetails );
	}

	public void sortByAMNumber(){
		this.sort("amSortColumn", SortDirection.ASCENDING);
	}

	public void sortByDeadLine(){
		this.sort("deadLineSortColumn", SortDirection.ASCENDING);
	}

	public String getGridType() {
		return gridType;
	}

	public void setGridType(String gridType) {
		this.gridType = gridType;
		if(!gridType.matches("VOORBEREIDING")){
			//set right columns
			removeHeaderRow(0);
			positieColumn.setHidden(true);
			zuurbehColumn.setHidden(true);
			zuurbehOKColumn.setHidden(true);
			zandstrColumn.setHidden(true);
			zandstrOKColumn.setHidden(true);
			herschColumn.setHidden(true);
			herschOKColumn.setHidden(true);
			herstelDatumColumn.setHidden(true);
			alleOnderdelenBinnenColumn.setHidden(true);
			leverdataColumn.setHidden(true);
		}

			if(gridType.matches("VOORBEREIDING")) {
				setStyleGenerator(e -> {
					if ((e.getCheckListBestek().isHerschilderen() && e.getCheckListBestek().isPompMWGespoten()) || (e.getCheckListBestek().isZandstralenEnHerschilderen() && e.getCheckListBestek().isPompMWGezandstraald())) {
						return "my_green_style_grid";
					}

					if ((e.getCheckListBestek().getUitersteHerstelDatum() != null) && (e.getCheckListBestek().getUitersteHerstelDatum().isBefore(LocalDateTime.now()))) {
						return "my_red_style_grid";
					}

//
					return "";
				});
			}
		}


	private CustomPair getLastStatusOfSNStart(DetailTicket detailTicket) {

		laatsteStatusElkeTechniekerSTOP.clear();
		laatsteStatusElkeTechniekerSTART.clear();
		WerkUren laatsteWerkUurTechnieker;
		List<WerkUren> werkUrenList = werkUrenRepository.findByOmschrijvingContains(detailTicket.getamNummer());
		for(Personeel technieker : personeelRepository.findAll()){
			try{
				List<WerkUren> werkUrenListTechnieker = werkUrenList.stream().filter( x -> x.getInlogNaamTechnieker().matches( technieker.getInlogNaam() ) ).collect( Collectors.toList());
				if (werkUrenListTechnieker != null){
					laatsteWerkUurTechnieker = werkUrenListTechnieker.stream().reduce( (first, second) ->  second).orElse( null );
					if(laatsteWerkUurTechnieker.getOmschrijving().contains( "Start" )){
						laatsteStatusElkeTechniekerSTART.add( laatsteWerkUurTechnieker );
					}
					else if(laatsteWerkUurTechnieker.getOmschrijving().contains( "Stop" )){
						laatsteStatusElkeTechniekerSTOP.add( laatsteWerkUurTechnieker );
					}
				}
			}
			catch (Exception e){
			}
		}

		if(laatsteStatusElkeTechniekerSTART.size() > 3){
			String string = "";
			for(WerkUren werkuur : laatsteStatusElkeTechniekerSTART){
				string = string.concat(werkuur.getInlogNaamTechnieker() + " "  );
			}
			detailTicket.setLaatsteStatus(string);
			return new CustomPair(true, string);
		}
		if(laatsteStatusElkeTechniekerSTART.size() > 2){
			String string = "";
			for(WerkUren werkuur : laatsteStatusElkeTechniekerSTART){
				string = string.concat(werkuur.getInlogNaamTechnieker() + " "  );
			}
			detailTicket.setLaatsteStatus(string);
			return new CustomPair(true, string);
		}
		if(laatsteStatusElkeTechniekerSTART.size() > 1){
			String string = "";
			for(WerkUren werkuur : laatsteStatusElkeTechniekerSTART){
				string = string.concat(werkuur.getInlogNaamTechnieker() + " "  );
			}
			detailTicket.setLaatsteStatus(string);
			return new CustomPair(true, string);
		}
		if(laatsteStatusElkeTechniekerSTART.size() > 0){
			String string = "";
			for(WerkUren werkuur : laatsteStatusElkeTechniekerSTART){
				string = string.concat(werkuur.getInlogNaamTechnieker() + " "  );
			}
			detailTicket.setLaatsteStatus(string);
			return new CustomPair(true, string);
		}
		if(laatsteStatusElkeTechniekerSTOP.size()>0){
			detailTicket.setLaatsteStatus("Gestopt");
			return new CustomPair(true, "Gestopt");
		}
		else {
			detailTicket.setLaatsteStatus("Niet Gestart");
			return new CustomPair(false, "Niet Gestart");
		}
	}

	public void setGeslecteerdPersoneel(Personeel geslecteerdPersoneel) {
		this.geslecteerdPersoneel = geslecteerdPersoneel;
	}

	public void setPersoneelButtons(List<Button>personeelButtons){
		buttonList = personeelButtons;
	}
	public void setPersoneelLabels(List<Label>personeelLabels){
		labelList = personeelLabels;
	}

}
