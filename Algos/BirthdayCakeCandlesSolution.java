package Algos;
/**
 * HackerRank Algos:
 * https://www.hackerrank.com/challenges/birthday-cake-candles/problem
 * @author Gusto
 */


import java.io.*;
import java.util.*;


/**
 * Given you can only blow out the tallest (MAX i) candles, how many can you blow out?
 *
 * Input Format:
 *   The first line contains a single integer n denoting the number of candles on the cake (persons's age).
 *   The second line contains n space-separated integers, where each integer i describes the height of candle i.
 *
 * Constraints:
 *   1 <= n candles <= 10^5
 *   1 <= heighti <= 10^7
 *
 * Output:
 *   Print the number of candles they can blow out on a new line.
 */
public class BirthdayCakeCandlesSolution {

	/**
	 * @param candles
	 * @param intArray
	 * @return
	 */
	public static int birthdayCakeCandles(int candles, int[] intHeights) {
		// Note: candles == intHeights.length

		// The number of candles that can be blown out (only the tallest candles). Will always be at least 1.
		int blowOut = 1;

		// Sort and get the max (biggest int) in the array, which is the last element post-sort
		// NOTE: This is a modified mergesort -> O(n*log(n))
		Arrays.sort(intHeights);
		int maxCandle = intHeights[candles - 1];
		// DEBUG
        System.out.println("MAX Candle height: " + maxCandle);

        // **NOTE: Without this check, test cases 6 & 7 fail
		// Edge cases (don't need to iterate through)
		// If all the numbers in the array are the same, then you can blow out ALL the candles
		if (intHeights[0] == maxCandle) {
			return candles;
		}

		// Iterate through the candle heights in reverse order (2nd to last to start)
		for (int i = candles - 2; i > 0; i--) {
			if (intHeights[i] < maxCandle) {
				break;
			}
			else {
				blowOut++;
			}
		}

		return blowOut;
    }


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// For UTF-8 text file input
//		String inputFile = "BirthdayCandles-6.txt";
		String inputFile = "BirthdayCandles-7.txt";
		String dataInputFilePath = "C:\\Users\\gusto\\eclipse\\workspace\\AlgorithmProblems\\Data\\" + inputFile;
		File dataInputFile = new File(dataInputFilePath);
		Scanner in = new Scanner(dataInputFile, "UTF-8");

		// For console input
//		Scanner in = new Scanner(System.in);

		// Parse the input (number of candles n, and int array of n candle heights)
        int n = in.nextInt();
        int[] ar = new int[n];
        for (int ar_i = 0; ar_i < n; ar_i++) {
            ar[ar_i] = in.nextInt();
        }
        in.close();

        // DEBUG
        System.out.println("Age/Candles: " + n);
        System.out.println("Height array length: " + ar.length);

        // Print out the number of candles blown out
        int result = birthdayCakeCandles(n, ar);
        System.out.println(result);
	}

	/**
	 ** TEST INPUT 1:
4
3 2 1 3
     ** OUTPUT 1:
2
     *
     ** TEST INPUT 2:
10
2 2 5 8 6 8 3 8 1 4
     ** OUTPUT 2:
3
     *
     ** TEST INPUT 3:
See data file: C:\Users\gusto\eclipse\workspace\AlgorithmProblems\Data\BirthdayCandles-6.txt
     ** OUTPUT 3:
100000
     *
	 */
}
