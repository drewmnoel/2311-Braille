package player;

/**
 * Abstract representation of an event in the scenario.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-12
 *
 */
public class Event {
	private static final long TTS = 0;
	private static final long AUDIO_PLAY = 1;
	private long type;
	private String eventDetails;

	/**
	 * Create a blank event
	 */
	public Event() {
	}

	/**
	 * Set the event type to be TTS
	 *
	 * @param textToRead
	 *            Relevant text to be read aloud
	 */
	public void setTTS(String textToRead) {
		this.type = Event.TTS;
		this.eventDetails = textToRead;
	}

	/**
	 * Check if the event type is TTS
	 *
	 * @return True only if the event is TTS
	 */
	public boolean isTTS() {
		return this.type == Event.TTS;
	}

	/**
	 * Set the event type to be Audio Play
	 *
	 * @param filename
	 *            Path (relative or absolute) to the file to be played
	 */
	public void setAudioPlay(String filename) {
		this.type = Event.AUDIO_PLAY;
		this.eventDetails = filename;
	}

	/**
	 * Check if the event is an Audio Play
	 *
	 * @return True only if the event is an Audio Play
	 */
	public boolean isAudioPlay() {
		return this.type == Event.AUDIO_PLAY;
	}

	/**
	 * Get the generic details parameter
	 *
	 * @return Event details. Meaning depends on the event type.
	 */
	public String getEventDetails() {
		return this.eventDetails;
	}
}
