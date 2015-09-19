/**
 * ICS 311 Implementation Project 2
 * @author John Rasay
 * @School University OF Hawaii
 * @Class ICS 311
 * @Fall 2012
 *
 */
public class Graph_Test {
	public static void main(String[] args) {
	    if(args.length == 0)
	    {
	        System.out.println("Error, please enter path of the file.");
	        System.exit(0);
	    }
	    else {
	    	Graph g = new Graph();
	    	g.FileRead(args[0]);
	    	g.calcMinMaxAvg();
	    	g.stronglyConnectedComponents();
	    	g.printGraphStat();
	    	
	    	//Debugging: Check if the File was read in correctly
	    	//g.printIncidentEdge();
	    	//g.printVertices();
	    	//g.printEdges();
	    }

	}

}
