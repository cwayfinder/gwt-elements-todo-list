package com.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;
import com.vaadin.polymer.paper.element.*;
import com.vaadin.polymer.paper.widget.PaperIconItem;
import com.vaadin.polymer.paper.widget.PaperToolbar;

import java.util.Arrays;

public class TodoList implements EntryPoint {

    public void onModuleLoad() {
        Polymer.importHref("paper-styles/demo-pages.html");

        // We have to load icon sets before run application
        Polymer.importHref(Arrays.asList("iron-icons/iron-icons.html",
                PaperDrawerPanelElement.SRC,
                PaperHeaderPanelElement.SRC,
                PaperToolbarElement.SRC,
                PaperIconItemElement.SRC,
                PaperRippleElement.SRC,
                PaperIconButtonElement.SRC,
                PaperFabElement.SRC,
                PaperDialogElement.SRC,
                PaperInputElement.SRC,
                PaperTextareaElement.SRC,
                PaperCheckboxElement.SRC,
                PaperButtonElement.SRC), new Function() {
            public Object call(Object arg) {
                // The app is executed when all imports succeed.
                startApplication();
                return null;
            }
        });
    }

    private void startApplication() {
        RootPanel.get().add(new Main());
    }
}
