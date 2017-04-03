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
		
		//Sets default cells to 3 and buttons to 5
		cellField.setText("5");
		buttonField.setText("3");
		
		
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

	public String getCellField() {
		return cellField.getText();
	}

	public void setCellField(String fieldText) {
		this.cellField.setText(fieldText);
	}

	public String getButtonField() {
		return buttonField.getText();
	}

	public void setButtonFieldText(String fieldText) {
		this.buttonField.setText(fieldText);
	}

	public String getTitleField() {
		return titleField.getText();
	}

	public void setTitleField(String fieldText) {
		this.titleField.setText(fieldText);
	}

}
