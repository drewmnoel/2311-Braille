package authoring;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import commands.PlayerCommand;

public class LeftPanel extends JPanel implements MouseListener {
	/** Autogenerated serial */
	private static final long serialVersionUID = 2716138356085893186L;

	private JScrollPane scrollPane = new JScrollPane();
	private JList<PlayerCommand> commandList = new JList<>();
	private DefaultListModel<PlayerCommand> listModel = new DefaultListModel<>();

	private GUI gui;

	/**
	 * Create a new left panel of the GUI.
	 *
	 * @param gui
	 *            Reference to the overall GUI object
	 */
	public LeftPanel(GUI gui) {
		// Create a basic JPanel with a grid layout
		super(new GridLayout(1, 1));

		// Set the JList to have listModel as the content
		commandList.setModel(listModel);

		// Set the scollpane to have commandList as its content
		scrollPane.setViewportView(commandList);

		commandList.addMouseListener(this);

		// Create a border around this panel
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Scenario"));

		// Add the scrollpane to this panel
		add(scrollPane);

		this.gui = gui;
	}

	/**
	 * Swap two string elements in the list, given their indices
	 *
	 * @param a
	 *            Parameter to swap
	 * @param b
	 *            Parameter to be swapped with
	 */
	private void swapElements(int a, int b) {
		// Get the element at each of the requested locations
		PlayerCommand strA = listModel.getElementAt(a);
		PlayerCommand strB = listModel.getElementAt(b);

		// Swap the elements and their indices
		listModel.set(a, strB);
		listModel.set(b, strA);
	}

	/**
	 * Add an element to the scrollpane in the left panel
	 *
	 * @param newElement
	 *            New element to be added
	 */
	public void addItem(PlayerCommand newElement) {
		listModel.addElement(newElement);
	}

	/**
	 * Move the currently selected element one spot higher in the list. If the
	 * selected element is already the top element, this method will gracefully
	 * do nothing.
	 */
	public void moveUp() {
		// Get the index of the selected element
		int selectedIndex = commandList.getSelectedIndex();

		// Do not move the top element "up"!
		if (selectedIndex == 0) {
			return;
		}

		// Swap the element with the one above it
		swapElements(selectedIndex, selectedIndex - 1);

		// Update the highlight position
		commandList.setSelectedIndex(selectedIndex - 1);
	}

	/**
	 * Move the currently selected element one spot lower in the list. If the
	 * selected element is already the bottom element, this method will
	 * gracefully do nothing.
	 */
	public void moveDown() {
		// Get the index of the selected element
		int selectedIndex = commandList.getSelectedIndex();

		// Do not move the bottom "down"!
		if (selectedIndex == listModel.size() - 1) {
			return;
		}

		// Swap the element with the one below it
		swapElements(selectedIndex, selectedIndex + 1);

		// Update the highlight position
		commandList.setSelectedIndex(selectedIndex + 1);
	}

	/**
	 * Remove the currently selected element from the list completely.
	 */
	public void deleteItem() {
		// Get the index of the selected element
		int selectedIndex = commandList.getSelectedIndex();

		// Remove that position from the listModel
		listModel.remove(selectedIndex);
	}
	
	public void clearItem() {
		listModel.removeAllElements();
	}
	
	public boolean elementCheck() {
		return listModel.isEmpty();
	}

	/**
	 * Re-set the statuses of each of the relevant buttons.
	 */
	public void recalculateButtonStatus() {
		// Get the index of the selected element
		int selectedIndex = commandList.getSelectedIndex();

		gui.getRightPanel().setUp(true);
		gui.getRightPanel().setDown(true);
		gui.getRightPanel().setDelete(true);

		if (selectedIndex == 0) {
			gui.getRightPanel().setUp(false);
		}
		if (selectedIndex == listModel.size() - 1) {
			gui.getRightPanel().setDown(false);
		}
		if (listModel.size() == 0 || selectedIndex == -1) {
			gui.getRightPanel().setUp(false);
			gui.getRightPanel().setDown(false);
			gui.getRightPanel().setDelete(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		recalculateButtonStatus();

		if (e.getClickCount() < 2) {
			return;
		}

		int index = commandList.locationToIndex(e.getPoint());
		PlayerCommand command = this.commandList.getModel().getElementAt(index);

		// Show the Add Item dialog
		Object answer;
		answer = JOptionPane.showInputDialog(gui, command.getEditLabel(), "Edit Item Details",
				JOptionPane.PLAIN_MESSAGE, null, null, command.getCurrentValue());

		if (answer == null) {
			return;
		}

		if (!((String) answer).isEmpty()) {
			command.setCurrentValue((String) answer);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	public List<PlayerCommand> getList() {
		List<PlayerCommand> result = new ArrayList<>();
		for (Object o : listModel.toArray()) {
			if (o instanceof PlayerCommand) {
				result.add((PlayerCommand) o);
			}
		}
		return result;
	}
}
