package authoring;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LeftPanel extends JPanel {
	JScrollPane scrollPane = new JScrollPane();

	public LeftPanel(GUI gui) {
		super(new GridLayout(1,1));
		JList<String> commandList = new JList<String>();
		commandList.setModel(new DefaultListModel<String>());
		DefaultListModel<String> listModel = (DefaultListModel<String>) commandList.getModel();
		listModel.addElement("Init Event");
		listModel.addElement("TTS Event");
		listModel.addElement("Button Event");
		listModel.addElement("Button Event");
		listModel.addElement("TTS Event");
		commandList.setModel(listModel);
		scrollPane.setViewportView(commandList);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Scenario"));
		add(scrollPane);
	}

	private DefaultListModel<String> getList() {
		JList<String> list = (JList<String>) scrollPane.getViewport().getView();
		return (DefaultListModel<String>) list.getModel();
	}

	private void setList(DefaultListModel<String> listModel) {
		JList<String> commandList = new JList<String>();
		commandList.setModel(listModel);
		scrollPane.setViewportView(commandList);
		this.remove(scrollPane);
		this.add(scrollPane);
	}

	private void swapElements(int a, int b) {
		DefaultListModel<String> list = getList();
		String strA = list.getElementAt(a);
		String strB = list.getElementAt(b);
		list.set(a, strB);
		list.set(b, strA);
	}

	public void addItem(String string) {
		DefaultListModel<String> list = getList();
		list.addElement(string);
		setList(list);
	}

	public void moveUp() {
		JList<String> commandList = (JList<String>) scrollPane.getViewport().getView();
		int selectedIndex = commandList.getSelectedIndex();

		// Do not move the top element "up"!
		if (selectedIndex == 0) {
			return;
		}

		// Swap the element with the one above it
		swapElements(selectedIndex, selectedIndex - 1);

		// Update the highlight position
		commandList.setSelectedIndex(selectedIndex-1);
	}

	public void moveDown() {
		JList<String> commandList = (JList<String>) scrollPane.getViewport().getView();
		int selectedIndex = commandList.getSelectedIndex();

		// Get the list
		DefaultListModel<String> listModel = getList();

		// Do not move the bottom down!
		if (selectedIndex == listModel.size() - 1) {
			return;
		}

		// Swap the element with the one below it
		swapElements(selectedIndex, selectedIndex + 1);

		// Update the highlight position
		commandList.setSelectedIndex(selectedIndex + 1);
	}

	public void deleteItem() {
		JList<String> commandList = (JList<String>) scrollPane.getViewport().getView();
		int selectedIndex = commandList.getSelectedIndex();

		DefaultListModel<String> list = getList();
		list.remove(selectedIndex);
		setList(list);
	}
}
