package Algos;
/**
 * HackerRank Algos:
 * https://www.hackerrank.com/challenges/pangrams/problem
 * @author Gusto
 */


import java.util.*;


/**
 * Roy wanted to increase his typing speed for programming contests. His friend suggested that he type the sentence "The quick brown fox jumps over the lazy dog" repeatedly.
 * This sentence is known as a pangram because it contains every letter of the alphabet.
 * After typing the sentence several times, Roy became bored with it so he started to look for other pangrams.
 * Given a sentence, determine whether it is a pangram.
 *
 * Input Format:
 *   Input consists of a string s
 *
 * Constraints:
 *   Length of s can be at most 10^3 and it may contain spaces, lower case and upper case letters.
 *   Lower-case and upper-case instances of a letter are considered the same
 *   1 <= |s| <= 1000
 *
 * Output:
 *   Output a line containing 'pangram' if s is a pangram, otherwise output 'not pangram'
 */
public class PangramsSolution {

	static String pangrams(String line) {
		String yes = "pangram";
		String no = "not pangram";
		int[] letters = new int[26];

		// Strip all whitespace from the string
		String lineWithoutSpaces = line.replaceAll("\\s+","");
		//System.out.println("DEBUG: lineWithoutSpaces: " + lineWithoutSpaces);

		// Iterate through the passed string keeping track of each of the chars found (a-z or A-z)
		for (int i = 0; i < lineWithoutSpaces.length(); i++) {
			// Convert all to lowercase since we don't care about the case
			char c = Character.toLowerCase(lineWithoutSpaces.charAt(i));
			//System.out.println("DEBUG: Char: " + c);
			// Increment this char location in the letters array
			letters[c - 'a']++;
		}

		// Now iterate through the letters to see if any are missing
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == 0) {
				return no;
			}
		}
 
		return yes;
    }


    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        in.close();
        System.out.println(pangrams(s));
    }

	/**
	 *
	 ** TEST INPUT 1:
We promptly judged antique ivory buckles for the next prize
	 ** OUTPUT 1:
pangram
	 *
	 *
	 ** TEST INPUT 2:
We promptly judged antique ivory buckles for the prize
	 ** OUTPUT 2:
not pangram
	 *
	 */
}
