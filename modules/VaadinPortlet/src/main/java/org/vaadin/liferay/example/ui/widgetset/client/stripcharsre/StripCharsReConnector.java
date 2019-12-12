package org.vaadin.liferay.example.ui.widgetset.client.stripcharsre;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;
import com.vaadin.client.ui.VTextField;
import org.vaadin.liferay.example.ui.widgetset.server.StripCharsRe;

@Connect(StripCharsRe.class)
public class StripCharsReConnector extends AbstractExtensionConnector implements KeyPressHandler {

	private static final long serialVersionUID = -7720221107791070067L;

	private VTextField field;

	@Override
	public StripCharsReState getState() {
		return (StripCharsReState) super.getState();
	}

	private VTextField getField() {
		return this.field;
	}

	private void setField(final VTextField field) {
		this.field = field;
	}

	@Override
	protected void extend(final ServerConnector target) {
		final VTextField field = (VTextField) ((ComponentConnector) target).getWidget();
		this.setField(field);

		if (field != null) {
			field.addKeyPressHandler(this);
		}
	}

	@Override
	public void onKeyPress(final KeyPressEvent event) {
		final VTextField field = this.getField();

		final int keyCode = event.getNativeEvent().getKeyCode();
		if (!this.isIrrelevantKey(keyCode) && !field.isReadOnly() && field.isEnabled()) {
			final char charCode = event.getCharCode();
			final String newValue = StripCharsReConnector.this.getNewFieldValue(charCode);

			if (this.getState().regexp != null && newValue.matches(this.getState().regexp)) {
				field.cancelKey();
			}
		}
	}

	private boolean isIrrelevantKey(final int keyCode) {
		boolean irrelevantKey = false;
		switch (keyCode) {
		case KeyCodes.KEY_LEFT:
		case KeyCodes.KEY_RIGHT:
		case KeyCodes.KEY_BACKSPACE:
		case KeyCodes.KEY_DELETE:
		case KeyCodes.KEY_TAB:
		case KeyCodes.KEY_UP:
		case KeyCodes.KEY_DOWN:
		case KeyCodes.KEY_SHIFT:
			irrelevantKey = true;
		}
		return irrelevantKey;
	}

	private String getNewFieldValue(final char charCode) {
		final VTextField field = this.getField();
		final int index = field.getCursorPos();
		final String previousText = field.getText();
		final StringBuilder buffer = new StringBuilder();
		buffer.append(previousText.substring(0, index));
		buffer.append(charCode);
		if (field.getSelectionLength() > 0) {
			buffer.append(previousText.substring(index + field.getSelectionLength(), previousText.length()));
		} else {
			buffer.append(previousText.substring(index, previousText.length()));
		}
		return buffer.toString();
	}

}