package org.vaadin.liferay.example;

import org.osgi.service.component.annotations.Component;

import com.vaadin.osgi.resources.OsgiVaadinTheme;
import com.vaadin.ui.themes.ValoTheme;

@Component
public class AppTheme extends ValoTheme implements OsgiVaadinTheme {
    public static final String THEME_NAME = "myTheme";

    @Override
    public String getName() {
        return THEME_NAME;
    }

}
