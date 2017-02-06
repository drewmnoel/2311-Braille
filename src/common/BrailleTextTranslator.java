package common;

/**
 * Utility class to convert between ASCII text and Braille cells
 *
 */
public class BrailleTextTranslator {

	/**
	 * Convert string input to array of Braille cells.
	 * Only supports translation of lower case alphabet characters and numbers 0 to 9.
	 * For all other characters use the setCell() method in the SimulatorCore class to set
	 * the cell pins directly. 
	 * 
	 * @param input String of text to convert into Braille cells. Must only contain lower case letters or numbers 0-9.  
	 * @return Braille result stored in an array of cells
	 */
	
	public static int[][] translate(String input) {
		int[][] result = new int[input.length()][];
		int i = 0;
		for (char c : input.toCharArray()) {
			String strC = new String(c + "");
			if (strC.equals(strC.toUpperCase())) {
				// TODO: What if there's capitals?
			}
			result[i++] = BrailleTextSymbol.ht.get(strC);
		}
		return result;
	}
}