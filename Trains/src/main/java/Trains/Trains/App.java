package Trains.Trains;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class App 
{
	private ResourceBundle properties;
	private static App instance;
	
	private App() {
		
			//to loading graph resource from the resource bundle
			properties = ResourceBundle.getBundle("Trains.Trains.graphInfo");
		
	}
	 
	//singleton class creation which is provides only one instance of the class 
	public static App getInstance() {
		if (instance == null) {
			instance = new App();
		}
		return instance;
	}
	
    public static String getProperty(String name) {
		String s;
		
			s = getInstance().properties.getString(name);
		
		return s;
	}
	
	private static FileInputStream getFileInputStream(String name) throws FileNotFoundException {
		FileInputStream stream = null;
		
		stream = new FileInputStream(name);
		    	
		return stream;
	}
	
    public static void main( String[] args ) throws IOException
    {    	
    	
		String graphFilename    = args[0];
		String commandsFilename = args[1];
		
    	
        Graph graph = new Graph(getFileInputStream(graphFilename));
        Processes actions = new Processes(getFileInputStream(commandsFilename));
    	    
    	System.out.println(actions.execute(graph));
    
    	
    }
}

