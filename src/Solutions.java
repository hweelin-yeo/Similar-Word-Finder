import java.util.Scanner;

/** Class Description: Class Solutions's method main runs the program to find
 * the most similar word */

public class Solutions {
	
	/** Returns: A string in corrections that is the most similar to inc 
	 * Precondition: inc is a string, corrections is a string [] */
	
	static String findSimilarity(String inc, String[] corrections) {
		String mostLikelyWord = corrections[0];
		double weightedLDScore = Integer.MAX_VALUE;

		for (int index = 0; index < corrections.length; index++) {
			String correction = corrections[index];
			double weightedLDCorrection = LDDistance.weightedLD(inc, correction);
			if (weightedLDCorrection < weightedLDScore) {
				mostLikelyWord = correction;
				weightedLDScore = weightedLDCorrection;
			}
		}
		
		System.out.println(mostLikelyWord);
		return mostLikelyWord;
	}

	public static void main(String[] args) {
		// testing(); 
		Scanner in = new Scanner(System.in);
		String incorrect_word = in.next();
		int m = in.nextInt();
		String[] correct_words = new String[m];
		for(int correct_words_i = 0; correct_words_i < m; correct_words_i++){
			correct_words[correct_words_i] = in.next();
		}
		String result = findSimilarity(incorrect_word, correct_words);
		System.out.println(result);
		in.close();
	}
	
	/** Just to test to see if things are working. Alternative to scanning for words */ 
	private static void testing() {
		String incorrect_word = "hello";
		String[] correct_words = new String[2];
		correct_words[0] = "hell";
		correct_words[1] = "bb";
		String result = findSimilarity(incorrect_word, correct_words);
		System.out.println(result);
	}
	
}