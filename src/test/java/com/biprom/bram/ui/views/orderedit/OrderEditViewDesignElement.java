package com.biprom.bram.ui.views.orderedit;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.CssLayoutElement;
import com.vaadin.testbench.elements.DateFieldElement;
import com.vaadin.testbench.elements.HorizontalLayoutElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.VerticalLayoutElement;
import com.vaadin.testbench.elementsbase.ServerClass;

@ServerClass("com.biprom.bram.ui.views.orderedit.OrderEditViewDesign")
@AutoGenerated
public class OrderEditViewDesignElement extends VerticalLayoutElement {

	public HorizontalLayoutElement getReportHeader() {
		return $(com.vaadin.testbench.elements.HorizontalLayoutElement.class).id("reportHeader");
	}

	public LabelElement getOrderId() {
		return $(com.vaadin.testbench.elements.LabelElement.class).id("orderId");
	}

	public LabelElement getStateLabel() {
		return $(com.vaadin.testbench.elements.LabelElement.class).id("stateLabel");
	}

	public ComboBoxElement getState() {
		return $(com.vaadin.testbench.elements.ComboBoxElement.class).id("state");
	}

	public DateFieldElement getDueDate() {
		return $(DateFieldElement.class).id("dueDate");
	}

	public ComboBoxElement getDueTime() {
		return $(ComboBoxElement.class).id("dueTime");
	}

	public ComboBoxElement getPickupLocation() {
		return $(ComboBoxElement.class).id("pickupLocation");
	}

	public TextFieldElement getFullName() {
		return $(com.vaadin.testbench.elements.TextFieldElement.class).id("fullName");
	}

	public TextFieldElement getPhone() {
		return $(com.vaadin.testbench.elements.TextFieldElement.class).id("phone");
	}

	public TextFieldElement getDetails() {
		return $(com.vaadin.testbench.elements.TextFieldElement.class).id("details");
	}

	public CssLayoutElement getProductInfoContainer() {
		return $(com.vaadin.testbench.elements.CssLayoutElement.class).id("productInfoContainer");
	}

	public ButtonElement getAddItems() {
		return $(com.vaadin.testbench.elements.ButtonElement.class).id("addItems");
	}

	public LabelElement getTotal() {
		return $(com.vaadin.testbench.elements.LabelElement.class).id("total");
	}

	public OrderHistoryElement getHistory() {
		return $(com.biprom.bram.ui.views.orderedit.OrderHistoryElement.class).id("history");
	}

	public ButtonElement getEditOrCancel() {
		return $(com.vaadin.testbench.elements.ButtonElement.class).id("cancel");
	}

	public ButtonElement getOk() {
		return $(com.vaadin.testbench.elements.ButtonElement.class).id("ok");
	}
}