package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-bubble-sort/problem
 * @author Gusto
 */


import java.util.*;


/**
 * Consider the following version of Bubble Sort:
for (int i = 0; i < n; i++) {
    // Track number of elements swapped during a single array traversal
    int numberOfSwaps = 0;

    for (int j = 0; j < n - 1; j++) {
        // Swap adjacent elements if they are in decreasing order
        if (a[j] > a[j + 1]) {
            swap(a[j], a[j + 1]);
            numberOfSwaps++;
        }
    }

    // If no elements were swapped during a traversal, array is sorted
    if (numberOfSwaps == 0) {
        break;
    }
}
 *
 * Task:
 *   Given an n-element array A = a0, a1, an-1 of distinct elements, sort array A in ascending order using the Bubble Sort algorithm above.
 *   Once sorted, print three things (one on each line): numSwaps, firstElement, lastElement
 * Hint: To complete this challenge, you must add a variable that keeps a running tally of all swaps that occur during execution
 *
 * Input Format:
 *   The first line contains an integer n denoting the number of elements in the array A.
 *   The second line contains n space-separated integers describing the respective values of a0, a1, an-1.
 *
 * Constraints:
 *   2 <= n <= 600
 *   0 <= ai <= 2 * 10^6 (2000000)
 *
 * Output:
 *   You must print the following three lines of output:
 *     1: Array is sorted in numSwaps swaps: numSwaps is the number of swaps that took place
 *     2: firstElement: the first element in the sorted array
 *     3: lastElement: the last element in the sorted array
 */
public class BubbleSortSolution {

	public static int totalNumberOfSwaps = 0;


	public static int[] bubbleSort(int arraySize, int[] intArray) {
		for (int i = 0; i < arraySize; i++) {
		    // Track number of elements swapped during a single array traversal
		    int numberOfSwaps = 0;

		    for (int j = 0; j < arraySize - 1; j++) {
		        // Swap adjacent elements if they are in decreasing order
		        if (intArray[j] > intArray[j + 1]) {
		            int temp = intArray[j];
		            intArray[j] = intArray[j + 1];
		            intArray[j + 1] = temp;
		            numberOfSwaps++;
		            totalNumberOfSwaps++;
		        }
		    }

		    // If no elements were swapped during a traversal, array is sorted
		    if (numberOfSwaps == 0) {
		        break;
		    }
		}

		// Return the sorted array
		return intArray;
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        in.close();

        int[] sortedArray = bubbleSort(n, a);

        // Print expected output
        System.out.println("Array is sorted in " + totalNumberOfSwaps + " swaps.");
        System.out.println("First Element: " + sortedArray[0]);
        System.out.println("Last Element: " + sortedArray[n - 1]);
	}

	/**
	 *
	 ** TEST INPUT 1:
3
1 2 3
	 ** OUTPUT 1:
Array is sorted in 0 swaps.
First Element: 1
Last Element: 3
	 *
	 *
	 ** TEST INPUT 2:
3
3 2 1
	 ** OUTPUT 2:
Array is sorted in 3 swaps.
First Element: 1
Last Element: 3
	 *
	 */
}
