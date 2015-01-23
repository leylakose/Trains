package Trains.Trains;

import java.util.ArrayList;

// FindPath provides a tree search on a graph looking for all possible paths  
 
public class FindPath {
	private Condition runCond;
	private Condition filterCond;
	
	
	private ArrayList<Path> findPaths(Graph graph, Node current,  Node target, Path ancestor) {
		ArrayList<Path> paths = new ArrayList<Path>();

		// For all neighbours directly reachable from the current node...
		for (Edge edge: current.getNeighbours()) {
			Node destination = edge.getDestination();
			int distance = edge.getDistance();
			Path newPath = new Path(ancestor.getDistance() + distance, ancestor.getStops() + 1);
			
			// if the path reaching this neighbour means, this search branch should be cancel
			if (!runCond.situation(newPath)) {
				continue;
			}
			
			// if this neighbour is the target that we are looking for:
			if (destination.isEqual(target)) {
				// if no filter was specified or the filter selects this path,
				// then it is added to the current list of results
				 if (filterCond == null || filterCond.situation(newPath)) {
					paths.add(newPath);
				 }
				 
			}
			
			// Look recursively for all paths that can be found via this neighbour
			for (Path path: findPaths(graph, destination, target, newPath)) {
				paths.add(path);
			}
		}
		return paths;
	}
	
	public Path[] findAllPaths(Graph graph, 
			                   Node start, 
			                   Node target) {
		return findPaths(graph, start, target, new Path(0,0)).toArray(new Path[0]);
	}
	
	/*	 
	  runCond determines under what circumstances the search should be continue.
	  filterCond filter specifies which paths should be included in the result.	
	 */
	public FindPath(Condition runCond, Condition filterCond) {
		this.runCond = runCond;
		this.filterCond = filterCond;
		
	}
}

