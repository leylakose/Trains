package Trains.Trains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Processes {
	private List<Process> ListOfProcess;
	private ProcessList Prcs;
	
	/*
	  Reads a process and  puts it on the process list for execution later.
	 
	 */
	public void read(String str) {
		if (!str.isEmpty()) {
			String words[] = str.split("\\s");
			
			String name = words[0];
			Process action = Prcs.createProcess(name);
			
			
			action.setParameters(words);
			
			ListOfProcess.add(action);
		}
	}
	
	public Processes(InputStream stream) throws IOException {
		this();
		
		// Read actions from input stream 
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String str;
		while((str = reader.readLine()) != null) {
			read(str);
		}
	}
	
	public Processes() {
		Prcs = new ProcessList();
		ListOfProcess   = new ArrayList<Process>();
	}

	// Executes this process on the given graph 
	public String execute(Graph graph) {
		StringBuilder s = new StringBuilder();//StringBuider can be modified

		for (Process action : ListOfProcess) {
			s.append(action + " => " + action.execute(graph) + "\n");
		}
		
		return s.toString();
	}
}



