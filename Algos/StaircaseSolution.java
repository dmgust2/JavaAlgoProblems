package Algos;
/**
 * HackerRank Algos:
 * https://www.hackerrank.com/challenges/staircase/problem
 * @author Gusto
 */


import java.util.*;


public class StaircaseSolution {

	/**
	 * Print a staircase of the passed size using # symbols and spaces.
	 * Note: The last line must have 0 spaces in it.
	 * @param size
	 */
	public static void printStaircase(int size) {
		for (int i = 0; i < size; i++) {
			int spaces = (size - 1) - i;

			// First print spaces
			for (int j = 0; j < spaces; j++) {
				System.out.print(' ');
			}

			// Now print #'s
			for (int k = spaces; k < size; k++) {
				System.out.print('#');
			}
			System.out.println();
		}
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        // Print out the staircase
        printStaircase(n);
	}

	/**
	 ** TEST INPUT:
6
     ** OUTPUT:
     #
    ##
   ###
  ####
 #####
######
	 */
}
