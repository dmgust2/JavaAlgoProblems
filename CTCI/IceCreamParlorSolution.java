package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem
 * @author Gusto
 */


import java.util.*;


/**
 * Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool together m dollars for ice cream.
 * On any given day, the parlor offers a line of n flavors. Each flavor i is numbered sequentially with a unique ID number from 1 to n and has a cost-i associated with it.
 * Note: They always buy 2 flavors of ice cream on each trip
 *
 * Task:
 *   Given the value of m and the cost of each flavor for t trips to the Ice Cream Parlor,
 *    help Sunny and Johnny choose two distinct flavors such that they spend their entire pool of money (exactly with no change) during each visit.
 *   For each trip to the parlor, print the ID numbers for the TWO types of ice cream that Sunny and Johnny purchase as two space-separated integers on a new line.
 *   You must print the smaller ID first and the larger ID second.
 *   *Note: Two ice creams having unique IDs i and j may have the same cost (i.e., cost-i = cost-j).
 *
 * Input Format:
 *   The first line contains an integer t denoting the number of trips to the ice cream parlor.
 *   The 3t subsequent lines describe all of Sunny and Johnny's trips to the parlor; each trip is described as follows:
 *     - The first line contains m (how much they will spend).
 *     - The second line contains n (the number of ice cream flavors available that trip/day).
 *     - The third line contains n space-separated integers denoting the cost of each respective flavor (cost of each flavor).
 *       The ith integer corresponds to the cost-i for the ice cream with ID number i (where 1 <= i <= n).
 *
 * Constraints:
 *   1 <= t <= 50
 *   2 <= m <= 10000 (10^4)
 *   2 <= n <= 10000 (10^4)
 *   1 <= cost-i <= 10000 (10^4) where i E (sigma) [1,n]  -> I think this means the Total (sum) cost of all flavors <= 10000
 *
 * Output:
 *   Print two space-separated integers denoting the respective ID numbers for the two distinct flavors they choose to purchase,
 *    where the smaller ID is printed first and the larger ID is printed second.
 *   Recall that each ice cream flavor has a unique ID number in the inclusive range from 1 to n flavors.
 */

class IceCream implements Comparable {
    int flavor;
    int index;

    public IceCream(int flavor, int index) {
    	this.flavor = flavor;
    	this.index = index;
    }

    @Override
    public int compareTo(Object o) {
    	return 0;
    }

    @Override
    public boolean equals(Object o) {
    	return true;
    }
}

public class IceCreamParlorSolution {

	public static int binarySearch(int first, int last, IceCream[] arr, int search) {
		return 0;
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int trip = 0; trip < t; trip++) {
			int m = in.nextInt();
			int n = in.nextInt();
			IceCream[] arr = new IceCream[n];

			for (int i = 0; i < n; i++)
				arr[i] = new IceCream(in.nextInt(), i + 1);

			Arrays.sort(arr);
			for (int i = 0; i < n - 1; i++) {
				int search = m - arr[i].flavor;
				if (search >= arr[i].flavor) {
					int index = binarySearch(i + 1, n - 1, arr, search);
					if (index != -1) {
						System.out.println(Math.min(arr[i].index, index) + " " + Math.max(arr[i].index, index));
						break;
					}
				}
			}
		}
		in.close();
	}

	/**
	 *
	 ** TEST INPUT 1:
2
4
5
1 4 5 3 2
4
4
2 2 4 3
	 ** OUTPUT 1:
1 4
1 2
	 *
	 *
	 ** TEST INPUT 2:
?
	 ** OUTPUT 2:
?
	 *
	 */

}
