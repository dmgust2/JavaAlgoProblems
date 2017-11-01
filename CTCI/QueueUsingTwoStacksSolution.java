package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem
 * @author Gusto
 */


import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * A queue is an abstract data type that maintains the order in which elements were added to it, allowing the oldest elements to be removed
 *   from the front and new elements to be added to the rear.
 * This is called a First-In-First-Out (FIFO) data structure because the first element added to the queue
 *   (i.e., the one that has been waiting the longest) is always the first one to be removed (like a line).
 * A basic queue has the following operations:
 *   Enqueue: add a new element to the end of the queue.
 *   Dequeue: remove the element from the front of the queue and return it
 * In this challenge, you must first implement a queue using TWO STACKS.
 * Then process q queries, where each query is one of the following 3 types of operations:
 *   1: Enqueue element x into the end of the queue.
 *   2: Dequeue the element at the front of the queue.
 *   3: Print the element at the front of the queue.
 *
 * Input Format:
 *   The first line contains a single integer q denoting the number of queries.
 *   Each line i of the q subsequent lines contains a single query in the form described in the problem statement above.
 *   All three queries start with an integer denoting the query type but only query 1 is followed by an additional space-separated value x
 *     denoting the value to be enqueued.
 *
 * Constraints:
 *   1 <= q <= 10^5 (100000)
 *   1 <= type <= 3
 *   1 <= x <= 10^9 (1000000000)
 *
 *   Note: Largest int value is 2147483647, so we can use int for x
 *   **NOTE: It is guaranteed that a valid answer always exists for each query of type 3
 *
 * Output:
 *   For each query of type 3, print the value of the element at the front of the queue on a new line.
 */
public class QueueUsingTwoStacksSolution {

	public static class MyQueue<T> {
        Stack<T> stackNewestOnTop = new Stack<T>();
        Stack<T> stackOldestOnTop = new Stack<T>();

        /**
         * Old, slow methods (first attempt)
         */
        // Add a new value to the queue
        public void enqueueSlow(T value) {
        	// Iterate through the oldest stack, pushing items on the newest stack
        	// TODO: This is very slow as always transferring the whole oldest stack on each add/enqueue
        	while (!stackOldestOnTop.isEmpty()) {
        		// Add the next oldest item to the newest stack
        		stackNewestOnTop.push(stackOldestOnTop.pop());
        	}

        	// Push the new value onto the newest stack
        	stackNewestOnTop.push(value);
        }

        // Remove the first value enqueued, or top value from the oldest stack
        public T dequeueSlow() {
        	// Iterate through the newest stack, pushing items on the oldest stack
        	// TODO: This is slow since we are transferring the whole newest stack on each removal/dequeue
        	while (!stackNewestOnTop.isEmpty()) {
        		// Add the next newest item to the oldest stack
        		stackOldestOnTop.push(stackNewestOnTop.pop());
    		}

        	// Return the oldest value off the oldest stack
        	if (stackOldestOnTop.isEmpty()) {
        		return null;
        	}
        	else {
        		return stackOldestOnTop.pop();
        	}
        }


        /**
         * New methods that are much faster, better algo!!!
         */

        // Add a new value to the queue
        public void enqueue(T value) {
        	// If both stacks are empty, add to the oldest stack
        	if (stackOldestOnTop.isEmpty() && stackNewestOnTop.isEmpty()) {
        		stackOldestOnTop.push(value);
        	}
        	else {
        		// Push the new value onto the newest stack
        		// TODO: I think you could skip the above logic and simply add to newest stack always
            	stackNewestOnTop.push(value);
        	}
        }

        // Return the first value enqueued, without removing it
        public T peek() {
        	if (stackOldestOnTop.isEmpty()) {
        		// TODO: Per the instructions (value is guaranteed)  I'm not adding error handling here
        	    // Return the first element added to the newest stack
        		return stackNewestOnTop.firstElement();
        	}
        	else {
        		return stackOldestOnTop.peek();
        	}
        }

        // Remove the first value enqueued, or top value from the oldest stack
        public T dequeue() {
        	// If the oldest stack contains items, return the oldest
        	if (!stackOldestOnTop.isEmpty()) {
        		return stackOldestOnTop.pop();
        	}
        	// Otherwise move the contents of the newest stack to the oldest and return the oldest
        	// This is way faster as we only move the contents of stackNewestOnTop -> stackOldestOnTop when the oldest stack is empty
        	else {
        		if (stackNewestOnTop.isEmpty()) {
        			// Edge case where the queue is empty
        			return null;
        		}
        		else {
        			while (!stackNewestOnTop.isEmpty()) {
                		// Add the next newest item to the oldest stack
                		stackOldestOnTop.push(stackNewestOnTop.pop());
            		}

        			return stackOldestOnTop.pop();
        		}
        	}
        }
    }


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args)  throws IOException {
		MyQueue<Integer> queue = new MyQueue<Integer>();

		// TEST--Timing
		long startTime = System.nanoTime();
		System.out.println("Starting...");

		// For UTF-8 text file input
		String inputFile = "QueueAsTwoStacks-IN-6.txt";
		String dataInputFilePath = "C:\\Users\\gusto\\eclipse\\workspace\\AlgorithmProblems\\Data\\";
		File dataInputFile = new File(dataInputFilePath + inputFile);
		Scanner in = new Scanner(dataInputFile, "UTF-8");

		// For UTF-8 text file output
		String outputFile = "QueueAsTwoStacks-OUT-6.txt";
		String dataOutputFilePath = "C:\\Users\\gusto\\eclipse\\workspace\\AlgorithmProblems\\Data\\";
		File dataOutputFile = new File(dataOutputFilePath  + outputFile);
		Scanner out = new Scanner(dataOutputFile, "UTF-8");

		// For console input (comment out sections above and Scanner out)
//		Scanner scan = new Scanner(System.in);

		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			int operation = in.nextInt();
			// enqueue
			if (operation == 1) {
				queue.enqueue(in.nextInt());
			}
			// dequeue
			else if (operation == 2) {
				queue.dequeue();
			}
			// print/peek
			else if (operation == 3) {
				int actual = queue.peek();
				int expected = out.nextInt();
				if (actual != expected) {
					System.out.println("FAIL: Expected: " + expected + ", but got: " + actual);
					break;
				}
				//System.out.println(actual);
			}
		}
		in.close();
		out.close();

		// Test--Timing
		System.out.println("DONE!");
		long endTime = System.nanoTime();
		long durationInMS = (endTime - startTime)/1000000;
		System.out.println("**Code duration in ms: " + durationInMS);
		// Note: My slow methods above were around ~4200ms, where as new fast ones are around ~300ms for test #6 above (files)
	}

	/**
	 *
	 ** TEST INPUT 1:
10
1 42
2
1 14
3
1 28
3
1 60
1 78
2
2
	 ** OUTPUT 1:
14
14
	 *
	 *
	 ** TEST INPUT 2:
See	 data file: C:\Users\gusto\eclipse\workspace\AlgorithmProblems\Data\QueueAsTwoStacks-IN-6.txt
	 ** OUTPUT 2:
See data file: C:\Users\gusto\eclipse\workspace\AlgorithmProblems\Data\QueueAsTwoStacks-OUT-6.txt
	 *
	 */
}
