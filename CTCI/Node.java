package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-linked-list-cycle/problem
 * @author Gusto
 */



public class Node {

	private int data;
	private Node next = null;

	/**
	 * CTOR
	 * @param data
	 */
    public Node(int data) {
    	this.data = data;
    }

    public int getData() { return data; }
    public Node getNext() { return next; }
    public void setNext(Node next) { this.next = next; }
}
