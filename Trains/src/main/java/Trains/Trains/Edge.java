package Trains.Trains;

public class Edge {
	private Node destination;
	private int  distance;
	
	public Node getDestination() {
		return destination;
	}
	
	public int getDistance() {
		return distance;
	}
		
	public Edge(Node n, int d) {
		destination = n;
		distance = d;
	}
	
	public String toString() {
		return destination.getName() + ": " + distance;
	}
}
