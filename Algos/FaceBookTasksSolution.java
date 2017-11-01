package Algos;


import java.util.*;


/**
 * Task:
 *   Determine the minimum total run time to run all tasks (set/list/array of tasks). Each task type is defined by an integer t.
 *   Two different tasks can run in parallel.
 *   Each task runs in one time segment, unless the proceeding task is of the same type, then it takes run time wait w before another task of the same type can run.
 *
 * Input Format:
 *   The first line contains a single integer n denoting the total number of tasks
 *   The second line contains the wait time w as an integer representing the number of time segments
 *   The third line contains n tasks of type t (integers) separated by a space
 *
 * Constraints:
 *   1 <= n <= 10000 (10^4)
 *   1 <= t <= MAX INT
 *   0 <= w <= MAX INT
 * Note: Can use integers for everything
 *
 * Output:
 *   Print the total time segments (integer) required to run all tasks
 */

class IntHeap {
	protected int size = 0;
	protected int[] items;


    /**
     * CTOR
     * @param capacity
     */
    public IntHeap(int capacity) {
    	items = new int[capacity];
    }


    /**
     * Protected Helper methods
     * Note: Protected allows the member to be accessed within the class and all of it's subclasses
     */
    protected int getParentIndex(int childIndex) { return (childIndex -1)/2; }
    protected int getLeftChildIndex(int parentIndex) { return (2 * parentIndex) + 1; }
    protected int getRightChildIndex(int parentIndex) { return (2 * parentIndex) + 2; }

    protected boolean hasParent(int index) { return getParentIndex(index) >= 0; }
    protected boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    protected boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }

    protected int parent(int index) { return items[getParentIndex(index)]; }
    protected int leftChild(int index) { return items[getLeftChildIndex(index)]; }
    protected int rightChild(int index) { return items[getRightChildIndex(index)]; }


    /**
     * Swap two items in the heap
     * @param indexOne
     * @param indexTwo
     */
    protected void swap(int indexOne, int indexTwo) {
        int temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }

    /**
     * Returns the current heap size
     * @return
     */
    public int getSize() { return size; }

    /**
     * Returns the first element/item in the heap
     * @return
     */
    public int peek() {
        if (size == 0) throw new IllegalStateException();

        return items[0];
    }

    /**
     * Print the current MinIntHeap in order
     */
    public void print() {
    	for (int i = 0; i < size; i++) {
    		System.out.print(items[i] + " ");
    	}
    	System.out.println();
    }
}

class MaxIntHeap extends IntHeap {

    /**
     * CTOR
     * @param capacity
     */
    public MaxIntHeap(int capacity) {
    	super(capacity);
    }


    /**
     * Restructures/fixes the heap upon insertions (bubbles new values up the heap)
     */
    private void heapifyUp() {
        int index = size - 1;		// Index of last element/item in the heap (e.g. just added/inserted value)

        // While parent is smaller, swap with current
        while (hasParent(index) && parent(index) < items[index]) {
            swap(getParentIndex(index), index);
            // Move up the heap
            index = getParentIndex(index);
        }
    }

    /**
     * Restructures/fixes the heap upon polls/deletions (bubbles values down the heap)
     */
    private void heapifyDown() {
        int index = 0;		// Index of first/root element in the heap

        // While have children (if no left, then there's no right child)
        while (hasLeftChild(index)) {
            // Get larger child
            int largerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) > leftChild(index)) {
                largerChildIndex = getRightChildIndex(index);
            }

            // If current item value is larger than children, then exit (nothing more to do)
            if (items[index] > items[largerChildIndex]) {
                break;
            }
            else {
                // Else we swap values
                swap(index, largerChildIndex);
            }

            // Move down the heap
            index = largerChildIndex;
        }
    }


    /**
     * Add a new element to the heap
     * @param item
     */
    public void add(int item) {
        items[size] = item;		// Add the new element to bottom of heap
        size++;                 // Increase the size of the heap by one
        heapifyUp();            // Call method to restructure/fix the heap
    }

    /**
     * Extract/remove/delete the maximum element, remove from the array, and restructure the heap
     * @return
     */
    public int poll() {
        if (size == 0) throw new IllegalStateException();

        int item = items[0];            // Get the first element (biggest or MAX)
        items[0] = items[size - 1];		// Set the first/root element to the last element
        size--;                       	// Decrease the size of the heap by one
        heapifyDown();                	// Call method to restructure/fix the heap

        return item;
    }

    /**
     * Print the current MaxIntHeap in order
     */
    public void print() {
    	System.out.print("Current MaxIntHeap: ");
    	super.print();
    }
}

public class FaceBookTasksSolution {

	public static int determineRunTimeHeap(Queue<Integer> tasks, int waitTime) {
		int queueSize = tasks.size();
		int totalRunTime = 0;

		// Create a Max Heap for managing run times
		MaxIntHeap maxHeap = new MaxIntHeap(queueSize);

		// Iterate through the task list populating the heap
		for (int t : tasks) {
			maxHeap.add(t);
		}

		return totalRunTime;
	}

	// ** FAILS for test 1 below -> outputs 7 when should be 5
	public static int determineRunTime(int[] taskList, int waitTime) {
		int listSize = taskList.length;
		int totalRunTime = 0;

		if (listSize <= 1) {
		    return listSize;
		}

		// Iterate over the list of tasks
		for (int i = 0; i < listSize; i++) {
			int currentTask = taskList[i];
			// Each task takes one time segment plus the MAX time segments equal to the core dump time
			totalRunTime = totalRunTime + (1 + waitTime);

			// Now lookahead and adjust the number of time segments based on future tasks
			for (int j = 1; j <= waitTime; j++) {
				int nextTaskIndex = i + j;
				if (nextTaskIndex < listSize && currentTask != taskList[nextTaskIndex]) {
					totalRunTime--;
				}
				else {
					// TODO: We need additional logic here
				    break;
				}
			}
		}

		return totalRunTime;
	}


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int w = in.nextInt();
        // Create a Queue for the list of tasks
        Queue<Integer> tasksQueue = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
        	tasksQueue.add(in.nextInt());
        }
// Array of ints
//        int[] tasks = new int[n];
//        for (int i = 0; i < n; i++) {
//        	tasks[i] = in.nextInt();
//        }
        in.close();

        // Determine the total run time of the tasks queue/list
        int totalRunTime = determineRunTimeHeap(tasksQueue, w);
        //int totalRunTime = determineRunTime(tasks, w);
        System.out.println(totalRunTime);
	}

	/**
	 *
	 ** TEST INPUT 0:
1
2
1
	 ** OUTPUT 0:
1
     *
     *
	 ** TEST INPUT 1:
4
3
1 2 3 1
	 ** OUTPUT 1:
5
	 *
	 *
	 ** TEST INPUT 2:
4
2
1 1 2 1
	 ** OUTPUT 2:
7
	 *
	 */

}
