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

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {
		return this.details;
	}

	abstract public int execute() throws PlayerException;
}
