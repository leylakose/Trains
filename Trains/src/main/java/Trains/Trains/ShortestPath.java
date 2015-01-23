package Trains.Trains;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ShortestPath extends Process {
	
	public static int Dijkstra(Graph graph, Node start, Node target) {
		Set<Node> unsettled = new HashSet<Node>();
		Set<Node> settled = new HashSet<Node>();
		
		Node[] nodes = graph.getNodes();
		Map<String, Integer> distance = new TreeMap<String, Integer>();

		// Start by setting the distances from all nodes to the start node to infinity
		for (Node node: nodes) {
			distance.put(node.getName(), Integer.MAX_VALUE);
		}
		
		// move start node to the list of unvisited nodes and set the 
		// distance to 0 (the distance from the start node to itself)
		unsettled.add(start);
		distance.put(start.getName(), 0);
		
		// while the list of unvisited nodes is not empty...
		while(!unsettled.isEmpty()) {
			
			// find the node in the unvisited list with the nearest node
			Node nearest=null;
			int minDistance = Integer.MAX_VALUE;
			for(Node n : unsettled) {
				Integer dist = distance.get(n.getName());
				if ( dist < minDistance) {
					nearest = n;
					minDistance = dist ;
				}
			}
			
			// move the nearest node to the list of visited nodes
			unsettled.remove(nearest);
			settled.add(nearest);
			
			// for all neighbours of the current node...
			for (Edge edge : nearest.getNeighbours()) {
				
				if (!settled.contains(edge.getDestination())) {
					// find the ones for which we find a better (shorter) path
					// to the source than the one we had previously calculated for it...
					int newDistance = distance.get(nearest.getName()) + edge.getDistance();
					Integer dist = distance.get(edge.getDestination().getName());
					if (newDistance < dist) {
						// save the new shortest distance for this neighbour
						// and move it to the list of unvisited nodes.
						distance.put(edge.getDestination().getName(), newDistance);
						unsettled.add(edge.getDestination());
					}
				}
			}
			
			// if the target node has already been visited, we can stop searching
			if (settled.contains(target)) {
				break;
			}
		}
		// returns the shortest distance recorded from the target node to the start node
		return distance.get(target.getName());
	}

	public String execute(Graph graph) {
		String[] parameters = getParameters();
		
		

		String nodesSpec = parameters[1];
		Node[] nodes     = getNodes(graph, nodesSpec);
	
		Node start = nodes[0];
		Node target = nodes[1];
		
		int shortestDistance = Integer.MAX_VALUE;
		
		// I didn't use the FindPath class for this because it implicates	 
		// selecting an arbitrary maximum number of stops after the search for the
		// shortest path should be given up. So, it is inefficient. Therefore I
		// decided to use the Dijkstra algorithm

		if (start.isEqual(target)) {
			// The algorithm finds the shortest path between two nodes. When the start and end nodes are
			// the same, the distance will be 0. The problem requests
			//  that in this case a different path be found instead
			// of just returning the obvious answer.
			for (Edge edge : start.getNeighbours()) {
				int distance = Dijkstra(graph, edge.getDestination(), target);
				if (distance != Integer.MAX_VALUE) {
					// a path has been found from a neighbour of the start node
					// to the start node. Total distance will include
					// the distance from the start node to this neighbour.
					distance += edge.getDistance();
					if (distance < shortestDistance) {
						// a new shortest distance has been found
						shortestDistance = distance;
					}
				}
			}
		} else {
			shortestDistance = Dijkstra(graph, nodes[0], nodes[1]);
		}
		
		if (shortestDistance == Integer.MAX_VALUE) {
			return App.getProperty("no_route");
		}
		return String.valueOf(shortestDistance);
	}
}

