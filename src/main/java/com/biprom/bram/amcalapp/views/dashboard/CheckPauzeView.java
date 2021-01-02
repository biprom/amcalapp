package com.biprom.bram.amcalapp.views.dashboard;

import com.biprom.bram.amcalapp.Designs.CheckPauzeDesign;
import com.biprom.bram.amcalapp.ui.subWindows.CheckPauzeSubWindow;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Window;

import java.time.LocalDateTime;

@SpringView
public class CheckPauzeView extends CheckPauzeDesign implements View {

    public LocalDateTime aangepasteEindTijd;
    public Window parentWindow;

    public CheckPauzeView() {

        setUpbPauzeGenomen();
        setUpbGeenPauzeGenomen();

    }

    private void setUpbGeenPauzeGenomen() {
        bGeenPauzeGenomen.addClickListener(x -> {
            aangepasteEindTijd = LocalDateTime.now();
            parentWindow.close();
        });
    }

    private void setUpbPauzeGenomen() {
        bPauzeGenomen.addClickListener(x -> {
            aangepasteEindTijd = LocalDateTime.now().minusMinutes(30);
            parentWindow.close();
        });
    }

    public void setLbTijdslot(String string){
        lbTijdslot.setValue(string);
    }

    public void setLbTotaalTijdSlot(String string){
        lbTotaalTijdSlot.setValue(string);
    }

    public LocalDateTime getAangepasteEindTijd() {
        return aangepasteEindTijd;
    }

    public void setWindow(CheckPauzeSubWindow window) {
        parentWindow = window;
    }

}
