package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-making-anagrams/problem
 * @author Gusto
 */


import java.util.*;


/**
 * Determine the number of chars required to delete from each passed string a, b to get valid anagrams.
 * It is guaranteed that strings a and b consist of lowercase English alphabetic letters (i.e. a-z).
 *** NOTE: You only need the number of deletions, not the actual final anagram strings
 *
 * Input Format:
 *   Strings a and b
 *
 * Constraints:
 *   1 <= |a|, |b| <= 10000 (10^4)
 *
 * Output:
 *   Number of total deletions required from both strings a, b to get anagrams 
 */
public class MakingAnagramsSolution {

	public static int numberNeeded(String first, String second) {
		int numberOfDeletions = 0;
		// Create an array of ints that maps to chars a-z: 0 -> a, 1 -> b, ..., 25 -> z
		int[] letters = new int[26];

	    // First iterate over the first string and update frequency of letters found
		for (int i = 0; i < first.length(); i++) {
			char c = first.charAt(i);
			// Increment this char location in the letters array
			letters[c - 'a']++;
		}

		// Now iterate over the second string and update the frequency of letters found
		for (int i = 0; i < second.length(); i++) {
			char c = second.charAt(i);
			// Decrement this char location in the letters array
			letters[c - 'a']--;
		}

		// Looks like O(n+m) ?

		// Now sum the char frequencies in the letters array using ABS
		for (int i = 0; i < letters.length; i++) {
			numberOfDeletions += Math.abs(letters[i]);
		}

		return numberOfDeletions;
    }


	/**
	 * MAIN
	 * @param args
	 */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        in.close();
//    	String a = "abccddefag";
//    	String b = "cdgggzz";
        System.out.println(numberNeeded(a, b));
    }

}
