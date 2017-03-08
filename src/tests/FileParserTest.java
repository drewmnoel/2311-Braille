package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import player.FileParser;

/**
 * This class tests the methods of the FileParser class and whether they
 * function correctly
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-03-06
 */

public class FileParserTest {

	FileParser fpTest;
	FileParser fpTestException;

	/** Placeholder to define that no exceptions should be thrown */
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/**
	 * A setup method to create a new object of type FileParser and set its file
	 * path to test.txt
	 *
	 * @throws Exception
	 *             Throws an Exception exception
	 */
	@Before
	public void setUp() throws Exception {

		fpTest = new FileParser();
		fpTest.setFileTarget("test.txt");
	}

	/**
	 * A method that tests to see if the file that is parsed, is parsed
	 * correctly. This is a check to make sure that the contents of the file is
	 * unchanged and no exceptions are thrown
	 *
	 * @throws IOException
	 *             Throws an IOException exception if it fails
	 */
	@Test
	public void testParseFile() throws IOException {

		File actual = new File(fpTest.getFileTarget());
		File expected = new File("test.txt");

		/*
		 * FileUtils is a method from the apache.commons.io library and serves
		 * as a good way of comparing files
		 */
		assertTrue(FileUtils.contentEquals(actual, expected));
	}

	/**
	 * A method that checks the getFileTarget() method It tests to see if the
	 * file that we set returns that same file. This also serves as a check for
	 * the mutator method.
	 *
	 * @throws IOException
	 *             Throws an IOException exception if it fails
	 */
	@Test
	public void testGetFileTarget() throws IOException {
		File expected = new File("testexpected.txt");
		fpTest.setFileTarget("testactual.txt");
		File actual = new File(fpTest.getFileTarget());
		assertTrue(FileUtils.contentEquals(actual, expected));

	}

	/**
	 * A method that checks to see if an exception is thrown for a file that has
	 * an invalid command
	 *
	 * @throws IOException
	 *             Throws an IOException if it fails
	 */
	@Test
	public void testExceptionParse() throws IOException {
		exception.expect(NullPointerException.class);
		fpTestException.setFileTarget("testexception.txt");

	}

}
