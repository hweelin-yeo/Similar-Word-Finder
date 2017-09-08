/** - Code structure
 * - How I would go on to implement a more accurate version of spell-checker, with
 * 		a) Data collection of commonly misspelled words
 * 		b) Classify ways of misspelling words for each category - Deletion, Insertion and 
 * 																Substitution
 * 
 * I describe my approach in 2 steps:
1. First get data  about commonly misspelled words
2. Tabulate the common misspell ways , using normalLDForDataCollection
3. Implement weighted leveinstein distance 


 * Due to time-constraints, I am unable to complete the full implementation, but
 * this is a high-level view of how I am going to go about doing it. The code does not work
 * at run-time (yet D: ). 
 */

/*********************************************************************************/
/** Class Description: Class LDDistance contains methods to calculate different types of
 * LDdistances - which in this case, is LD and weightedLD  */

public class LDDistance {

	public enum MisSpell { INSERTION, DELETION, SUBSTITUTION };

	static double costOfSubstitution;
	static double costOfInsertion;
	static double costOfDeletion;

	/** Function that provides a standard way of calculating LD, with the "cost" of different types 
	 * of misspells being the same - i.e. a user has an equal chance of misspelling by substitution,
	 * insertion and deletion
	 * 
	 *  Main use-case: Tabulate the common misspell ways (taking note of which chars involved, 
	 *  and which way to misspell) before deciding on the “cost” by adding it into the different 
	 *  misspells dictionaries*/

	public static void normalLDForDataCollection(String inc, String correction) {

		int costOfSubstitution = 1;
		int costOfInsertion = 1;
		int costOfDeletion = 1;

		int [][] mx; // matrix for Leveinstein Distance
		int n; // length of inc
		int m; // length of correction
		char inc_i; // ith character of inc
		char correction_j; // jth character of correction

		n = inc.length ();
		m = correction.length ();

		// inc or correction are of length 0
		if (n == 0) {
			return;
		}

		if (m == 0) {
			return;
		}

		// Set up matrix
		mx = new int[n+1][m+1];
		for (int i = 0; i <= n; i++) {
			mx[i][0] = i;
		}

		for (int j = 0; j <= m; j++) {
			mx[0][j] = j;
		}

		// Calculate LD
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {

				inc_i = inc.charAt (i - 1);
				correction_j = correction.charAt (j - 1);
				int cost = 0;
				if (inc_i == correction_j) {
					cost = 0;
				} else {
					cost = costOfSubstitution;
				}

				mx[i][j] = minimumForDataCollection (mx[i-1][j]+costOfDeletion, 
						mx[i][j-1]+costOfInsertion, 
						mx[i-1][j-1] + cost); 
				
				enterMispellDictForDataCollection (mx[i-1][j]+costOfDeletion, 
						mx[i][j-1]+costOfInsertion, 
						mx[i-1][j-1], inc_i, correction_j, cost);
			}
		}
	}

	/** Method to be called to find weightedLD, after data collection is done.
	 * Returns: WeightedLD for string inc and correction 
	 * Precondition: inc and correction are both strings */
	
	public static double weightedLD (String inc, String correction) {
		// TODO: Similar implementation to normalLDForDataCollection, only that 
		// the code for to find mx[i][j] would look different, as cost of misspells
		// is subjected to which character tuples.
		
		// To find the cost, call Weights.weighMisspells(inc_i, correction_j, MisSpell.x) 
		// where x is the type of misspell.
		
		return 0.0;
	}

					/******************************************* 
					 * 				Helper Functions 
					 * ****************************************/
	
	/** Returns: Minimum of integers d, e and f 
	 * Precondition: d, e and f are integers */
	private static int minimumForDataCollection (int d, int e, int f) {
		double min = minimum(d, e, f);
		return (int) min;
	}
	
	/** Returns: Minimum of doubles a, b and c 
	 * Precondition: a, b and c are doubles */
	public static void enterMispellDictForDataCollection (int d, int e, int f, char i, char j, int cost) {
		MisSpell m = whichMispell(d, e, f+cost);
		if (m == MisSpell.DELETION) {
			// TODO : Add Tuple <i,j> to Weights.mispellByDeletion (if it doesn't exist) with a 
			// count of 1, or increase its count by 1
		}
		else if (m == MisSpell.INSERTION) {
			// TODO : Add Tuple <i,j> to Weights.mispellByInsertion (if it doesn't exist) with a 
			// count of 1, or increase its count by 1
		}
		else if (m == MisSpell.SUBSTITUTION) {
			if (cost == 0) {
				return; // no misspelling occurred 
			} else {
				// TODO : Add Tuple <i,j> to Weights.mispellBySubstition (if it doesn't exist) with a 
				// count of 1, or increase its count by 1
			}
		}
	}
	
	/** Returns: Minimum of doubles a, b and c 
	 * Precondition: a, b and c are doubles */
	private static double minimum (double d, double e, double f) {
		double min = d;
		if (e < min) {
			min = e;
		}
		if (f < min) {
			min = f;
		}
		return min;
	}
	
	/** Returns which type of misspelling error.  
	 * Precondition: d, e and f are doubles */
	private static MisSpell whichMispell (double d, double e, double f) {
		double min = minimum(d, e,f);
		if (d == min) { 
			return MisSpell.DELETION;
		} else if (e == min) {
			return MisSpell.INSERTION;
		} else 
			return MisSpell.SUBSTITUTION;
	}
}
