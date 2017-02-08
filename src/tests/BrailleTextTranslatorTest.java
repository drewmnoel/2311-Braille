package tests;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import common.BrailleTextTranslator;

/**
 * JUnit tests for the Braille Translator utility class
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-01-31
 *
 */
public class BrailleTextTranslatorTest {
	String testString;
	int[][] expectedResult = {
			{1, 0, 0, 0, 0, 0, 0, 0},
			{1, 1, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 1, 0, 0, 0},
			{1, 0, 0, 0, 1, 1, 0, 0},
			{1, 0, 0, 0, 0, 1, 0, 0},
			{1, 1, 0, 0, 1, 0, 0, 0},
			{1, 1, 0, 0, 1, 1, 0, 0},
			{1, 1, 0, 0, 0, 1, 0, 0},
			{0, 1, 0, 0, 1, 0, 0, 0},
			{0, 1, 0, 0, 1, 1, 0, 0},
			{1, 0, 1, 0, 0, 0, 0, 0},
			{1, 1, 1, 0, 0, 0, 0, 0},
			{1, 0, 1, 0, 1, 0, 0, 0},
			{1, 0, 1, 0, 1, 1, 0, 0},
			{1, 0, 1, 0, 0, 1, 0, 0},
			{1, 1, 1, 0, 1, 0, 0, 0},
			{1, 1, 1, 0, 1, 1, 0, 0},
			{1, 1, 1, 0, 0, 1, 0, 0},
			{0, 1, 1, 0, 1, 0, 0, 0},
			{0, 1, 1, 0, 1, 1, 0, 0},
			{1, 0, 1, 0, 0, 0, 1, 0},
			{1, 1, 1, 0, 0, 0, 1, 0},
			{0, 1, 1, 0, 1, 1, 1, 0},
			{1, 0, 1, 0, 1, 0, 1, 0},
			{1, 0, 1, 0, 1, 1, 1, 0},
			{1, 0, 1, 0, 0, 1, 1, 0}
	};
	/**
	 * Set the testing string for later use
	 *
	 * @throws Exception Should never happen
	 */
	@Before
	public void setUp() throws Exception {
		testString = "abcdefghijklmnopqrstuvwxyz";
	}

	/**
	 * Test that the translator translates ASCII to Braille cell correctly for each letter.
	 */
	@Test
	public void testTranslateString() {
		int[][]translateResult = BrailleTextTranslator.translate(testString);
		for(int i = 0; i<26; i++) {
			assertTrue(Arrays.equals(translateResult[i], expectedResult[i]));
		}

	}
}
