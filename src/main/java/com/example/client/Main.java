package com.example.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.vaadin.polymer.elemental.*;
import com.vaadin.polymer.paper.element.*;

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
    HTMLElement content;
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
                // remove all child elements
                while (content.hasChildNodes()) {
                    content.removeChild(content.getFirstChild());
                }
            }
        });

        menuClearDone.addEventListener("click", new EventListener() {
            @Override
            public void handleEvent(Event event) {
                closeMenu();

                NodeList<Element> list = content.querySelectorAll("div.item");
                for (int i = list.getLength() - 1; i > -1; i--) {
                    Element child = list.item(i);
                    PaperCheckboxElement checkbox = child.querySelector("paper-checkbox");
                    if (checkbox.getChecked()) {
                        content.removeChild(child);
                    }
                }
            }
        });
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