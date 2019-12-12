package org.vaadin.liferay.example.ui.widgetset.server;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.AbstractTextField;
import org.vaadin.liferay.example.ui.widgetset.client.stripcharsre.StripCharsReState;

public class StripCharsRe extends AbstractExtension {

	private static final long serialVersionUID = 3272897487575591033L;

	public static StripCharsRe init(final AbstractTextField field) {
		final StripCharsRe cs = new StripCharsRe();
		cs.extend(field);
		return cs;
	}

	private StripCharsRe() {
		super();
	}

	@Override
	public StripCharsReState getState() {
		return (StripCharsReState) super.getState();
	}

	public void setRegExp(final String regexp) {
		this.getState().regexp = regexp;
	}
}