package Algos;
/**
 * HackerRank Algos:
 * https://www.hackerrank.com/challenges/diagonal-difference/problem
 * @author Gusto
 */


import java.util.*;


public class DiagonalDifferenceSolution {

	/**
	 * Returns the ABS diagonal difference between left & right diagonal sums of the input matrix
	 * @param size
	 * @param matrix
	 * @return
	 */
	public static int getDiagonalDifference(int size, int[][] matrix) {
		int diagonalDifference = 0;

		// First get the diagonal sum/total left to right
		int leftDiagonal = 0;
		for (int i = 0; i < size; i++) {
			leftDiagonal += matrix[i][i];
		}

		// Now get the diagonal sum/total right to left
		int rightDiagonal = 0;
		for (int i = 0; i < size; i++) {
			rightDiagonal += matrix[i][(size - 1) - i];
		}

		// Get the ABS difference
		diagonalDifference = Math.abs(leftDiagonal - rightDiagonal);

		return diagonalDifference;
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] a = new int[n][n];
        for (int a_i = 0; a_i < n; a_i++) {
            for(int a_j = 0; a_j < n; a_j++) {
                a[a_i][a_j] = in.nextInt();
            }
        }
        in.close();

        // Print the ABS diagonal difference
        int answer = getDiagonalDifference(n, a);
        System.out.println(answer);
	}

	/**
	 ** TEST INPUT:
3
11 2 4
4 5 6
10 8 -12
     ** OUTPUT:
15
     *
     ** TEST INPUT:
5
1 2 3 4 5
2 1 2 3 4
3 1 2 3 4
4 1 2 3 4
5 1 2 3 4
     ** OUTPUT:
5
	 */
}
