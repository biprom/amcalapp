package com.biprom.bram.ui.subWindows;



import com.biprom.bram.ui.views.dashboard.CheckPauzeView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Window;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class CheckPauzeSubWindow extends Window {

    CheckPauzeView checkPauzeView;

    public CheckPauzeSubWindow(CheckPauzeView checkPauzeView) {
        this.checkPauzeView = checkPauzeView;

        checkPauzeView.setWindow(this);

        this.setClosable(false);

        setContent( checkPauzeView );

    }

    public CheckPauzeView getCheckPauzeView() {
        return checkPauzeView;
    }
}
