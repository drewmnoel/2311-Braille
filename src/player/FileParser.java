package player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import events.AudioEvent;
import events.BrailleEvent;
import events.ButtonEvent;
import events.Event;
import events.InitEvent;
import events.JumpEvent;
import events.TTSEvent;

/**
 * File parser to parse Braille scenario files The constructor initializes the
 * class and sets the file to be read. Using parseFile and parseEventType each
 * line is read and broken down into events and assigned corresponding event
 * details to them.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-02-12
 */
public class FileParser {
	private String fileTarget;

	/**
	 * Create a file parser with default settings
	 */
	public FileParser() {
	}

	/**
	 * Create a file parser which also sets the file to parse
	 *
	 * @param fileTarget
	 *            Path to the file which should be read and parsed
	 */
	public FileParser(String fileTarget) {
		this();
		setFileTarget(fileTarget);
	}

	/**
	 * Parse the file line-by-line. Each line represents an input event.
	 *
	 * @return List of events read from the file.
	 * @throws IOException
	 *             if fileTarget is an invalid file path or if command in file
	 *             is invalid
	 */
	public List<Event> parseFile() throws IOException {
		// Setup eventList to store events information
		List<Event> eventList = new ArrayList<>();
		String line;
		// initialize file reading
		File inputFile = new File(fileTarget);
		FileReader inputReader = new FileReader(inputFile);
		BufferedReader bufferedInput = new BufferedReader(inputReader);
		// Read until all text has been read and assign event details to
		// eventList
		// based on event invoked
		while ((line = bufferedInput.readLine()) != null) {
			parseEventType(eventList, line, bufferedInput);
		}
		// Close stream and return eventList
		inputReader.close();
		bufferedInput.close();
		return eventList;
	}

	/**
	 * Parses a given line for the relevant event type
	 *
	 * @param eventList
	 * @param line
	 * @param bufferedInput
	 * @throws IOException
	 */

	private void parseEventType(List<Event> eventList, String line, BufferedReader bufferedInput) throws IOException {
		// Determine which event was invoked
		String eventType = getCommand(line);
		// Get event information
		String eventArgs = getArgs(line);

		Event tempEvent;
		// Start an event based on which one has been invoked in test file
		switch (eventType) {
		case "TTS":
			tempEvent = new TTSEvent();
			break;
		case "AUDIO":
			tempEvent = new AudioEvent();
			break;
		case "BRAILLE":
			tempEvent = new BrailleEvent();
			break;
		case "BUTTON":
			tempEvent = new ButtonEvent();
			break;
		case "JUMP":
			tempEvent = new JumpEvent();
			break;
		case "INIT":
			tempEvent = new InitEvent();
			break;
		default:
			bufferedInput.close();
			throw new IOException("Invalid player command");
		}
		tempEvent.setDetails(eventArgs);
		eventList.add(tempEvent);
	}

	/**
	 * Helper method to extract the command part of a line
	 *
	 * @param input
	 *            Full line to parse
	 * @return The command component of the line
	 */
	private String getCommand(String input) {
		return input.split(" ", 2)[0];
	}

	/**
	 * Helper method to extract the args part of a line
	 *
	 * @param input
	 *            Full line to parse
	 * @return The args component of the line
	 */
	private String getArgs(String input) {
		return input.split(" ", 2)[1];
	}

	/**
	 * Retrieve file target
	 *
	 * @return Path to file
	 */
	public String getFileTarget() {
		return fileTarget;
	}

	/**
	 * Set the file target for processing
	 *
	 * @param fileTarget
	 *            Path to file to use. Accepts absolute and relative paths.
	 */
	public void setFileTarget(String fileTarget) {
		this.fileTarget = fileTarget;
	}
}
