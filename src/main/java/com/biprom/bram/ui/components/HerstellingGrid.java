package com.biprom.bram.ui.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.biprom.bram.backend.data.entity.Order;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

@SpringComponent
@PrototypeScope
public class HerstellingGrid extends Grid<DetailTicket> {

	@Autowired
	private HerstellingDataProvider herstellingDataProvider;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

	public HerstellingGrid() {

		addStyleName("orders-grid");
		setSizeFull();
		removeHeaderRow(0);

		// Add stylenames to rows
		setStyleGenerator( HerstellingGrid::getRowStyle);

		// Due column
		Column<DetailTicket, String> dueColumn = addColumn(
				detailTicket -> twoRowCell(getTimeHeader(detailTicket.getDetailAanmaakDatum().toLocalDate()), String.valueOf(detailTicket.getDetailAanmaakDatum().format( formatter ))),
				new HtmlRenderer());
		dueColumn.setSortProperty("dueDate", "dueTime");
		dueColumn.setStyleGenerator(order -> "due");

		// Summary column
		Column<DetailTicket, String> summaryColumn = addColumn(detailTicket -> {
			String klant = detailTicket.getOpdrachtgever();
			return twoRowCell(klant, getOrderSummary(detailTicket));
		}, new HtmlRenderer()).setExpandRatio(1).setSortProperty("customer.fullName").setMinimumWidthFromContent(false);
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
				return dueDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US) + " "
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


}
