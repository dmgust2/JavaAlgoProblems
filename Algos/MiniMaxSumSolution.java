package Algos;
/**
 * HackerRank Algos:
 * https://www.hackerrank.com/challenges/mini-max-sum/problem
 * @author Gusto
 */


import java.util.*;


public class MiniMaxSumSolution {
	/**
	 * Print two space-separated long integers denoting the respective minimum and maximum values that can be calculated by
	 *  summing exactly four of the five integers.
	 * Constraints: Each integer is in the inclusive range 1 <= n <= 10^19
	 * Note: The final result sum CAN be bigger than 32 bit integer (so use long data type)
	 * @param intArray
	 */
	public static void printMiniMaxSum(int[] intArray) {		
		// We know the array size is exactly 5 ints
		int arraySize = 5;

		// First sort the array of ints
		// NOTE: This is a modified mergesort -> O(n*log(n))
		Arrays.sort(intArray);

		// Sum 4 out of the 5 smallest ints to get the MIN (we know these will be the FIRST 4 ints)
		long minSum = 0;
		for (int i = 0; i < (arraySize - 1); i++) {
			minSum += intArray[i];
		}

		// Sum 4 out of the 5 largest ints to get the MAX (we know these will be the LAST 4 ints)
		long maxSum = 0;
		for (int j = 1; j < arraySize; j++) {
			maxSum += intArray[j];
		}

		// Print out the Min and Max sums
		System.out.println(minSum + " " + maxSum);
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int[] arr = new int[5];
        for (int arr_i = 0; arr_i < 5; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        in.close();

        // Print out the Min and Max sums
        printMiniMaxSum(arr);
	}

	/**
	 ** TEST INPUT 1:
1 2 3 4 5
     ** OUTPUT 1:
10 14
     *
     ** TEST INPUT 2:
5 3 1 4 2
     ** OUTPUT 2:
10 14
     *
	 */
}
