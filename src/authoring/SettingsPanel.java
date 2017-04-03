package authoring;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SettingsPanel extends JPanel {

	private GUI gui;
	private JTextField cellField;
	private JTextField buttonField;
	private JTextField titleField;

	public SettingsPanel(GUI gui) {
		setLayout(new GridBagLayout());

		JLabel cellLabel = new JLabel("Cells:", SwingConstants.RIGHT);
		JLabel buttonLabel = new JLabel("Buttons:", SwingConstants.RIGHT);
		JLabel titleLabel = new JLabel("Title:", SwingConstants.RIGHT);

		cellField = new JTextField(10);
		buttonField = new JTextField();
		titleField = new JTextField();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(cellLabel, gbc);

		gbc.gridy++;
		add(buttonLabel, gbc);

		gbc.gridy++;
		add(titleLabel, gbc);

		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx++;
		gbc.gridy = 0;
		add(cellField, gbc);

		gbc.gridy++;
		add(buttonField, gbc);

		gbc.gridy++;
		add(titleField, gbc);
	}

	public JTextField getCellField() {
		return cellField;
	}

	public void setCellField(JTextField cellField) {
		this.cellField = cellField;
	}

	public JTextField getButtonField() {
		return buttonField;
	}

	public void setButtonField(JTextField buttonField) {
		this.buttonField = buttonField;
	}

	public JTextField getTitleField() {
		return titleField;
	}

	public void setTitleField(JTextField titleField) {
		this.titleField = titleField;
	}

}
