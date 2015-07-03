package com.example.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.vaadin.polymer.elemental.Event;
import com.vaadin.polymer.elemental.EventListener;
import com.vaadin.polymer.paper.element.PaperCheckboxElement;

public class Item {

    private final DivElement element;

    interface ItemUiBinder extends UiBinder<DivElement, Item> {
    }

    private static ItemUiBinder ourUiBinder = GWT.create(ItemUiBinder.class);
    
    @UiField
    Element title;
    @UiField
    Element description;
    @UiField
    PaperCheckboxElement done;

    public Item() {
        element = ourUiBinder.createAndBindUi(this);

        done.addEventListener("iron-change", new EventListener() {
            @Override
            public void handleEvent(Event event) {
                if (done.getActive()) {
                    title.addClassName("done");
                } else {
                    title.removeClassName("done");
                }
            }
        });
    }

    public DivElement getElement() {
        return element;
    }
}
