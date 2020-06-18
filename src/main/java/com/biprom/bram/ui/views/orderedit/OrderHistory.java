package com.biprom.bram.ui.views.orderedit;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;
import org.vaadin.spring.events.EventBus.ViewEventBus;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.spring.annotation.SpringComponent;
import com.biprom.bram.app.security.SecurityUtils;
import com.biprom.bram.backend.data.entity.HistoryItem;
import com.biprom.bram.backend.data.entity.Order;
import com.biprom.bram.backend.service.HerstellingService;
import com.biprom.bram.backend.service.UserService;
import com.biprom.bram.ui.utils.DateTimeFormatter;
import com.vaadin.ui.Button.ClickShortcut;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Encapsulates the order history part of the order edit view.
 * <p>
 * Created as a single class because the logic is so simple that using a pattern
 * like MVP would add much overhead for little gain. If more complexity is added
 * to the class, you should consider splitting out a presenter.
 */
@SpringComponent
@PrototypeScope
public class OrderHistory extends OrderHistoryDesign {

	private final DateTimeFormatter dateTimeFormatter;

	private final ViewEventBus eventBus;

	private Order order;

	private final HerstellingService herstellingService;

	private final UserService userService;

	@Autowired
	public OrderHistory(DateTimeFormatter dateTimeFormatter, ViewEventBus eventBus, HerstellingService herstellingService,
                        UserService userService) {
		this.dateTimeFormatter = dateTimeFormatter;
		this.eventBus = eventBus;
		this.herstellingService = herstellingService;
		this.userService = userService;
	}

	@PostConstruct
	public void init() {
		// Uses binder to get bean validation for the message
		BeanValidationBinder<HistoryItem> binder = new BeanValidationBinder<>(HistoryItem.class);
		binder.setRequiredConfigurator(null); // Don't show a *
		binder.bind(newCommentInput, "message");
		commitNewComment.addClickListener(e -> {
			if (binder.isValid()) {
				addNewComment(newCommentInput.getValue());
			} else {
				newCommentInput.focus();
			}
		});

		// We don't want a global shortcut for enter, scope it to the panel
		addAction(new ClickShortcut(commitNewComment, KeyCode.ENTER, null));
	}

	public void addNewComment(String comment) {
		herstellingService.addHistoryItem(order, comment, SecurityUtils.getCurrentUser(userService));
		eventBus.publish(this, new OrderUpdatedEvent());
	}

	public void setOrder(Order order) {
		this.order = order;
		newCommentInput.setValue("");
		items.removeAllComponents();
		order.getHistory().forEach(historyItem -> {
			Label l = new Label(formatMessage(historyItem));
			l.addStyleName(ValoTheme.LABEL_SMALL);
			l.setCaption(formatTimestamp(historyItem) + " by " + historyItem.getCreatedBy().getName());
			l.setWidth("100%");
			items.addComponent(l);
		});
	}

	private String formatTimestamp(HistoryItem historyItem) {
		return dateTimeFormatter.format(historyItem.getTimestamp(), Locale.US);
	}

	private String formatMessage(HistoryItem historyItem) {
		return historyItem.getMessage();
	}

}
