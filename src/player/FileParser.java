package player;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	 * @throws IOException if fileTarget is an invalid file path
	 */
	public List<Event[]> parseFile() throws IOException {
		// TODO: Parse the file line-by-line. Each line represents an event.
		// Return a list of these events
		// that will need to be executed in order.
		
		
		return new ArrayList<>();

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
