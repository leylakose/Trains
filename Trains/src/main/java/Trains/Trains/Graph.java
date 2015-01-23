package Trains.Trains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Graph {
    
    Map<String, Node> nodeMap = new TreeMap<String, Node>();
    
    public Node[] getNodes() {
    	return nodeMap.values().toArray(new Node[0]);
    }
    
    
     //Look for a given node in this graph by name. If it doesn't exist, a new
     //node is created and added in the graph.
     
    public Node getNode(String name) {
    	Node node = nodeMap.get(name);
    	if (node == null) {
    		node = new Node(name);
    		nodeMap.put(name, node);
    	}
    	return node;
    }
    
	/*
	  reads a series of graph edges (connections between nodes) from
	  a given string of the form XYZ( X is source and Y is target and
	  Z is distance) For example "AB3".
	 */
	public void read(String str) {
		for (String s : str.split("[\\s,]")) {
			if (!s.isEmpty()) {
				// An edge  must be at least 3 characters
				
				String sourceNodeName = s.substring(0,1);
				String targetNodeName = s.substring(1,2);
				
				int distance;
					distance = Integer.parseInt(s.substring(2));
			
				Node source = getNode(sourceNodeName);
				Node target = getNode(targetNodeName);
				
				source.addEdge(new Edge(target, distance));
			}
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Map.Entry<String, Node> entry : nodeMap.entrySet()) {
			s.append(entry.getKey() + ":\n");
			for (Edge edge : entry.getValue().neighbours) {
				s.append("  -> " + edge + "\n");
			}
		}
		return s.toString();
	}
	
	
	 // Constructs a Graph read from an InputStream
	 
	public Graph(InputStream stream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String str;
		while((str = reader.readLine()) != null) {
			read(str);
		}
	}
	
	public Graph(String str) {
		read(str);
	}
}

