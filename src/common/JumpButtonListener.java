package common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class implements a listener to detect when a button is clicked. When a
 * button is clicked, it sets the object reference to be the listener's ID. In
 * order to use, create a SharedObject and pass it to the constructor for each
 * of the listeners (one for each button) with a unique identifier. Sleep the
 * main thread and wait for the value of the shared object to change. The new
 * value of the shared object is the identifier of the listener which was fired.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-02
 */
public class JumpButtonListener implements ActionListener {
	private SharedObject shared;
	private int id;

	/**
	 * Create a new listener with a shared object reference and a unique
	 * listener ID
	 *
	 * @param shared
	 *            Reference to a SharedObject which is shared between all
	 *            listeners of the same type.
	 * @param id
	 *            A unique identifier which will allow for detecting which
	 *            listener fired
	 */
	public JumpButtonListener(SharedObject shared, int id) {
		this.shared = shared;
		this.id = id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		shared.setId(id);
	}

}
