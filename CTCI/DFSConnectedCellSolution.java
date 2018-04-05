package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem
 * @author Gusto
 */


import java.util.*;

import CTCI.BasicGraph.GraphNode;


/**
 * Consider a matrix with n rows and m columns, where each cell contains either a 0 or a 1 and any cell containing a 1 is called a filled cell.
 * Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally
 * In other words, cell [i][j] is connected to cells:
 *   [i - 1][j - 1], [i - 1][j], [i - 1][j + 1], [i][j - 1], [i][j + 1], [i + 1][j - 1], [i + 1][j], [i + 1][j + 1]
 *   provided that the location exists in the matrix for that [i][j].
 * If one or more filled cells are also connected, they form a region.
 * Note that each cell in a region is connected to at least one other cell in the region but is not necessarily directly connected to all the other cells in the region.
 *
 * Task:
 *   Given an (n X m) matrix, find and print the number of cells in the largest region in the matrix.
 *   Note that there may be more than one region in the matrix.
 *
 * Input Format:
 *   The first line contains an integer n denoting the number of rows in the matrix.
 *   The second line contains an integer m denoting the number of columns in the matrix.
 *   Each line i of the n subsequent lines contains m space-separated integers describing the respective values filling each row in the matrix.
 *
 * Constraints:
 *   0 <= n, m <= 10
 *
 * Output:
 *   Print the number of cells in the largest region in the given matrix.
 */
public class DFSConnectedCellSolution {

	public class GraphNode {
		private int id;
		private boolean filled;
		LinkedList<GraphNode> adjacent = new LinkedList<GraphNode>();

		/**
		 * CTOR
		 * @param id
		 * @param filled
		 */
		private GraphNode(int id, boolean filled) {
			this.id = id;
			this.filled = filled;
		}
	}

	public class MatrixGraph {
		// Mapping from the node id to the node itself (maintained when adding to the graph)
		private Map<Integer, GraphNode> nodeLookup = new HashMap<Integer, GraphNode>();


		private GraphNode getNode(int id) {
			return nodeLookup.get(id);
		}

		public int addNode(int nodeId, int value) {
			boolean filled = (value == 1) ? true : false;
			GraphNode newNode = new GraphNode(nodeId, filled);

			// Add the new node to the graph
			nodeLookup.put(nodeId, newNode);

			return nodeId;
		}

		public void addEdge(int source, int destination) {
			GraphNode src = getNode(source);
			GraphNode dest = getNode(destination);
			src.adjacent.add(dest);
		}
	}



	/**
	 * Determines the biggest filled region in the passed matrix
	 * @param matrix
	 * @return
	 */
    public static int getBiggestRegion(int[][] matrix) {
        int biggestRegion = 0;

        
        // Now determine each region of filled cells (1's) in the matrix
        
        
        // Now find the largest region (most number of filled cells)
        

        // Return the biggest region
    	return biggestRegion;
    }


    /**
	 * MAIN
	 * @param args
	 */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for (int grid_i = 0; grid_i < n; grid_i++) {
            for (int grid_j = 0; grid_j < m; grid_j++) {
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        in.close();
        System.out.println(getBiggestRegion(grid));
    }

	/**
	 *
	 ** TEST INPUT 1:
4
4
1 1 0 0
0 1 1 0
0 0 1 0
1 0 0 0
	 ** OUTPUT 1:
5
	 *
	 *
	 ** TEST INPUT 2:
?
	 ** OUTPUT 2:
?
	 *
	 */
}
