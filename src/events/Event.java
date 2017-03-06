package events;

import player.PlayerException;

/**
 * Abstract representation of an Event in the scenario. This class shall not be
 * instantiated directly since it is abstract. Rather, one of the following
 * should be created: AudioEvent, BrailleEvent, ButtonEvent, InitEvent,
 * JumpEvent, TTSEvent.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-05
 *
 */
abstract public class Event {
	private String details;

	/**
	 * Set the generic details of the event. The subclassed event types may
	 * process the input given as required.
	 *
	 * @param details
	 *            String containing the entire parameter list, separated by
	 *            spaces
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * Get the generic details of the event. The subclassed event types may have
	 * processed the value as appropriate, but the return value will always be
	 * of the same form (i.e. the value will still be a space-separated list of
	 * arguments)
	 *
	 * @return String containing the entire parameter list, separated by spaces
	 */
	public String getDetails() {
		return this.details;
	}

	/**
	 * Execute the event. This will perform the appropriate actions of the
	 * event. Depending on the subtype, this may include: playing an audio file,
	 * manipulating the GUI, reading a TTS line etc. The method will calculate
	 * the relative offset to jump to in the input file. For most subtypes, this
	 * will simply be a value of +1 to indicate that the next line should be
	 * executed next. For other types, the value will depend on the exact event
	 * parameters that were given. There is no guarantee that the offset will be
	 * sane (e.g. might ask to jump past the end of the file).
	 * Garbage-in-garbage-out.
	 *
	 * @return The relative offset in the input that should be seeked to
	 * @throws PlayerException
	 *             If any exception occurs in the event execution. The exception
	 *             will be due to a failure of detecting errors in the parsing
	 *             and are not the fault of the actual event. The exception will
	 *             be chained and contain a helpful message on the error.
	 */
	abstract public int execute() throws PlayerException;
}
