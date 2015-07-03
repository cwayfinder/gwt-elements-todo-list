package com.vaadin.polymer.elemental;

import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;

@JsType
public interface Element extends Node {

    @JsProperty
    String getInnerHTML();

    @JsProperty
    String getTextContent();

    @JsProperty
    DOMTokenList getClassList();

    @JsProperty
    CSSStyleDeclaration getStyle();

    void setAttribute(String name, Object value);
    String getAttribute(String name);
    boolean hasAttribute(String name);
    void removeAttribute(String name);

    <T extends Element> T querySelector(String selector);
    <T extends Element> NodeList<T> querySelectorAll(String selector);
}
