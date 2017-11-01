package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-is-binary-search-tree?h_r=next-challenge&h_v=zen
 * @author Gusto
 */



public class TreeNode {

	TreeNode left, right;
    int data;

    /**
     * CSTOR
     * @param data
     */
	public TreeNode(int data) {
		this.data = data;
	}


	/**
	 * Recursive BST insert
	 * @param value
	 */
    public void insert(int value) {
        if (value <= data) {
            if (left == null) {
                left = new TreeNode(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new TreeNode(value);
            } else {
                right.insert(value);
            }
        }
    }


    /**
     * Recursive BST contains (or find)
     * @param value
     * @return
     */
    public boolean contains(int value) {
        if (value == data) {
            return true;
        } else if (value < data) {
            if (left == null) {
                return false;
            } else {
                return left.contains(value);
            }
        } else {
            if (right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        }
    }


    /**
     * Recursive in-order traversal of a BST to print each node's data
     *   In-order: Visit left node, then root, then right nodes -> A-B-C
     */
    public void printInOrder() {
    	// Print left node (A)
        if (left != null) {
            left.printInOrder();
        }

        // Print root node (B)
        System.out.println(data);

        // Print right node (C)
        if (right != null) {
            right.printInOrder();
        }
    }


    /**
     * Recursive pre-order traversal of a BST to print each node's data
     *   Pre-order: Visit root, then left nodes, then right nodes -> B-A-C
     */
    public void printPreOrder() {
    	// Print root node (B)
    	System.out.println(data);

    	// Print left node (A)
        if (left != null) {
            left.printPreOrder();
        }

        // Print right node (C)
        if (right != null) {
            right.printPreOrder();
        }
    }


    /**
     * Recursive post-order traversal of a BST to print each node's data
     *   Post-order: Visit left nodes, then right nodes, then the root node last -> A-C-B
     */
    public void printPostOrder() {
    	// Print left node (A)
        if (left != null) {
            left.printPostOrder();
        }

        // Print right node (C)
        if (right != null) {
            right.printPostOrder();
        }

    	// Print root node (B)
    	System.out.println(data);
    }
}
