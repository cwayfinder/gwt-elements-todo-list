package com.example.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.vaadin.polymer.elemental.Event;
import com.vaadin.polymer.elemental.EventListener;
import com.vaadin.polymer.elemental.HTMLElement;
import com.vaadin.polymer.paper.element.*;
import com.vaadin.polymer.paper.widget.*;

public class Main extends Composite {
    interface MainUiBinder extends UiBinder<HTMLPanel, Main> {
    }

    private static MainUiBinder ourUiBinder = GWT.create(MainUiBinder.class);

    @UiField
    PaperDrawerPanelElement drawerPanel;

    @UiField
    PaperIconItemElement menuClearAll;
    @UiField
    PaperIconItemElement menuClearDone;

    @UiField
    DivElement content;
    @UiField
    PaperFabElement addButton;

    @UiField
    PaperDialogElement addItemDialog;
    @UiField
    PaperInputElement titleInput;
    @UiField
    PaperTextareaElement descriptionInput;
    @UiField
    PaperButtonElement confirmAddButton;

    public Main() {
        initWidget(ourUiBinder.createAndBindUi(this));

        addButton.addEventListener("click", new EventListener() {
            @Override
            public void handleEvent(Event event) {
                addItemDialog.open();
            }
        });

        confirmAddButton.addEventListener("click", new EventListener() {
            @Override
            public void handleEvent(Event event) {
                if (!titleInput.getValue().isEmpty()) {
                    addItem(titleInput.getValue(), descriptionInput.getValue());
                    // clear text fields
                    titleInput.setValue("");
                    descriptionInput.setValue("");
                }
            }
        });

        menuClearAll.addEventListener("click", new EventListener() {
            @Override
            public void handleEvent(Event event) {
                closeMenu();
                content.removeAllChildren();
            }
        });

        menuClearDone.addEventListener("click", new EventListener() {
            @Override
            public void handleEvent(Event event) {
                closeMenu();
                for (int i = content.getChildCount() - 1; i > -1; i--) {
                    Node child = content.getChild(i);
                    PaperCheckboxElement checkbox = findCheckbox((Element) child);

                    if (checkbox.getChecked()) {
                        content.removeChild(child);
                    }
                }
            }
        });
    }

    private PaperCheckboxElement findCheckbox(Element element) {
        Element div = DOM.getChild(element, 1);
        Element h4 = DOM.getChild(div, 0);
        return (PaperCheckboxElement) DOM.getChild(h4, 0);
    }

    private void addItem(String title, String description) {
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        content.appendChild(item.getElement());
    }

    private void closeMenu() {
        if (drawerPanel.getNarrow()) {
            drawerPanel.closeDrawer();
        }
    }
}