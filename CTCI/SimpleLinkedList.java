package CTCI;

/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-linked-list-cycle/problem
 * @author Gusto
 */


public class SimpleLinkedList {

	private Node head = null;
	private int size = 0;


	public Node getHead() { return head; }


	/**
	 * Appends a new Node to the end of the SimpleLinkedList
	 * @param data
	 */
	public void append(int data) {
        if (head == null) {
            head = new Node(data);
            size++;
            return;
        }

        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(new Node(data));
        size++;
    }


	/**
	 * Finds the *first* node (from the head) with the passed data
	 * @param data
	 * @return
	 */
    public Node getNode(int data) {
        Node current = head;
        // TODO: This could create an infinite loop if there's a cycle...
        while (current != null) {
        	if (current.getData() == data) {
        		return current;
        	}
            current = current.getNext();
        }

        return null;
    }


	/**
	 * Prints the current SimpleLinkedList
	 */
	public void printList() {
		this.printList(size);
	}

	/**
	 * Prints the current SimpleLinkedList with the passed size
	 * @param printSize
	 */
	public void printList(int printSize) {
		System.out.println("Current list size: " + size);

		if (head == null) {
			System.out.println("List is empty!");
			return;
		}

		Node current = head;
		int currentSize = 0;
        while (current != null && (currentSize < printSize)) {
        	System.out.print(current.getData() + " ");
            current = current.getNext();
            currentSize++;
        }
        System.out.println();
	}
}
