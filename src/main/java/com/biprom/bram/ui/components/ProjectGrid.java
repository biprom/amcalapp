package com.biprom.bram.ui.components;

import com.biprom.bram.backend.data.entity.CustomPair;
import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.MainTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.Personeel;
import com.biprom.bram.backend.data.entity.mongodbEntities.WerkUren;
import com.biprom.bram.backend.mongoRepositories.MainTicketRepository;
import com.biprom.bram.backend.mongoRepositories.PersoneelRepository;
import com.biprom.bram.backend.mongoRepositories.WerkUrenRepository;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;
import org.vaadin.spring.annotation.PrototypeScope;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@SpringComponent
@PrototypeScope
public class ProjectGrid extends Grid<DetailTicket> {

	@Autowired
	private MainTicketRepository mainTicketRepository;

	@Autowired
	private WerkUrenRepository werkUrenRepository;

	@Autowired
	private PersoneelRepository personeelRepository;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

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

	public ProjectGrid() {

		addStyleName("orders-grid");
		setSizeFull();

		// Add stylenames to rows
		setStyleGenerator( ProjectGrid::getRowStyle);

		addColumn(detailTicket -> {
			return detailTicket.getamNummer();
		}).setId("amSortColumn");


		addComponentColumn(detailTicket -> {
			Label label = new Label(detailTicket.getOpdrachtgever());
			label.setStyleName("h3");
			return label;
		}).setWidth(250);


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
					werkUren.setOmschrijving( "Start Inoxwerk : " + x.getamNummer() );
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
					WerkUren werkUren = new WerkUren();
					werkUren.setOmschrijving( "Stop Inoxwerk : " + x.getamNummer() );
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

			} );
			return button;
		} );

		addComponentColumn( x -> {
			Button button = new Button( "VOLLEDIG AF" );
			button.setStyleName( "primary" );
			button.setWidth( "100%" );
			button.setHeight( "100%" );
			button.addClickListener( e -> {
					WerkUren werkUren = new WerkUren();
					werkUren.setOmschrijving( "STOP Inoxwerk : " + x.getamNummer() );
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


		// Naam klant
		Column<DetailTicket, String> summaryColumn = addColumn(detailTicket -> {
			String opdracht = detailTicket.getInterneOpmerkingen();
			return twoRowCell(opdracht, getOrderSummary(detailTicket));
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
