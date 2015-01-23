package Trains.Trains;

import java.util.Map;
import java.util.TreeMap;

  //creating Processes based on a command

public class ProcessList {
	private Map<String, Class<?> > processMap;
	
	public Process createProcess(String s)  {
		Class<?> c = processMap.get(s);
		Process action = null;

		if (c != null) {
				try {
					
						action = (Process) c.newInstance();
				} catch (Exception e) {
					System.err.println("createProcess error!!");
				}

				
		}		

		return action;
	}
	
	public ProcessList() {
		processMap = new TreeMap<String, Class<?> >();
		
		processMap.put("distance:", Distance.class);
		processMap.put("trips:", Trips.class);
		processMap.put("shortest:", ShortestPath.class);
	}
}