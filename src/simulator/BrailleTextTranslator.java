package simulator;

public class BrailleTextTranslator {
	public static String translate(String input) {
		StringBuilder result = new StringBuilder();
		for (char c: input.toCharArray()) {
			System.out.print(BrailleTextSymbol.ht.get(new String(c + "")));
		}
		return result.toString();
	}
}
