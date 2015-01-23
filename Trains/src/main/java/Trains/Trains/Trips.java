package Trains.Trains;

public class Trips extends Process {
	
	public String execute(Graph graph) {
		String[] parameters = getParameters();

		// second & third parameter specify the condition
		// fourth parameter is the node's specify
		
		String condition = parameters[1];
		String entity    = parameters[2];
		String nodesSpec = parameters[3];
		
		String operator  = condition.substring(0,1);
		int value        = Integer.parseInt(condition.substring(1));
		
		Node[] nodes     = getNodes(graph, nodesSpec);
		
		Node start = nodes[0];
		Node target = nodes[1];
		Condition filterCond = new Condition(operator, value, entity);
		Condition runCond = filterCond;
		
		// when the filter condition is to find all paths which is match
		// a given value, the test runs as long as the number of stops
		// of the distance is still less than the value we are searching for
		if (operator.equals("=")) {
			runCond = new Condition("<", value + 1, entity);
		}
		
		Path[] paths = new FindPath(runCond, filterCond).findAllPaths(graph, start, target);
		
		return String.valueOf(paths.length);
	}
}
