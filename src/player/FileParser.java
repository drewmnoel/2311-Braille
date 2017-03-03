package player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
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
	 */
	public FileParser(String fileTarget) {
		this();
		setFileTarget(fileTarget);
	}

	/**
	 *
	 * @return
	 * @throws IOException
	 *             if fileTarget is an invalid file path or if command in file
	 *             is invalid
	 */
	public List<Event> parseFile() throws IOException {
		// TODO: Parse the file line-by-line. Each line represents an event.
		// Return a list of these events
		// that will need to be executed in order.
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
	 * Parses whether a line in the text file is for an audio or TTS event
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

	private String getCommand(String input) {
		return input.split(" ", 2)[0];
	}

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
