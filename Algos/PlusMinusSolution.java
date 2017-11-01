package Algos;
/**
 * HackerRank Algos:
 * https://www.hackerrank.com/challenges/plus-minus/problem
 * @author Gusto
 */


import java.util.*;
import java.text.DecimalFormat;


public class PlusMinusSolution {

	/**
	 * Prints the following based on the integer array input:
	 * A decimal representing the fraction of positive numbers in the array compared to its size.
	 * A decimal representing the fraction of negative numbers in the array compared to its size.
	 * A decimal representing the fraction of zeroes in the array compared to its size.
	 * @param size
	 * @param array
	 */
	public static void printArrayFractions(int size, int[] a) {
		int positiveCount = 0;
		int negativeCount = 0;
		int zeroCount = 0;

		// Iterate through the integer array incrementing counts of +, -, 0
		for (int i = 0; i < size; i++) {
			if (a[i] > 0) {
				positiveCount++;
			}
			else if (a[i] < 0) {
				negativeCount++;
			}
			else {
				zeroCount++;
			}
		}

		// Compute the fractions based on counts/size
		// Set the decimal format to 6 places
		DecimalFormat df = new DecimalFormat("#0.000000");
		// **NOTE** You need one of the numbers to be casted to a float|double, otherwise will do integer math and throw away the remainder...
		double positiveFraction = ((double) positiveCount) / size;
		double negativeFraction = ((double) negativeCount) / size;
		double zeroFraction = ((double) zeroCount) / size;

		// Print out the fractions
		System.out.println(df.format(positiveFraction));
		System.out.println(df.format(negativeFraction));
		System.out.println(df.format(zeroFraction));
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        in.close();

        // Print out the fractions
        printArrayFractions(n, arr);
    }

	/**
	 ** TEST INPUT:
6
-4 3 -9 0 4 1
     ** OUTPUT:
0.500000
0.333333
0.166667
	 */
}
