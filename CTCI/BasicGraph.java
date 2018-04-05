package CTCI;


import java.util.*;


public class BasicGraph {
    // Mapping from the node id to the node itself (maintained when adding to the graph)
	private HashMap<Integer, GraphNode> nodeLookup = new HashMap<Integer, GraphNode>();

	public static class GraphNode {
		private int id;
		LinkedList<GraphNode> adjacent = new LinkedList<GraphNode>();

		/**
		 * CTOR
		 * @param id
		 */
		private GraphNode(int id) {
			this.id = id;
		}
	}


	private GraphNode getNode(int id) {
		return nodeLookup.get(id);
	}

	// TODO: Need to implement an addNode method...

    public void addEdge(int source, int destination) {
    	GraphNode src = getNode(source);
    	GraphNode dest = getNode(destination);
    	src.adjacent.add(dest);
    }


    public boolean hasPathDFS(int source, int destination) {
    	GraphNode src = getNode(source);
    	GraphNode dest = getNode(destination);
    	// Replacement/alternative to having a hasVisited flag
    	HashSet<Integer> visited = new HashSet<Integer>();
    	return hasPathDFS(src, dest, visited);
    }

    private boolean hasPathDFS(GraphNode source, GraphNode destination, HashSet<Integer> visited) {
    	// In this case there's no path to the destination, so return false
    	if (visited.contains(source.id)) {
    		return false;
    	}

    	// Add that we visited this current node
    	visited.add(source.id);

    	// Found path to destination
    	if (source == destination) {
    		return true;
    	}

    	// Otherwise, check all children for a path to the destination node
    	for (GraphNode child : source.adjacent) {
    		if (hasPathDFS(child, destination, visited)) {
    			return true;
    		}
    	}

    	// No path to destination
    	return false;
    }


    public boolean hasPathBFS(int source, int destination) {
    	return hasPathBFS(getNode(source), getNode(destination));
    }

    private boolean hasPathBFS(GraphNode source, GraphNode destination) {
    	LinkedList<GraphNode> nextToVisit = new LinkedList<GraphNode>();
    	// Replacement/alternative to having a hasVisited flag
    	HashSet<Integer> visited = new HashSet<Integer>();

    	// Add source node to nextToVisit list
    	nextToVisit.add(source);

    	while (!nextToVisit.isEmpty()) {
    		GraphNode currentNode = nextToVisit.remove();

    		// Found path to destination
    		if (currentNode == destination) {
        		return true;
        	}

    		// Go to the next node if we already visited
    		if (visited.contains(currentNode.id)) {
    			continue;
    		}

    		// Add that we visited this current node
    		visited.add(currentNode.id);

    		// Otherwise, Queue all adjacent children nodes for this current node
        	for (GraphNode child : currentNode.adjacent) {
        		nextToVisit.add(child);
        	}
    	}

    	// No path to destination
    	return false;
    }

}
