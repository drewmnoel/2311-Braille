package authoring;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import commands.GoHereCommand;
import commands.PlayerCommand;
import commands.SkipButtonCommand;
import commands.SkipCommand;

class ColourCellRenderer extends JLabel implements ListCellRenderer<Object> {
	private static final long serialVersionUID = -3271692534445955654L;
	private ColourMapper mapper;

	public ColourCellRenderer(ColourMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		setText(value.toString());

		if (!(value instanceof PlayerCommand)) {
			return this;
		}

		PlayerCommand pc = (PlayerCommand) value;

		String locationTag = "";
		if (pc instanceof SkipCommand || pc instanceof GoHereCommand) {
			locationTag = pc.getCurrentValue();

		}
		// If the command is of type SkipButton, that has a button and location
		// tag in its description
		if (pc instanceof SkipButtonCommand) {
			locationTag = pc.getCurrentValue().split(" ", 2)[1];

		}

		setForeground(mapper.getColour(locationTag));
		return this;
	}
}