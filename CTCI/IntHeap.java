package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-find-the-running-median/problem
 * @author Gusto
 */


public class IntHeap {
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
