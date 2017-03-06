package player;

/**
 * Abstract representation of an event in the scenario. Events are created by
 * FileParser parsing an input file
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-12
 *
 */
public class Event {
	/** TTS Event */
	private static final long TTS = 0;
	/** Audio Play Event */
	private static final long AUDIO_PLAY = 1;
	/** Braille Display Update Event */
	private static final long BRAILLE = 2;
	/** Button Press with Jumps Event */
	private static final long BUTTON = 3;
	/** Unconditional Jump Event */
	private static final long JUMP = 4;
	/** Simulator Initialize Event */
	private static final long INITIALIZE_SIM = 5;

	private long type;
	private String eventDetails;

	/**
	 * Create a blank event
	 */
	public Event() {
	}

	/**
	 * Set the event type to be Initialize Sim
	 *
	 * @param cellsAndButtons
	 *            String containing number of cells whitespace number of buttons
	 */
	public void setInitializeSim(String cellsAndButtons) {
		this.type = Event.INITIALIZE_SIM;
		this.eventDetails = cellsAndButtons;
	}

	/**
	 * Check if the event type is Initialize Sim
	 *
	 * @return True only if the event is Initialize Sim
	 */
	public boolean isInitializeSim() {
		return this.type == Event.INITIALIZE_SIM;
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
	 * Set the event to be Set Braille
	 *
	 * @param wordToSet
	 *            String to represent on braille cells
	 */
	public void setBraille(String wordToSet) {
		this.type = Event.BRAILLE;
		//if string has no caps set it as is
		if(!hasCaps(wordToSet)) {
			this.eventDetails = wordToSet;
		}
		//if string is all caps set two cap cell flags at front ('@')
		else if (wordToSet.toUpperCase().equals(wordToSet)) {
			this.eventDetails = "@@" + wordToSet;
		}
		//if string has some caps but is not all caps, set cap flags before very capital letter
		else {
			this.eventDetails = addCapsCells(wordToSet);
		}

		//if string has numbers, add flags before the first digit of the digit group
		if (hasDigits(wordToSet)) {
			this.eventDetails = addDigitCells(wordToSet);
		}

	}

	/**
	 * Create a string with sentinel character markers added for Braille numeric translation
	 *
	 * @param message Standard message for processing
	 *
	 * @return Modified message with sentinel markers added before each numeric group
	 */
	public String addDigitCells(String message) {
		StringBuffer modified = new StringBuffer();
		char ch, lastCh;

		for(int i = 0; i < message.length(); i++) {
			ch = message.charAt(i);
			if (i > 0) {
				lastCh = message.charAt(i-1);
			} else {
				// To satisfy that lastCh is init'd before the below check
				lastCh = 'X';
			}

			// Only add the flag to the beginning of each group
			if ((i == 0 || !Character.isDigit(lastCh)) && Character.isDigit(ch)) {
				modified.append('#');
				modified.append(ch);
			}
			else {
				modified.append(ch);
			}
		}

		return modified.toString();
	}

	/**
	 * Check if the input string contains any digits (e.g. "503", "1", and "3232309").
	 *
	 * @param message Any input string which should be checked
	 * @return True if and only if there is at least one digit
	 */
	public boolean hasDigits(String message) {
		boolean digitFlag = false;
		char ch;
		for (int i = 0; i < message.length(); i++) {
			ch = message.charAt(i);
			if (Character.isDigit(ch)) {
				digitFlag = true;
			}
		}
		return digitFlag;
	}



	/**
	 * Check if the event is a Set Braille
	 *
	 * @return True only if event is a Set Braille
	 */
	public boolean isSetBraille() {
		
		return this.type == Event.BRAILLE;
	}

	/**
	 * Set the event to be a button push
	 *
	 * @param buttonJumpArgs
	 *            Button jump args, relative jump offsets separated by spaces
	 */
	public void setButton(String buttonJumpArgs) {
		this.type = Event.BUTTON;
		this.eventDetails = buttonJumpArgs;
	}

	/**
	 * Check if the event is a button push
	 *
	 * @return True only if event is a button
	 */
	public boolean isButton() {
		return this.type == Event.BUTTON;
	}

	/**
	 * Set the event to be an unconditional jump
	 *
	 * @param jumpOffset
	 *            Relative jump offset to seek to
	 */
	public void setJump(String jumpOffset) {
		this.type = Event.JUMP;
		this.eventDetails = jumpOffset;
	}

	/**
	 * Check if the event is an unconditional jump
	 *
	 * @return True only if event is a button
	 */
	public boolean isJump() {
		return this.type == Event.JUMP;
	}

	/**
	 * Returns a long representing the type of this event
	 *
	 * @return Event type code
	 */
	public long eventType() {
		return this.type;
	}

	/**
	 * Get the generic details parameter
	 *
	 * @return Event details. Meaning depends on the event type.
	 */
	public String getEventDetails() {
		return this.eventDetails;
	}
	
	//set to private after testing
	/**
	 * Checks if a string contains capital letters
	 * 
	 * @param message string to be checked
	 * @return True if there are capitals, false otherwise
	 */
	public boolean hasCaps(String message) {
		boolean capFlag = false;
		char ch;
		for (int i = 0; i < message.length(); i++) {
			ch = message.charAt(i);
			if (Character.toUpperCase(ch) == ch && Character.isLetter(ch)) {
				capFlag = true;
			}
		}
		return capFlag;
	}
	
	//set to private after testing
	/**
	 * Adds 'capital letter flags '@'' infront of ever capital letter in a string
	 * @ is alt-175 
	 * 
	 * @param message string to have flags added
	 * @return modified string
	 */
	public String addCapsCells(String message) {
		StringBuffer modified = new StringBuffer();
		char ch;
		for(int i = 0; i < message.length(); i++) {
			ch = message.charAt(i);
			if(Character.toUpperCase(ch) == ch && Character.isLetter(ch)) {
				modified.append('@');
				modified.append(Character.toLowerCase(ch));
			}
			else {
				modified.append(ch);
			}
		}
		return modified.toString();
	}
}
