package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-linked-list-cycle/problem
 * @author Gusto
 */


import java.util.*;


/**
 * A linked list is said to contain a cycle if any node is visited more than once while traversing the list.
 * Complete the function provided in the editor below. It has one parameter: a pointer to a Node object named head that points to the head of a linked list.
 * Your function must return a boolean denoting whether or not there is a cycle in the list. If there is a cycle, return true; otherwise, return false.
 *   Note: If the list is empty, head will be null.
 *
 * Input Format:
 *   Our hidden code checker passes the appropriate argument to your function. You are not responsible for reading any input from stdin
 *
 * Constraints:
 *   0 <= list size <= 100
 *
 * Output:
 *   If the list contains a cycle, your function must return true. If the list does not contain a cycle, it must return false.
 *   The binary integer corresponding to the boolean value returned by your function is printed to stdout by our hidden code checker.
 */
public class DetectLinkedListCycleSolution {

	private final static int MAX_NODES = 100;

	/**
	 * Creates a linked list with the passed int array in order--the order of the array is the order of the linked list
	 * @param intArray
	 */
	public static SimpleLinkedList createLinkedList(int[] intArray) {
		// Create a new Linked List for the int array
		SimpleLinkedList intLinkedList = new SimpleLinkedList();

		// Iterate through the int array and create the linked list
		for (int i = 0; i < intArray.length; i++) {
			intLinkedList.append(intArray[i]);
		}

		return intLinkedList;
	}


	/**
	 * Determines if the passed linked list contains a cycle using the 100 max Node constraint
	 * @param head
	 * @return
	 */
	public static boolean hasCycleConstraint(Node head) {
		Node current = head;

		// Since we know the list cannot be larger than 100 Nodes, if you loop for 101+ then there is a cycle
		for (int i = 0; current != null; i++) {
			if (i == MAX_NODES) {
				return true;
			}
			current = current.getNext();
		}

		return false;
	}


	/**
	 * Determines if the passed linked list contains a cycle using a HashSet
	 * @param head
	 * @return
	 */
	public static boolean hasCycleHashSet(Node head) {
		Node current = head;

		// Navigate through the list marking each node we visit in a HashSet
		Set<Node> visitedSet = new HashSet<Node>();
        while (current != null) {
        	if (visitedSet.contains(current)) {
        		return true;
        	}
        	// Add the current Node to the visited set
        	visitedSet.add(current);
        	current = current.getNext();
        }

        return false;
	}


	/**
	 * Determines if the passed linked list contains a cycle using pointers
	 * @param head
	 * @return
	 */
	public static boolean hasCyclePointers(Node head) {
		// Empty list case
		if (head == null) return false;

		Node slow = head;
		Node fast = head.getNext();

		// Use two pointers to determine if there's a cycle
		while (slow != fast) {
			if ((fast == null) || (fast.getNext() == null)) return false;

			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}

		return true;
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        in.close();

        // Create a linked list from the passed data
        SimpleLinkedList intLinkedList = createLinkedList(arr);
        intLinkedList.printList();

        // ** Test Setup (for Input 2 &  below) **
        Node threeNode = intLinkedList.getNode(3);
        if (threeNode != null) {
        	Node twoNode = intLinkedList.getNode(2);
        	// Now point Node:3 -> Node:2 to create a cycle
        	if (twoNode != null) {
        		threeNode.setNext(twoNode);
        	}
        	// Print out the new list with the cycle
        	intLinkedList.printList(n + 2);
        }

        // Determine if the linked list contains a cycle
        boolean result1 = hasCycleConstraint(intLinkedList.getHead());
        System.out.println("Has Cycle: " + result1);
        boolean result2 = hasCycleHashSet(intLinkedList.getHead());
        System.out.println("Has Cycle: " + result2);
        boolean result3 = hasCyclePointers(intLinkedList.getHead());
        System.out.println("Has Cycle: " + result3);
	}

	/**
	 *
	 ** TEST INPUT 1:
1
1
    ** OUTPUT 1:
false
    *
    ** TEST INPUT 2:
3
1 2 3
    ** OUTPUT 2:
true
    *
    ** TEST INPUT 3:
5
1 1 2 4 3
    ** OUTPUT 3:
true
    *
	 */
}
