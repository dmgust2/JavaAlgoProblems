package Algos;
/**
 * Facebook interview question
 * @author Gusto
 */


import java.util.*;
import java.util.stream.*;


/**
 * Task:
 *   Given a sequence of n digits (0-9) x, find the next permutation (smallest number that has same set of digits as x and is greater than x).
 *   If x is the greatest possible number with its set of digits (digits in descending order), then print the next permutation is not possible.
 *
 * Input Format:
 *   The first line contains an integer n denoting the number of digits
 *   The second line contains the n spaced digits
 *
 * Constraints:
 *   1 <= n <= 10
 *   1 <= x <= 10^9 (1000000000)
 *
 * Output:
 *   Print the next permutation sequence or not possible
 *
 */
public class FaceBookNextPermutation {

	/**
	 * Prints the next permutation given the passed int array (or digit sequence) e.g. 132
	 * @param sequence		int[] of digits e.g. [1,2,3]
	 */
	public static void printNextPermutation(int[] sequence) {
		String NOT_POSSIBLE = "Next permutation NOT possible";

		if (sequence.length <= 1) {
			System.out.println(NOT_POSSIBLE);
			return;
		}

		// TODO: Is this the best way to convert int[] => String?
		String digitString = "";
		for (int i = 0; i < sequence.length; i++) {
			digitString = digitString + sequence[i];
		}

		// Note: using a Set is a good way to prevent duplicates
		// OR you could do this also to remove dupes (which actually seems faster): ArrayList<String> finalList = new ArrayList<String>(new LinkedHashSet<String>(permutationList));
		// https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
		Set<String> permutationSet = generatePermutations("", digitString);
		// DEBUG
		//printPermutations(permutationSet, "generated");

		// Convert the Set to a List so we can sort (new Java 8 streams feature)
		List<String> permutationList = permutationSet.stream().collect(Collectors.toList());

		Collections.sort(permutationList);
		// DEBUG
		//printPermutations(permutationList, "sorted");

		// Return the next value (permutation) in the sorted list
		int index = permutationList.indexOf(digitString);
		if (index != -1) {
			System.out.println("Current permutation: " + permutationList.get(index));
			System.out.println("Next permutation:    " + permutationList.get(index + 1));
		}
		else {
			System.out.println(NOT_POSSIBLE);
		}
	}

	/**
	 * Generates all possible permutations (without duplicates)
	 * @param prefix		Input string (digits) prefix
	 * @param digitString	Input string (digits) prefix
	 * @return				Set<String> of permutations
	 */
	public static Set<String> generatePermutations(String prefix, String digitString) {
		Set<String> permutationSet = new HashSet<>();

		if (digitString.length() == 0) {
			permutationSet.add(prefix);
		}
		else {
			// Recursively iterate over the string generating permutations
			for (int i = 0; i < digitString.length(); i++) {
				String newPrefix = prefix + digitString.charAt(i);
				String newDigitString = digitString.substring(0, i) + digitString.substring(i + 1, digitString.length());
				permutationSet.addAll(generatePermutations(newPrefix, newDigitString));
			}
		}

		return permutationSet;
	}

	/**
	 * Print the collection of permutations
	 * @param permutations		List or Set of permutations
	 * @param type				Type of permutations
	 */
	public static void printPermutations(Collection<String> permutations, String type) {
		System.out.println("Printing the " + type + " permutations:");
		// One-line alternate: permutations.forEach(permutation -> System.out.print(p + " "));
		for (String p : permutations) {
			System.out.print(p + " ");
		}
		System.out.println();
	}



	/**
	 * Prints all permutations of a passed string
	 * @param prefix	Input string prefix
	 * @param str		Input string
	 */
	public static void printPermutations(String prefix, String str) {
		if (str.length() == 0) {
			System.out.println(prefix);
		}
		else {
			// Recursively iterate over the string generating permutations
			for (int i = 0; i < str.length(); i++) {
				generatePermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, str.length()));
			}
		}
	}


    /**
     * MAIN
     * @param args
     */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] sequence = new int[n];
		for (int i = 0; i < n; i++) {
			sequence[i] = in.nextInt();
		}
        in.close();

        // TEST--Timing
        long startTime = System.nanoTime();
        System.out.println("Starting...");

        // Print the next sequence or permutation after the passed digits
        // This is not as efficient -> O(n!) -> 4! = 24
        printNextPermutation(sequence);

        // TODO: Add algo that performs better (doesn't need to generate all permutations)
        

        // Test--Timing
        System.out.println("DONE!");
        long endTime = System.nanoTime();
        long durationInMS = (endTime - startTime)/1000000;
        System.out.println("**Code duration in ms: " + durationInMS);


		// Quick generation of permutations via recursion
//		printPermutations("", "1");
//		System.out.println("-----------------------");
//		printPermutations("", "12");
//		System.out.println("-----------------------");
//		printPermutations("", "123");
	}

	/**
	 *
	 ** TEST INPUT 0:
1
1
	 ** OUTPUT 0:
1
	 *
	 *
	 ** TEST INPUT 1:
2
1 2
	 ** OUTPUT 1:
21
	 *
	 *
	 ** TEST INPUT 2:
3
1 2 3
	 ** OUTPUT 2:
132
	 *
	 *
	 ** TEST INPUT 3:
4
4 7 6 2
	 ** OUTPUT 3:
6247
	 *
	 *
	 ** TEST INPUT 4:
10
3 8 7 5 5 4 3 2 1 0
	 ** OUTPUT 4:
4012335578
	 *
	 */
}
