package com.biprom.bram.ui.views.dashboard;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Label;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.board.Row;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.biprom.bram.backend.data.DashboardData;
import com.biprom.bram.backend.data.DeliveryStats;
import com.biprom.bram.backend.data.entity.Order;
import com.biprom.bram.backend.data.entity.Product;
import com.biprom.bram.backend.service.HerstellingService;
import com.biprom.bram.ui.components.HerstellingGrid;
import com.biprom.bram.ui.navigation.NavigationManager;
import com.biprom.bram.ui.views.orderedit.OrderEditView;

/**
 * The dashboard view showing statistics about sales and deliveries.
 * <p>
 * Created as a single View class because the logic is so simple that using a
 * pattern like MVP would add much overhead for little gain. If more complexity
 * is added to the class, you should consider splitting out a presenter.
 */
@SpringView
public class DashboardView extends DashboardViewDesign implements View {

	private static final String DELIVERIES = "Deliveries";

	private static final String BOARD_ROW_PANELS = "board-row-panels";

	private final NavigationManager navigationManager;
	private final HerstellingService herstellingService;

	private final BoardLabel demontageLabel = new BoardLabel("Demontage", "3", "today");
	private final BoardBox demontageBox = new BoardBox( demontageLabel );

	private final BoardLabel voorbereidingLabel = new BoardLabel("Voorbereiding", "1", "na");
	private final BoardBox voorbereidingBox = new BoardBox( voorbereidingLabel );

	private final BoardLabel herstellingLabel = new BoardLabel("Herstelling", "2", "new");
	private final BoardBox herstellingBox = new BoardBox( herstellingLabel );

	private final BoardLabel inoxLabel = new BoardLabel("Inox- werk", "4", "tomorrow");
	private final BoardBox inoxBox = new BoardBox( inoxLabel );

	private final HerstellingGrid herstellingGrid;
	private final HerstellingGrid voorbereidingGrid;
	private final HerstellingGrid demontageGrid;
	private final HerstellingGrid inoxGrid;

	@Autowired
	public DashboardView(NavigationManager navigationManager,
						 HerstellingService herstellingService,
						 HerstellingGrid herstellingGrid,
						 HerstellingGrid voorbereidingGrid,
						 HerstellingGrid demontageGrid,
						 HerstellingGrid inoxGrid) {
		this.navigationManager = navigationManager;
		this.herstellingService = herstellingService;
		this.herstellingGrid = herstellingGrid;
		this.demontageGrid = demontageGrid;
		this.voorbereidingGrid = voorbereidingGrid;
		this.inoxGrid = inoxGrid;

		herstellingGrid.setHeight( "600px" );
		demontageGrid.setHeight( "600px" );
		voorbereidingGrid.setHeight( "600px" );
		inoxGrid.setHeight( "600px" );

		Label labelDemontage = new Label( "Demontage" );
		labelDemontage.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelDemontage);
		vLayoutGrid.addComponent( demontageGrid );

		Label labelVoorbereiding = new Label( "Voorbereidig" );
		labelVoorbereiding.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelVoorbereiding );
		vLayoutGrid.addComponent( voorbereidingGrid );

		Label labelHerstelling = new Label( "Herstelling" );
		labelHerstelling.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelHerstelling );
		vLayoutGrid.addComponent( herstellingGrid );

		Label labelInox = new Label( "Inox" );
		labelInox.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelInox );
		vLayoutGrid.addComponent( inoxGrid );


	}

	@PostConstruct
	public void init() {
		setResponsive(true);

		Row row = board.addRow(demontageBox, voorbereidingBox, herstellingBox,
				inoxBox);
		row.addStyleName("board-row-group");

		herstellingGrid.setId("dueGrid");
		herstellingGrid.setSizeFull();

		herstellingGrid.addSelectionListener( e -> selectedOrder(e.getFirstSelectedItem().get()));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		DashboardData data = fetchData();
		updateLabels(data.getDeliveryStats());
	}

	private DashboardData fetchData() {
		return herstellingService.getDashboardData(MonthDay.now().getMonthValue(), Year.now().getValue());
	}
	private void updateLabels(DeliveryStats deliveryStats) {
		demontageLabel.setContent(deliveryStats.getDeliveredToday() + "/" + deliveryStats.getDueToday());
		voorbereidingLabel.setContent(Integer.toString(deliveryStats.getNotAvailableToday()));
		voorbereidingBox.setNeedsAttention(deliveryStats.getNotAvailableToday() > 0);
		herstellingLabel.setContent(Integer.toString(deliveryStats.getNewOrders()));
		inoxLabel.setContent(Integer.toString(deliveryStats.getDueTomorrow()));
	}

	public void selectedOrder(Order order) {
		navigationManager.navigateTo(OrderEditView.class, order.getId());
	}

}
