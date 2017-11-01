package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem
 * Java 7
 * @author Gusto
 */


import java.util.Scanner;



public class ArrayLeftRotationSolution {

	/**
	 * 
	 * @param initialArray
	 * @param arrayLength
	 * @param numberOfRotations
	 * @return
	 */
	public static int[] arrayLeftRotation(int[] initialArray, int arrayLength, int numberOfRotations) {
		// Not sure I need to actually check if all the various constraints above are violated?
        if (arrayLength < 1) {
            throw new IllegalArgumentException("arrayLength must be > 0");
        }
        else if ((numberOfRotations < 1) || (numberOfRotations > arrayLength)) {
            throw new IllegalArgumentException("numberOfRotations must be > 0 and < arrayLength");
        }

        // Create a new array of size arrayLength
        int[] newRotatedArray = new int[arrayLength];
        // DEBUG
        //printIntArray("Initial array", initialArray);

        // Iterate over the passed initialArray for numberOfRotations left rotations
        for (int i = 0; i < arrayLength; i++) {
            int index = (i + (arrayLength - numberOfRotations)) % arrayLength;
            newRotatedArray[index] = initialArray[i];

            // DEBUG
            //printIntArray("Array rotation " + i, newRotatedArray);
        }

        return newRotatedArray;
    }

    /**
     * Print the passed Integer array with spaces
     * @param text
     * @param intArray
     */
    public static void printIntArray(String text, int[] intArray) {
        if (text != null) {
            System.out.print(text + ": ");
        }
        for (int i = 0; i < intArray.length; i++)
            System.out.print(intArray[i] + " ");

        System.out.println();
    }


    /**
     * MAIN
     * @param args
     */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	    int n = in.nextInt();
	    int k = in.nextInt();
	    int[] a = new int[n];
	    for (int a_i = 0; a_i < n; a_i++) {
	        a[a_i] = in.nextInt();
	    }
	    in.close();

	    int[] output = new int[n];
	    output = arrayLeftRotation(a, n, k);
	    printIntArray(null, output);
	}

}
