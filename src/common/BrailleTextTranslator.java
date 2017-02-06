package common;

/**
 * Utility class to convert between ASCII text and Braille cells
 *
 */
public class BrailleTextTranslator {

	/**
	 * Convert string input to array of Braille cells.
	 * Only supports translation of lower case alphabet characters.
	 * For all other characters use the setCell() method in the SimulatorCore class to set
	 * the cell pins directly. 
	 * 
	 * @param input String of text to convert into Braille cells. Must only contain lower case letters.  
	 * @return Braille result stored in an array of cells
	 */
	
	public static int[][] translate(String input) {
		int[][] result = new int[input.length()][];
		for (int i = 0; i < input.length(); i++) {
			String current = input.substring(i, i+1).toLowerCase();

			result[i] = BrailleTextSymbol.ht.get(current);
		}
		return result;
	}
}