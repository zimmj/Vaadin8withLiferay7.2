package org.vaadin.liferay.example;

import org.osgi.service.component.annotations.Component;

import com.vaadin.osgi.resources.OsgiVaadinWidgetset;

@Component
public class AppWidgetSet implements OsgiVaadinWidgetset {

    public static final String NAME = "org.vaadin.liferay.example.ui.widgetset.CustomeWidgetSet";

    @Override
    public String getName() {
        return NAME;
    }

}
