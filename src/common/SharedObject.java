package common;

/**
 * Generic integer shared object. Used to give multiple objects a shared integer
 * value. Exists only as a thin wrapper around the int type.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-05
 */
public class SharedObject {
	private int id;

	/**
	 * Set the internal integer id to some arbitrary value
	 *
	 * @param id
	 *            New value of the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtain the current internal integer id value
	 *
	 * @return The last value of the id
	 */
	public int getId() {
		return this.id;
	}
}
