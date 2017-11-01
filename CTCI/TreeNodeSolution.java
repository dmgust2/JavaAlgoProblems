package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-is-binary-search-tree?h_r=next-challenge&h_v=zen
 * @author Gusto
 */


import java.util.Arrays;
import java.util.Scanner;



public class TreeNodeSolution {

	/** The MAX allowed tree data integer value is 10^4
	 * 10^0 = 1
	 * 10^1 = 10
	 * 10^2 = 100
	 * 10^4 = 10000
	 */
	private final static int MAX_INTEGER = 10000;


    /**
     * Create a BST using the passed sorted integer array
     * @param sortedTreeData
     */
    public static TreeNode createBST(int[] sortedTreeData) {
    	// Check if tree data array is empty
    	if (sortedTreeData.length == 0) {
    		return null;
    	}

    	// DEBUG
    	System.out.println("Currently creating BST with data: " + Arrays.toString(sortedTreeData));

    	// First find the middle element to create the correct root node
    	int middleIndex = (int)Math.floorDiv(sortedTreeData.length, 2);
    	// Debug
    	System.out.println("Mid index found: " + middleIndex);
    	TreeNode root = new TreeNode(sortedTreeData[middleIndex]);

    	// Recursively create the BST children nodes
    	//    If there's only 1 number, then we just create the root node and return
    	if (sortedTreeData.length > 1) {
            // Pass array items start -> (mid - 1)
    		root.left = createBST(Arrays.copyOfRange(sortedTreeData, 0, middleIndex));
        	// Pass array items (mid + 1) -> end
        	root.right = createBST(Arrays.copyOfRange(sortedTreeData, middleIndex + 1, sortedTreeData.length));
    	}

    	return root;
    }


    /**
     * Check BST helper
     * @param root
     * @return
     */
	public static boolean checkBST(TreeNode root) {
	    return checkBST(root, 0, MAX_INTEGER);
	}


	/**
	 * Check if passed tree root is a BST
	 * Notes: The data value of every node in a node's left subtree is less than the data value of that node.
	 *   The data value of every node in a node's right subtree is greater than the data value of that node.
	 *   Data constraints: 0 < data <= 10^4
	 *   Note: We do not consider a binary tree to be a binary search tree if it contains duplicate values.
	 * @param node
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean checkBST(TreeNode node, int min, int max) {
	    if (node == null) {
	    	return true;
	    }

	    return  min < node.data &&
	    		node.data < max &&
	            checkBST(node.left, min, node.data) &&
	            checkBST(node.right, node.data, max);
	}


	/**
	 * Read user tree data input in from the CLI/Console
	 * @return
	 */
	public static int[] readTreeDataFromCommandLine() {
		Scanner in = new Scanner(System.in);

	    // Get the space separated tree data integers e.g. 1 2 3 4 5 6 7
	    System.out.println("Enter the tree data as integers separated by spaces: ");
	    String inputString = in.nextLine();
	    in.close();
	    String[] strArray = inputString.split(" ");
	    int[] treeDataArray = new int[strArray.length];
	    for (int i = 0; i < strArray.length; i++)
	    {
	        // TODO: Add error handling for non integers...
	    	// TODO: Add error handling for a non-sorted array (or simply sort)?
	    	treeDataArray[i] = Integer.parseInt(strArray[i]);
	    }

	    return treeDataArray;
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		int[] treeData = readTreeDataFromCommandLine();
		// DEBUG
        //System.out.println("User input tree data array: " + Arrays.toString(treeData));

        // Create a BST from test input data
        TreeNode root = createBST(treeData);

        // Print the created BST
       /* System.out.println("Printing user input created BST in-order:");
        root.printInOrder();
        System.out.println("Printing user input created BST pre-order:");
        root.printPreOrder();
        System.out.println("Printing user input created BST post-order:");
        root.printPostOrder();*/

        // Now check if the tree is in fact a BST
        boolean isBST = checkBST(root);
        String output = (isBST == true) ? "Yes" : "No";
        System.out.println("Is this tree a BST: " + output);
	 }

}
