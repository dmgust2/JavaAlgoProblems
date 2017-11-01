package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-find-the-running-median/problem
 * @author Gusto
 */


import java.text.DecimalFormat;
import java.util.*;


/**
 * The median of a dataset of integers is the midpoint value of the dataset for which an equal number of integers are less than and greater than the value.
 *  To find the median, you must first sort your dataset of integers in non-decreasing order then the following:
 *   1: If your dataset contains an odd number of elements, the median is the middle element of the sorted sample.
 *     e.g. In the sorted dataset {1,2,3}, 2 is the median
 *   2: If your dataset contains an even number of elements, the median is the average of the two middle elements of the sorted sample.
 *     e.g. In the sorted dataset {1,2,3,4} => 2+3/2 = 2.5 is the median
 *
 * Given an input stream of n integers, you must perform the following task for each ith integer:
 *   1: Add the ith integer to a running list of integers.
 *   2: Find the median of the updated list (i.e. for the first element through the ith element).
 *   3: Print the list's updated median on a new line. The printed value must be a double-precision number scaled to one decimal place (e.g. 12.3).
 *
 * Input Format:
 *   The first line contains a single integer n denoting the number of integers in the data stream.
 *   Each line i of the n subsequent lines contains an integer ai to be added to your list.
 *
 * Constraints:
 *   1 <= n <= 10^5 (100000)
 *   0 <= ai <= 10^5 (100000)
 *
 * Output:
 *   After each new integer is added to the list, print the list's updated median on a new line as a single double-precision number scaled to
 *    1 decimal place (e.g. 12.3)
 */
public class HeapsRunningMedianSolution {

	public static MinIntHeap minIntHeap;
	public static MaxIntHeap maxIntHeap;


	/**
	 * Optimizes the two heaps so we can always easily determine the median
	 */
	public static void optimizeHeaps() {
		// DEBUG: Print the current heap structures BEFORE balancing
		System.out.println("After inserts, before optimizing:");
    	minIntHeap.print();
    	maxIntHeap.print();

    	// Basically we want to make sure we always keep the elements in the correct heap and balanced (heap size never differs by more than 1)
		while (minIntHeap.getSize() != 0 && maxIntHeap.getSize() != 0 && (minIntHeap.peek() < maxIntHeap.peek())) {
			int min = minIntHeap.poll();
			int max =  maxIntHeap.poll();
			// Swap the top heap numbers
			minIntHeap.add(max);
			maxIntHeap.add(min);
		}

		// DEBUG: Print the current heap structures AFTER balancing
		System.out.println("After optimization:");
    	minIntHeap.print();
    	maxIntHeap.print();
	}


	/**
	 * Prints the median value across the two heaps, scaled to one decimal place (e.g. 12.3)
	 */
	public static void printMedian() {
		double median = 0;

		// Given the way we are doing inserts, an even number of element means both stacks are the same size
		if (minIntHeap.getSize() == maxIntHeap.getSize()) {
			// Note: Both heaps should never be empty (size = 0) per the Constraints (n >= 1)
			// **NOTE: You need at least one of the numbers to be casted to a double, otherwise will do integer math and throw away the remainder...
			median = ((double)minIntHeap.peek() + (double)maxIntHeap.peek()) / 2;
		}
		else {
			// For an odd number of elements, the median is always the top of the maxIntHeap (given our heap optimizations)
			median = maxIntHeap.peek();
		}

		// Set the decimal format to 1 decimal place
	    DecimalFormat df = new DecimalFormat("#0.0");

	    // Print the median value scaled to one decimal place (e.g. 12.3)
		System.out.println("Current median: " + df.format(median));
		//System.out.println(df.format(median));
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Create Min and Max heaps
        minIntHeap = new MinIntHeap(n/2 + 1);
        maxIntHeap = new MaxIntHeap(n/2 + 1);

        // As we parse the input, add the next int to the proper heap, then optimize the heaps, then print out the current median
        for (int a_i = 0; a_i < n; a_i++) {
        	// This basically alternates which heap we insert into initially
        	if (maxIntHeap.getSize() > minIntHeap.getSize()) {
        	    minIntHeap.add(in.nextInt());
        	}
        	else {
        		maxIntHeap.add(in.nextInt());
        	}

        	// Optimize the two heaps
        	optimizeHeaps();

            // Print the current Median for the current list/heap
            printMedian();
        }
        in.close();
	}

	/**
	 *
	 ** TEST INPUT 1:
6
12
4
5
3
8
7
	 ** OUTPUT 1:
12.0
8.0
5.0
4.5
5.0
6.0
	 *
	 *
	 ** TEST INPUT 2:
?
	 ** OUTPUT 2:
?
	 *
	 */
}
