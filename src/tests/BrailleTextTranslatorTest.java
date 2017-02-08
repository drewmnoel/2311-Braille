package tests;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import common.BrailleTextTranslator;

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
	@Before
	public void setUp() throws Exception {
		testString = "abcdefghijklmnopqrstuvwxyz";

	}

	@Test
	public void testTranslateString() {
		int[][]translateResult = BrailleTextTranslator.translate(testString);
		for(int i = 0; i<26; i++) {
			assertTrue(Arrays.equals(translateResult[i], expectedResult[i]));
		}

	}
}
