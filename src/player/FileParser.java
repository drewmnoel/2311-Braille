package player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File parser to parse Braille scenario files
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
		List<Event> eventList = new ArrayList<Event>();
		String line;
		File inputFile = new File(fileTarget);
		FileReader inputReader = new FileReader(inputFile);
		BufferedReader bufferedInput = new BufferedReader(inputReader);
		while ((line = bufferedInput.readLine()) != null) {
			parseEventType(eventList, line, bufferedInput);
		}
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
		String eventType = getCommand(line);
		String eventArgs = getArgs(line);

		Event tempEvent = new Event();

		switch (eventType) {
		case "TTS":
			tempEvent.setTTS(eventArgs);
			break;
		case "AUDIO":
			tempEvent.setAudioPlay(eventArgs);
			break;
		case "BRAILLE":
			tempEvent.setBraille(eventArgs);
			break;
		case "BUTTON":
			tempEvent.setButton(eventArgs);
			break;
		case "JUMP":
			tempEvent.setJump(eventArgs);
			break;
		case "INIT":
			tempEvent.setInitializeSim(eventArgs);
			break;
		default:
			bufferedInput.close();
			throw new IOException("Invalid player command");
		}
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
