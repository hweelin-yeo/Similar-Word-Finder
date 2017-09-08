import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import LDDistance.MisSpell;

/** Class Description: Weights contains methods that calculate the cost for
 *  misspells, specific to each tuple.  */

public class Weights {
	
	static Map<String, String> dictionary = 
			new HashMap<String, String>();                           // dictionary of correct and misspelled words
	static Map< Tuple<Character, Character>, Integer> mispellByDeletion = 
			new HashMap< Tuple <Character, Character> , Integer>();  // dictionary of tuples involved in misspellings by deletion
	static Map< Tuple<Character, Character>, Integer> mispellByInsertion = 
			new HashMap< Tuple <Character, Character> , Integer>(); // dictionary of tuples involved in misspellings by insertion
	static Map< Tuple<Character, Character>, Integer> mispellBySubstitution = 
			new HashMap< Tuple <Character, Character> , Integer>(); // dictionary of tuples involved in misspellings by substitution
	
	public static void main(String[] args) throws FileNotFoundException {
		processMispelledWordsFromWordstxt();
		tabulateMisspells();
	}
	
	/** Returns: A double, which represents the cost of a certain misspell 
	 * for a tuple of characters. 
	 *Precondition: tuple is a tuple, mis is a MisSpell type. */
	
	public static double weighMisspells(Tuple<Character, Character> tuple, MisSpell mis) {
		// TODO: Calculate how frequent tuple <char, char> in its dictionary of 
		// misspellByDeletion, and get a fraction based on that. Hence the more 
		// frequent it is misspelled, the smaller the double obtained. 
		return 0.0;
	}
	
	
					/******************************************* 
					 * 				Helper Functions 
					 * ****************************************/

	/** Process words from Words.txt */
	private static void processMispelledWordsFromWordstxt() throws FileNotFoundException {
		System.out.println(System.getProperty("user.dir"));
		File file = new File("Words.txt");
		Scanner scanner = new Scanner(file);

		while (scanner.hasNext()) {
			String incorrectWord = scanner.next();
			String correctWord = scanner.next();
			dictionary.put(incorrectWord, correctWord);
		}

		scanner.close();
	}

	/** Tabulate misspells from dictionary of correct and incorrect words */
	private static void tabulateMisspells() {
		for (Entry<String, String> entry : dictionary.entrySet()) {
			String correct = entry.getKey();
			String incorrect = entry.getValue();
			LDDistance.normalLDForDataCollection(incorrect, correct);
		}
	}
	
	/** Credits to: 
	 * https://stackoverflow.com/questions/2670982/using-pairs-or-2-tuples-in-java */
	/** Class Description: An instance of a tuple is in the form of <x,y> */
	private class Tuple<X, Y> { 
		public final X x; 
		public final Y y; 
		public Tuple(X x, Y y) { 
			this.x = x; 
			this.y = y; 
		} 
	}
}