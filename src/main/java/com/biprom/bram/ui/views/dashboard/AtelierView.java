package com.biprom.bram.ui.views.dashboard;

import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import javax.annotation.PostConstruct;

import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.ui.components.HerstellingDataProvider;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Label;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.board.Row;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.biprom.bram.backend.data.DashboardData;
import com.biprom.bram.backend.data.DeliveryStats;
import com.biprom.bram.backend.data.entity.Order;
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
public class AtelierView extends AtelierDesign implements View {

	private static final String DELIVERIES = "Deliveries";

	private static final String BOARD_ROW_PANELS = "board-row-panels";

	private final NavigationManager navigationManager;
	private final HerstellingDataProvider herstellingDataProvider;

	private final BoardLabel demontageLabel = new BoardLabel("Demontage", "3", "today");
	private final BoardBox demontageBox = new BoardBox( demontageLabel );

	private final BoardLabel voorbereidingLabel = new BoardLabel("Voorbereiding", "1", "na");
	private final BoardBox voorbereidingBox = new BoardBox( voorbereidingLabel );

	private final BoardLabel herstellingLabel = new BoardLabel("Herstelling", "2", "new");
	private final BoardBox herstellingBox = new BoardBox( herstellingLabel );

	private final BoardLabel inoxLabel = new BoardLabel("Inox- werk", "4", "tomorrow");
	private final BoardBox inoxBox = new BoardBox( inoxLabel );

	private final BoardButton syncButton = new BoardButton("SYNC");
	private final BoardBox syncBox = new BoardBox( syncButton );

	private final BoardLabel syncInfo = new BoardLabel("Update", "1", "tomorrow");
	private final BoardBox syncInfoBox = new BoardBox( syncInfo );

	private final HerstellingGrid herstellingGrid;
	private final HerstellingGrid voorbereidingGrid;
	private final HerstellingGrid demontageGrid;
	private final HerstellingGrid inoxGrid;

	@Autowired
	public AtelierView(NavigationManager navigationManager,
					   HerstellingDataProvider herstellingDataProvider,
					   HerstellingGrid herstellingGrid,
					   HerstellingGrid voorbereidingGrid,
					   HerstellingGrid demontageGrid,
					   HerstellingGrid inoxGrid) {
		this.navigationManager = navigationManager;
		this.herstellingDataProvider = herstellingDataProvider;
		this.herstellingGrid = herstellingGrid;
		this.demontageGrid = demontageGrid;
		this.voorbereidingGrid = voorbereidingGrid;
		this.inoxGrid = inoxGrid;

		herstellingGrid.setHeight( "600px" );
		demontageGrid.setHeight( "600px" );
		voorbereidingGrid.setHeight( "600px" );
		inoxGrid.setHeight( "600px" );

		Label labelHerstellen = new Label( "Te Herstellen" );
		labelHerstellen.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelHerstellen);
		vLayoutGrid.addComponent( herstellingGrid );

		Label labelDemontage = new Label( "Demontage" );
		labelDemontage.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelDemontage );
		vLayoutGrid.addComponent( demontageGrid );

		Label labelVoorbereiding = new Label( "Voorbereidig" );
		labelVoorbereiding.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelVoorbereiding );
		vLayoutGrid.addComponent( voorbereidingGrid );

		Label labelInox = new Label( "Inox" );
		labelInox.setStyleName( "h2" );
		vLayoutGrid.addComponent( labelInox );
		vLayoutGrid.addComponent( inoxGrid );


	}

	@PostConstruct
	public void init() {
		setResponsive(true);

		Row rowStats = board.addRow(demontageBox, voorbereidingBox, herstellingBox,
				inoxBox);
		rowStats.addStyleName("board-row-group");

		Row rowUpdate = boardUpdate.addRow(syncBox, syncInfoBox);
		rowUpdate.addStyleName("board-row-group");

		herstellingGrid.setId("dueGrid");
		herstellingGrid.setSizeFull();
		herstellingGrid.setData( herstellingDataProvider.getAllDetailsToHerstel() );

		demontageGrid.setId("dueGrid");
		demontageGrid.setSizeFull();
		demontageGrid.setData( herstellingDataProvider.getAllDetailsToDemontage() );

		voorbereidingGrid.setId("dueGrid");
		voorbereidingGrid.setSizeFull();
		voorbereidingGrid.setData( herstellingDataProvider.getAllDetailsToVoorbereiding() );



		//herstellingGrid.addSelectionListener( e -> selectedOrder(e.getFirstSelectedItem().get()));
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
