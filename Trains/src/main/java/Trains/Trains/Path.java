package Trains.Trains;

public class Path {
	private int distance;
	private int stops;
	
	public int getDistance() {
		return distance;
	}
	public int getStops() {
		return stops;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public void setStops(int stops) {
		this.stops = stops;
	}
	public Path(int distance, int stops) {
		this.distance = distance;
		this.stops = stops;
	}
	public String toString() {
		return "(" + distance + ", " + stops +")";
	}
}
