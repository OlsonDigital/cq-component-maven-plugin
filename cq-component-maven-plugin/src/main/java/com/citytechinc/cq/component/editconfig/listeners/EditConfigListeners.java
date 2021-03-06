package com.citytechinc.cq.component.editconfig.listeners;

import com.citytechinc.cq.component.xml.AbstractXmlElement;

import java.util.Map;

public class EditConfigListeners extends AbstractXmlElement {

    private final Map<String, String> listeners;

    public EditConfigListeners(EditConfigListenersParameters parameters) {
        super(parameters);
        listeners = parameters.getListeners();
    }

    public Map<String, String> getListeners() {
        return listeners;
    }

}
