package com.biprom.bram.ui.components;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import com.biprom.bram.backend.data.entity.Order;
import com.biprom.bram.backend.service.HerstellingService;

@SpringComponent
@PrototypeScope
public class HerstellingDataProvider extends FilterablePageableDataProvider<Order, Object> {

	private final HerstellingService herstellingService;
	private LocalDate filterDate = LocalDate.now().minusDays(1);

	@Autowired
	public HerstellingDataProvider(HerstellingService herstellingService) {
		this.herstellingService = herstellingService;
	}

	@Override
	protected Page<Order> fetchFromBackEnd(Query<Order, Object> query, Pageable pageable) {
		return herstellingService.findAnyMatchingAfterDueDate(getOptionalFilter(), getOptionalFilterDate(), pageable);
	}

	private Optional<LocalDate> getOptionalFilterDate() {
		if (filterDate == null) {
			return Optional.empty();
		} else {
			return Optional.of(filterDate);
		}
	}

	public void setIncludePast(boolean includePast) {
		if (includePast) {
			filterDate = null;
		} else {
			filterDate = LocalDate.now().minusDays(1);
		}
	}

	@Override
	protected int sizeInBackEnd(Query<Order, Object> query) {
		return (int) herstellingService.countAnyMatchingAfterDueDate(getOptionalFilter(), getOptionalFilterDate());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("dueDate", SortDirection.ASCENDING));
		sortOrders.add(new QuerySortOrder("dueTime", SortDirection.ASCENDING));
		// id included only to always get a stable sort order
		sortOrders.add(new QuerySortOrder("id", SortDirection.DESCENDING));
		return sortOrders;
	}
}
