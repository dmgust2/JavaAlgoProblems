package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-lonely-integer/problem
 * @author Gusto
 */


import java.util.*;
import java.util.stream.*;


/**
 * Task:
 *   Consider an array of n integers, A = [a0, a1, ... an-1], where all but one of the integers occur in pairs.
 *   In other words, every element in A occurs exactly twice except for one unique element (the 'lonely' integer).
 *   Given A, find and print the unique element.
 *
 * Input Format:
 *   The first line contains a single integer n denoting the number of integers in the array.
 *   The second line contains n space-separated integers describing the respective values in array A.
 *
 * Constraints:
 *   1 <= n < 100
 *   It is guaranteed that n is an odd number
 *   0 <= ai <= 100, where 0 <= i < n
 *
 * Output:
 *   Print the unique number that occurs only once in array A on a new line.
 */

public class LonelyIntegerSolution {

	/**
	 * BruteForce way to do this with a HashMap
	 * @param		int[] a
	 * @return		int Lonely integer
	 */
    public static int lonelyIntegerHashMap(int[] a) {
        // Put ints in a map so its <int key, int count>
        Map<Integer, Integer> intMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < a.length; i++) {
            if (intMap.containsKey(a[i])) {
                // Increment the count for this item/key
                intMap.put(a[i], intMap.get(a[i]) + 1);
            }
            else {
                // Add new key to the map
                intMap.put(a[i], 1);
            }
        }

        // Now return the lonely integer (the one that has a count of 1)
        // NOTE: This code won't handle invalid cases, like where there is no lonely int or more than one
        for (Map.Entry<Integer, Integer> entry : intMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1;
    }


    /**
     * MAIN
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        System.out.println(lonelyIntegerHashMap(a));
        in.close();
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
3
1 1 2
	 ** OUTPUT 1:
2
	 *
	 *
	 ** TEST INPUT 2:
5
0 0 1 2 1
	 ** OUTPUT 2:
2
	 *
	 */
}
