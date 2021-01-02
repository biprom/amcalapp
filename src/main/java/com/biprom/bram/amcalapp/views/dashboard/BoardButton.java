package com.biprom.bram.amcalapp.views.dashboard;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class BoardButton extends VerticalLayout {

	Label label;
	Button button;
	private String content;


	public BoardButton( String content) {
		addStyleName("board-box-label");
		setSizeFull();
		setContent(content);
	}

	public BoardButton(String content, String styleName) {
		this(content);
		addStyleName(styleName);
	}

	public void setContent(String content) {
		this.content = content;
		label = new Label( content );
		button = new Button( "SYNCHRONISEER MET KABBA" );
		button.addStyleName( "friendly" );
		button.addStyleName( "borderless" );

		button.setHeight( "100%" );

		this.addComponents( label,button );


	}

}
