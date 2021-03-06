package com.biprom.bram.amcalapp.views.orderedit;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;
import javax.validation.ValidationException;

import com.biprom.bram.amcalapp.HasLogger;
import com.biprom.bram.amcalapp.data.OrderState;
import com.biprom.bram.amcalapp.data.entity.Customer;
import com.biprom.bram.amcalapp.data.entity.Order;
import com.biprom.bram.amcalapp.data.entity.OrderItem;
import com.biprom.bram.amcalapp.service.HerstellingService;
import com.biprom.bram.amcalapp.views.storefront.StorefrontView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.vaadin.spring.events.EventBus.ViewEventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import com.vaadin.data.HasValue;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.biprom.bram.amcalapp.service.PickupLocationService;
import com.biprom.bram.amcalapp.service.UserService;
import com.biprom.bram.amcalapp.ui.navigation.NavigationManager;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SpringComponent
@ViewScope
public class OrderEditPresenter implements Serializable, HasLogger {

	private OrderEditView view;

	private final HerstellingService herstellingService;
	private final UserService userService;

	private final PickupLocationService pickupLocationService;

	private final NavigationManager navigationManager;

	private final ViewEventBus viewEventBus;

	private static final List<OrderState> happyPath = Arrays.asList(OrderState.NEW, OrderState.CONFIRMED,
			OrderState.READY, OrderState.DELIVERED);

	@Autowired
	public OrderEditPresenter(ViewEventBus viewEventBus, NavigationManager navigationManager, HerstellingService herstellingService,
							  UserService userService, PickupLocationService pickupLocationService) {
		this.viewEventBus = viewEventBus;
		this.navigationManager = navigationManager;
		this.herstellingService = herstellingService;
		this.userService = userService;
		this.pickupLocationService = pickupLocationService;
		viewEventBus.subscribe(this);
	}

	@PreDestroy
	public void destroy() {
		viewEventBus.unsubscribe(this);
	}

	@EventBusListenerMethod
	private void onProductInfoChange(ProductInfoChangeEvent event) {
		updateTotalSum();
		view.onProductInfoChanged();
	}

	@EventBusListenerMethod
	private void onOrderItemDelete(OrderItemDeletedEvent event) {
		removeOrderItem(event.getOrderItem());
		view.onProductInfoChanged();
	}

	@EventBusListenerMethod
	private void onOrderItemUpdate(OrderUpdatedEvent event) {
		refresh(view.getOrder().getId());
	}

	void init(OrderEditView view) {
		this.view = view;
	}

	/**
	 * Called when the user enters the view.
	 */
	public void enterView(Long id) {
		Order order;
		if (id == null) {
			// New
			order = new Order();
			order.setState(OrderState.NEW);
			order.setItems(new ArrayList<>());
			order.setCustomer(new Customer());
			order.setDueDate(LocalDate.now().plusDays(1));
			order.setDueTime(LocalTime.of(8, 00));
			order.setPickupLocation(pickupLocationService.getDefault());
		} else {
			//order = herstellingService.findOrder(id);

		}

		//refreshView(order);
	}

	private void updateTotalSum() {
		int sum = view.getOrder().getItems().stream().filter(item -> item.getProduct() != null)
				.collect(Collectors.summingInt(item -> item.getProduct().getPrice() * item.getQuantity()));
		view.setSum(sum);
	}

	public void editBackCancelPressed() {
		if (view.getMode() == OrderEditView.Mode.REPORT) {
			// Edit order
			view.setMode(OrderEditView.Mode.EDIT);
		} else if (view.getMode() == OrderEditView.Mode.CONFIRMATION) {
			// Back to edit
			view.setMode(OrderEditView.Mode.EDIT);
		} else if (view.getMode() == OrderEditView.Mode.EDIT) {
			// Cancel edit
			Long id = view.getOrder().getId();
			if (id == null) {
				navigationManager.navigateTo(StorefrontView.class);
			} else {
				enterView(id);
			}
		}
	}

	public void okPressed() {
		if (view.getMode() == OrderEditView.Mode.REPORT) {
			// Set next state
			Order order = view.getOrder();
			Optional<OrderState> nextState = getNextHappyPathState(order.getState());
			if (!nextState.isPresent()) {
				throw new IllegalStateException(
						"The next state button should never be enabled when the state does not follow the happy path");
			}
			//herstellingService.changeState(order, nextState.get(), SecurityUtils.getCurrentUser(userService));
			refresh(order.getId());
		} else if (view.getMode() == OrderEditView.Mode.CONFIRMATION) {
			Order order = saveOrder();
			if (order != null) {
				// Navigate to edit view so URL is updated correctly
				navigationManager.updateViewParameter("" + order.getId());
				enterView(order.getId());
			}
		} else if (view.getMode() == OrderEditView.Mode.EDIT) {
			Optional<HasValue<?>> firstErrorField = view.validate().findFirst();
			if (firstErrorField.isPresent()) {
				((Focusable) firstErrorField.get()).focus();
				return;
			}
			// New order should still show a confirmation page
			Order order = view.getOrder();
			if (order.getId() == null) {
				filterEmptyProducts();
				view.setMode(OrderEditView.Mode.CONFIRMATION);
			} else {
				order = saveOrder();
				if (order != null) {
					refresh(order.getId());
				}
			}
		}
	}

	private void refresh(Long id) {
//		Order order = herstellingService.findOrder(id);
//		if (order == null) {
//			view.showNotFound();
//			return;
//		}
		//refreshView(order);

	}

	private void refreshView(Order order) {
		view.setOrder(order);
		updateTotalSum();
		if (order.getId() == null) {
			view.setMode(OrderEditView.Mode.EDIT);
		} else {
			view.setMode(OrderEditView.Mode.REPORT);
		}
	}

	private void filterEmptyProducts() {
		LinkedList<OrderItem> emptyRows = new LinkedList<>();
		view.getOrder().getItems().forEach(orderItem -> {
			if (orderItem.getProduct() == null) {
				emptyRows.add(orderItem);
			}
		});
		emptyRows.forEach(this::removeOrderItem);
	}

	private Order saveOrder() {
		try {
			filterEmptyProducts();
			Order order = view.getOrder();
			return null;
		} catch (ValidationException e) {
			// Should not get here if validation is setup properly
			Notification.show("Please check the contents of the fields: " + e.getMessage(), Type.ERROR_MESSAGE);
			getLogger().error("Validation error during order save", e);
			return null;
		} catch (OptimisticLockingFailureException e) {
			// Somebody else probably edited the data at the same time
			Notification.show("Somebody else might have updated the data. Please refresh and try again.",
					Type.ERROR_MESSAGE);
			getLogger().debug("Optimistic locking error while saving order", e);
			return null;
		} catch (Exception e) {
			// Something went wrong, no idea what
			Notification.show("An unexpected error occurred while saving. Please refresh and try again.",
					Type.ERROR_MESSAGE);
			getLogger().error("Unable to save order", e);
			return null;
		}
	}

	public Optional<OrderState> getNextHappyPathState(OrderState current) {
		final int currentIndex = happyPath.indexOf(current);
		if (currentIndex == -1 || currentIndex == happyPath.size() - 1) {
			return Optional.empty();
		}
		return Optional.of(happyPath.get(currentIndex + 1));
	}

	private void removeOrderItem(OrderItem orderItem) {
		view.removeOrderItem(orderItem);
		updateTotalSum();
	}
}
