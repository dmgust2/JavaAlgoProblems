package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-find-the-running-median/problem
 * @author Gusto
 */


public class MinIntHeap extends IntHeap {

    /**
     * CTOR
     * @param capacity
     */
    public MinIntHeap(int capacity) {
    	super(capacity);
    }


    /**
     * Restructures/fixes the heap upon insertions (bubbles new values up the heap)
     */
    private void heapifyUp() {
        int index = size - 1;		// Index of last element/item in the heap (e.g. just added/inserted value)

        // While parent is larger, swap with current
        while (hasParent(index) && parent(index) > items[index]) {
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
            // Get smaller child
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            // If current item value is smaller than children, then exit (nothing more to do)
            if (items[index] < items[smallerChildIndex]) {
                break;
            }
            else {
                // Else we swap values
                swap(index, smallerChildIndex);
            }

            // Move down the heap
            index = smallerChildIndex;
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
     * Extract/remove/delete the minimum element, remove from the array, and restructure the heap
     * @return
     */
    public int poll() {
        if (size == 0) throw new IllegalStateException();

        int item = items[0];            // Get the first element (smallest or MIN)
        items[0] = items[size - 1];		// Set the first/root element to the last element
        size--;                       	// Decrease the size of the heap by one
        heapifyDown();                	// Call method to restructure/fix the heap

        return item;
    }

    /**
     * Print the current MinIntHeap in order
     */
    public void print() {
    	System.out.print("Current MinIntHeap: ");
    	super.print();
    }
}
