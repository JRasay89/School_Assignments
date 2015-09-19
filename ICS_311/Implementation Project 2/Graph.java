import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/**
 * ICS 311 Implementation Project 2
 * @author John Rasay
 * @School University OF Hawaii
 * @Class ICS 311
 * @Fall 2012
 *
 */
public class Graph {
//Instance Variables-------------------------------------------------------------------------------------------------------------------
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	private String fileName;
	private boolean isDirGraph;
	
	//For Strongly Connected Components
	private ArrayList<Vertex> dfsCollection;
	private ArrayList<ArrayList<Vertex>> sccCollection;
	int time;
	
	//For calculating the Min, Avg and Max of the graph
	//Indegree min,avg and max
	private int minInDegree; 
	private double avgInDegree;
	private int maxInDegree;
	 //Outdegree min,avg and mx
	private int minOutDegree;
	private double avgOutDegree;
	private int maxOutDegree;
	
	private double avgUD;
	
//Contructor---------------------------------------------------------------------------------------------------------------------------
	public Graph() {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}
	
//Accessor------------------------------------------------------------------------------------------------------------------------------
		/**
		 * Method for getting the total number of vertices in the graph
		 * @return the total number of vertices in the graph
		 */
		public int numVertices() {
			return vertices.size();
		}
		
		/**
		 * Method for getting the total number of edges in the graph
		 * @return the total number of vertices in the graph
		 */
		public int numEdges() {
			return edges.size();
		}
		
		/**
		 * This method returns an iterator of the vertices
		 * @return an iterator of the vertices
		 */
		public Iterator<Vertex> vertices() {
			return vertices.iterator();
		}
		
		/**
		 * This method returns an iterator of the edges
		 * @return an iterator of the edges
		 */
		public Iterator<Edge> edges(){
			return edges.iterator();
		}
		
//Accessing the Undirected graph--------------------------------------------------------------------------------------------------------------------
		/**
		 * Returns the number of edges incident to the vertex
		 * @param v is the vertex
		 * @return the number of edges incident to the vertex
		 */
		public int degree (Vertex v) {
			return v.getDegree();
		}
		
		/**
		 * This method returns an iterator of the vertices adjacent to v
		 * @param v the vertex
		 * @return the vertices adjacent to v
		 */
		public Iterator<Vertex> adjacentVertices(Vertex v) {
			Iterator<Edge> itr = edges.iterator();
			ArrayList<Vertex> adjacentVertex = new ArrayList<Vertex>();
			while(itr.hasNext()) {
				Edge e = itr.next();
				Vertex[] endVertex = endVertices(e);
				if (endVertex[0] == v && endVertex[1] != v) {
					adjacentVertex.add(endVertex[1]);
				}
				else if (endVertex[1] == v && endVertex[0] != v) {
					adjacentVertex.add(endVertex[0]);
				}
			}
			return adjacentVertex.iterator();
		}
		
		/**
		 * This method returns an iterator of the edges incident to v
		 * @param v is the vertex
		 * @return an iterator of the edges incident to v
		 */
		public Iterator<Edge> incidentEdges(Vertex v) {
			return v.incidentEdges();
		}
		
		/**
		 * Returns an array containing the two end vertices of the given edge.
		 * @param e is the edge inputed.
		 * @return the array containing the two end vertices of the edge.
		 */
		public Vertex[] endVertices(Edge e) {
			return e.getEndVertices();
		}
		
		/**
		 * Given v is an end point of e
		 * Returns the end vertex of e opposite from v.
		 * Throws an InvalidEdgeException when v is not an endpoint of e.
		 * 
		 * @param v is the vertex
		 * @param e	is the edge
		 * @return the end vertex of the edge opposite to the given vertex. 
		 * @throws InvalidEdgeException if given vertex is not an end of vertex to the give edge.
		 */
		public Vertex opposite(Vertex v, Edge e) throws InvalidEdgeException {
			if (e.getOrigin() == v) {
				return e.getDestination();
			}
			else if (e.getDestination() == v) {
				return e.getOrigin();
			}
			else {
				throw new InvalidEdgeException("Vertex is not an endpoint of the edge.");
			}
		}
		
		/**
		 * Checks to see if two Vertex are adjacent
		 * @param v1 is the vertex
		 * @param v2 is the 2nd vertex
		 * @return true or false if they are adjacent or not respectively
		 */
		public boolean areAdjacent(Vertex v1, Vertex v2) {
			Iterator<Edge> itr = v1.incidentEdges();
			while(itr.hasNext()) {
				Vertex[] endVertex = endVertices(itr.next());
				if (endVertex[0] == v1 && endVertex[1] == v2 || endVertex[0] == v2 && endVertex[1] == v1) {
					return true;
				}
			}
			return false;
		}
		
//Accessing Directed Graphs------------------------------------------------------------------------------------------------------------------------------
		/**
		 * This method returns an iterator of the directed edges of the graph
		 * @return an iterator of the directed edges of the graph
		 */
		public java.util.Iterator<Edge> directedEdges() {
			ArrayList<Edge> directedEdge = new ArrayList<Edge>();
			Iterator<Edge> itr = edges.iterator();
			while(itr.hasNext()) {
				Edge e = itr.next();
				if (e.getDirection() == true) {
					directedEdge.add(e);
				}
			}
			return directedEdge.iterator();
		}
		
		/**
		 * This method returns an iterator of the undirected edges of the graph
		 * @return an iterator of the undirected edges of the graph
		 */
		public java.util.Iterator<Edge> undirectedEdges() {
			ArrayList<Edge> directedEdge = new ArrayList<Edge>();
			Iterator<Edge> itr = edges.iterator();
			while(itr.hasNext()) {
				Edge e = itr.next();
				if (e.getDirection() == false) {
					directedEdge.add(e);
				}
			}
			return directedEdge.iterator();
		}
		
		
		/**
		 * Returns the number of directed edges (arcs) incoming to v
		 * @param v is the vertex
		 * @return the number of directed edges (arcs) incoming to v
		 */
		public int inDegree(Vertex v) {
			ArrayList<Vertex> inVertex = new ArrayList<Vertex>();
			Iterator<Edge> itr = v.incidentEdges();
			while(itr.hasNext()) {
				Edge e = itr.next();
				if (e.getDestination() == v && e.getDirection() == true) {
					inVertex.add(e.getOrigin());
				}
			}
			return inVertex.size();
		}
		
		/**
		 * This method returns the number of directed edges (arcs) outgoing from v
		 * @param v is the vertex
		 * @return the number of directed edges (arcs) outgoing from v
		 */
		public int outDegree(Vertex v) {
			ArrayList<Vertex> outVertex = new ArrayList<Vertex>();
			Iterator<Edge> itr = v.incidentEdges();
			while(itr.hasNext()) {
				Edge e = itr.next();
				if (e.getOrigin() == v && e.getDirection() == true) {
					outVertex.add(e.getDestination());
				}
			}
			return outVertex.size();
		}
		
		/**
		 * This method returns an iterator over the vertices adjacent to v by incoming edges.
		 * @param v is the vertex
		 * @return  an iterator over the vertices adjacent to v by incoming edges.
		 */
		public java.util.Iterator inAdjacentVertices(Vertex v) {
			ArrayList<Vertex> inVertex = new ArrayList<Vertex>();
			Iterator<Edge> itr = v.incidentEdges();
			while(itr.hasNext()) {
				Edge e = itr.next();
				if (e.getDestination() == v && e.getDirection() == true) {
					inVertex.add(e.getOrigin());
				}
			}
			return inVertex.iterator();
		}
		
		/**
		 * This method returns an iterator over the vertices adjacent to v by outgoing edges.
		 * @param v
		 * @return an iterator over the vertices adjacent to v by outgoing edges.
		 */
		public java.util.Iterator outAdjacentVertices(Vertex v) {
			ArrayList<Vertex> outVertex = new ArrayList<Vertex>();
			Iterator<Edge> itr = v.incidentEdges();
			while(itr.hasNext()) {
				Edge e = itr.next();
				if (e.getOrigin() == v && e.getDirection() == true) {
					outVertex.add(e.getDestination());
				}
			}
			return outVertex.iterator();
		}
		
		/**
		 * This method returns an iterator over the incoming edges of v.
		 * @param v
		 * @return an iterator over the incoming edges of v.
		 */
		public Iterator<Edge> inIncidentEdges(Vertex v) {
			ArrayList<Edge> inEdge = new ArrayList<Edge>();
			Iterator<Edge> itr = v.incidentEdges();
			while(itr.hasNext()) {
				Edge e = itr.next();
				if (e.getDestination() == v && e.getDirection() == true) {
					inEdge.add(e);
				}
			}
			return inEdge.iterator();
		}
		
		/**
		 * This method returns an iterator over the outgoing edges of v.
		 * @param v
		 * @return an iterator over the outgoing edges of v.
		 */
		public Iterator<Edge> outIncidentEdges(Vertex v) {
			ArrayList<Edge> outEdge = new ArrayList<Edge>();
			Iterator<Edge> itr = v.incidentEdges();
			while(itr.hasNext()) {
				Edge e = itr.next();
				if (e.getOrigin() == v && e.getDirection() == true) {
					outEdge.add(e);
				}
			}
			return outEdge.iterator();
		}
		
		/**
		 * Returns the destination vertex of the edge
		 * @param e is the edge
		 * @return the destination vertex of the edge
		 */
		public Vertex destination(Edge e) {
			return e.getDestination();
		}
		
		/**
		 * Find the origin vertex of the directed edge
		 * @param e is the edge
		 * @return the origin vertex of the directed edge
		 */
		public Vertex origin(Edge e) throws InvalidEdgeException {
			if(e.getDirection() == false) {
				throw new InvalidEdgeException("The graph is undirected");
			}
			else {
				return e.getOrigin();
			}
		}
		
		/**
		 * Check if the edge is directed or undirected
		 * @param e is the edge
		 * @return true or false if the edge is directed or undirected respectively
		 */
		public boolean isDirected(Edge e) {
			return e.getDirection();
		}
		
		
//Mutators (Undirected & Directed)----------------------------------------------------------------------------------------
		/**
		 * Inserts a new undirected edge
		 * @param u is one of the end vertices of the edge
		 * @param v is one of the end vertices of the edge
		 * @param info about the edge ex. weight
		 * @return the edge
		 */
		public Edge insertEdge(Vertex u, Vertex v, Object info) {
			Edge e = new Edge(u,v,info,false);
			edges.add(e);
			return e;
		}
		
		/**
		 * Inserts a new vertex
		 * @param info about the vertex
		 * @return the vertex
		 */
		public Vertex insertVertex(Object info) {
			Vertex v = new Vertex(info);
			vertices.add(v);
			return v;
		}
		
		/**
		 * Inserts a new directed edge
		 * @param u	is the origin of the edge
		 * @param v	is the destination of the edge
		 * @param info	about the edge ex. weight
		 * @return the edge
		 */
		public Edge insertDirectedEdge(Vertex u, Vertex v, Object info) {
			Edge e = new Edge(u,v,info,true);
			edges.add(e);
			return e;
		}
		
		/**
		 * Removes the vertex and all its incident edges.
		 * @param v is the vertex to be remove.
		 * @return the vertex that is being remove.
		 */
		public Object removeVertex(Vertex v) {
			vertices.remove(v);
			Iterator<Edge> itr = edges.iterator();
			while (itr.hasNext()) {
				Edge e = itr.next();
				if (e.getOrigin() == v || e.getDestination() == v) { 			//Deleting adjacent vertices edges thats incident to v
					if (e.getOrigin() != v) {									//If v is the destination remove the origin's edges incident to v
						Iterator<Edge> itr2 = e.getOrigin().incidentEdges();
						while (itr2.hasNext()) {
							Edge ee = itr2.next();
							if (ee.getOrigin() == v || ee.getDestination() == v) {
								e.getOrigin().removeIncidentEdge(ee);
							}
							
						}
					}
					else if (e.getDestination() != v) {								//If v is the origin remove the destination's edges incident to v
						Iterator<Edge> itr2 = e.getDestination().incidentEdges();
						while (itr2.hasNext()) {
							Edge ee = itr2.next();
							if (ee.getOrigin() == v || ee.getDestination() == v) {
								e.getOrigin().removeIncidentEdge(ee);
							}
						}
					}
					edges.remove(e);
				}
			}
			return v;
		}
		
		/**
		 * Removes the edge.
		 * @param e is the edge to be remove.
		 * @return the edge being remove.
		 */
		public Object removeEdge(Edge e) {
			edges.remove(e);
			Vertex[] eV = e.getEndVertices();
			eV[0].removeIncidentEdge(e);		//Remove the edge in the Origin or Destinations incidence collection
			eV[1].removeIncidentEdge(e);
			return e;
		}
		
		/**
		 * This sets the direction of the edge away from the vertex. Makes an undirected edge directed.
		 * @param e is the edge.
		 * @param newOrigin	is the origin of the edge.
		 * @throws InvalidEdgeException if the vertex is not one of the edge's endpoint.
		 */
		public void setDirectionFrom(Edge e, Vertex newOrigin) throws InvalidEdgeException {
			Vertex[] endVertex = e.getEndVertices();
			if (endVertex[0] != newOrigin || endVertex[1] != newOrigin) {
				throw new InvalidEdgeException("The vertex is not an endpoint of the edge.");
			}
			else if (endVertex[0] == newOrigin) {
				e.setDirection(true); //Make the undirected edge directed
			}
			else if (endVertex[1] == newOrigin) {
				e.setDestination(endVertex[0]); 		//Switch Origin and Destination of Edge
				e.setOrigin(endVertex[1]);
				e.setDirection(true);
			}
		}
		
		/**
		 * This sets the direction of the edge towards the vertex. Makes an undirected edge directed.
		 * @param e is the edge
		 * @param newDestination	is the destination of the edge.
		 * @throws InvalidEdgeException if the vertex is not one of the edge's endpoint.
		 */
		public void setDirectionTo(Edge e, Vertex newDestination) throws InvalidEdgeException {
			Vertex[] endVertex = e.getEndVertices();
			if (endVertex[0] != newDestination || endVertex[1] != newDestination) {
				throw new InvalidEdgeException("The vertex is not an endpoint of the edge.");
			}
			else if (endVertex[0] == newDestination) {
				e.setDestination(endVertex[0]);
				e.setOrigin(endVertex[1]);		//Switch Origin and Destination of Edge
				e.setDirection(true);
			}
			else if (endVertex[1] == newDestination) {
				e.setDirection(true);
			}
		}
		
		/**
		 * This sets the edge to be undirected.
		 * @param e is the parameter which is either true or false.
		 */
		public void makeUndirected(Edge e) {
			e.setDirection(false);
		}
		
		/**
		 * Reverse the direction of the edge.
		 * @param e is the edge.
		 * @throws InvalidEdgeException if the edge is undirected.
		 */
		public void reverseDirection(Edge e) throws InvalidEdgeException {
			if(e.getDirection() == true) {
				Vertex temp = e.getOrigin();
				e.setOrigin(e.getDestination());
				e.setDestination(temp);
			}
			else {
				throw new InvalidEdgeException("Edge is undirected ");
			}
		}
		
//Filling in the graph-------------------------------------------------------------------------------------------------------------------------------------------------------------
		/**
		 * Reads a file to fill in the graph 
		 * @param fileName is the file
		 */
		public void FileRead(String fileName) {
			this.fileName = fileName;
			try {
				File myFile = new File(fileName);
				FileReader fileReader = new FileReader(myFile);
				
				BufferedReader reader = new BufferedReader(fileReader);
				
				String line = null;
				String read_Mode = "";
				int i = 0;
				
				while ((line = reader.readLine()) != null) {
					String[] nextLine = line.split(" ");
					i++;
					if (nextLine[0].equals("*Vertices")) {
						read_Mode = "vertices";
					}
					else if (nextLine[0].equals("*Arcs")) {
						read_Mode = "arcs";
					}
					else {
						if (read_Mode.equals("vertices")) {
							Vertex v = new Vertex(nextLine[0]);
							v.setId(i-1);
							vertices.add(v);
						}
						else if (read_Mode.equals("arcs")) {
							Vertex o = vertices.get(Integer.parseInt(nextLine[0])-1);
							Vertex d = vertices.get(Integer.parseInt(nextLine[1])-1);
							boolean isDir = false;
							if (nextLine.length == 3) {
								isDir = true;
								isDirGraph = isDir;
							}

							Edge e = new Edge(o, d, null, isDir);
							edges.add(e);
						}
					}
				}
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		//Prints information about the graph
		public void printGraphStat() {
			System.out.println("------------------------------------------------------------");
			System.out.println("Graph " + fileName);
			System.out.println("------------------------------------------------------------");
			System.out.println("|V| = " + vertices.size());
			System.out.println("|E| = " + edges.size());
			System.out.println();
			if (isDirGraph == false) {
				System.out.println("The Graph is Undirected.");
				System.out.printf("Degree distribution: %15s %n","average");
				System.out.printf("Degree(v):%18s %2.3f %n%n","",avgUD);
				System.out.println("Strongly Connected Components Does Not Apply.");
			}
			else {
				System.out.printf("Degree distribution: %10s %15s %15s \n", "minium","average","max");					//Printing out the Minimum, Maximum and Average InDegree and OutDegree
				System.out.printf("inDegree(v): %13d %13s%2.3f %16d%n", minInDegree, "", avgInDegree, maxInDegree);
				System.out.printf("inDegree(v): %13d %13s%2.3f %16d %n", minOutDegree, "", avgOutDegree, maxOutDegree);
				//Strongly Connected Components
				System.out.println();
				System.out.println("------------------------------------------------------------");
				System.out.println("Number of Strongly Connected Components: " + sccCollection.size());
				System.out.println("------------------------------------------------------------");
				System.out.println("Top 20 SCC by size:");
				System.out.printf("%s %10s %10s %30s %n", "ID",	"Root",  "Size",  "Highest Degree Vertices");
				Collections.sort(sccCollection, new sizeComparable());
				Iterator<ArrayList<Vertex>> itr = sccCollection.iterator();
				for (int i = 0; itr.hasNext() && i < 20; i++) {
					ArrayList<Vertex> aV = itr.next();
					Vertex v = aV.get(0);
					Vertex r = null;
					String r_id = "";
					Iterator<Vertex> itr2 = aV.iterator();
					while (itr2.hasNext()) {
						Vertex u = itr2.next();
						if (u.getAnnotation("root").equals(true)) {
							r = u;
						}
					}
					if (r != null) {
						r_id = String.valueOf(r.getId());
					}
					System.out.printf("%d %10s %10d %s%d%s %d %n", i+1, r_id ,aV.size(),   "        deg(",v.getId(),") = " , v.getDegree());
				}
			}
		}
		
		
		/**
		 * Calculate the Minimum, Maximum and Average of the inDegree and OutDegree for a graph.
		 */
		public void calcMinMaxAvg() {
			Iterator<Vertex> itr = vertices.iterator();
			//Variables for calculating the min,max and avg indegree
			int minInD = 0;
			int maxInD = 0;
			double avgInD = 0;
			double sumInD = 0;
			//Variables for calculating the min,max and avg outdegree
			int minOutD = 0;
			int maxOutD = 0;
			double avgOutD = 0;
			double sumOutD = 0;
			
			double avgUdirD = 0;
			if (isDirGraph == false) {
				while (itr.hasNext()) {
					Vertex v = itr.next();
					avgUdirD += this.degree(v);
				}
				
				
			}
			else {
				while(itr.hasNext()) {
					Vertex v = itr.next();
					if (this.inDegree(v) <= minInD) {
						minInD = this.inDegree(v);
					}
				
					if (this.inDegree(v) >= maxInD) {
						maxInD = this.inDegree(v);
					}
				
					if (this.outDegree(v) <= minOutD) {
						minOutD = this.outDegree(v);
					}
				
					if (this.outDegree(v) >= maxOutD) {
						maxOutD = this.outDegree(v);
					}
				
					sumInD += this.inDegree(v);
					sumOutD += this.outDegree(v);
				
				}
			}
			
			minInDegree = minInD;
			maxInDegree = maxInD;
			avgInDegree = round(sumInD/vertices.size());
			
			minOutDegree = minOutD;
			maxOutDegree = maxOutD;
			avgOutDegree = round(sumOutD/vertices.size());
			
			avgUD = round(avgUdirD/vertices.size());
		}
		
		/**
		 * This method rounds the double to three decimal places.
		 * @param num is the number to be rounded.
		 * @return the rounded the number.
		 */
		private static double round(double num) {
			double result = num * 1000;
			result = Math.round(result);
			result = result / 1000;
			return result;
			}	
		
//Strongly Connected Components---------------------------------------------------------------------------------------------------------------------------------------
		//DFS
		private void dfs(Stack<Vertex> s) {
			dfsCollection = new ArrayList<Vertex>();
			sccCollection = new ArrayList<ArrayList<Vertex>>();
			
			Iterator<Vertex> itr = vertices.iterator();
			while (itr.hasNext()) {
				Vertex u = itr.next();
				u.setAnnotation("color", "white");
				u.setAnnotation("pi",null);
				u.setAnnotation("root", false);
			}
			time = 0;
			if (s == null) {
				itr = vertices.iterator();
				while (itr.hasNext()) {
					Vertex u = itr.next();
					if (u.getAnnotation("color").equals("white")) {
						dfsCollection.addAll(dfsVisit(u));
					}
							
				}
			}
			else {
				while (s.isEmpty() != true) {
					Vertex u = s.pop();
					u.setAnnotation("root", true);
					ArrayList<Vertex> vertexCollection = dfsVisit(u);
					Collections.sort(vertexCollection, new myComparable());
					sccCollection.add(vertexCollection);
					s.removeAll(vertexCollection);
				}
			}
		}
		//DFSVISIT
		private ArrayList<Vertex> dfsVisit(Vertex u) {
			ArrayList<Vertex> vertexCollection = new ArrayList<Vertex>();
			time = time + 1;
			u.setAnnotation("d", time);
			u.setAnnotation("color","gray");
			Iterator<Vertex> itr = this.outAdjacentVertices(u);
			while (itr.hasNext()) {
				Vertex v = itr.next();
				if (v.getAnnotation("color").equals("white")) {
					v.setAnnotation("pi", u);
					vertexCollection.addAll(dfsVisit(v));
				}
			}
			u.setAnnotation("color", "black");
			time = time + 1;
			u.setAnnotation("f", time);
			vertexCollection.add(u);
			return vertexCollection;
		}
		//Compute the SCC of the graph
		public ArrayList<ArrayList<Vertex>> stronglyConnectedComponents() {
			dfs(null);
			Stack<Vertex> s = new Stack<Vertex>();
			Iterator<Vertex> itr = dfsCollection.iterator();
			while (itr.hasNext()) {
				Vertex v = itr.next();
				s.add(v);
			}
			Iterator<Edge> itr2 = edges.iterator();
			while (itr2.hasNext()) {
				Edge e = itr2.next();
				e.reverseDirection();
			}
			dfs(s);

			return sccCollection;
		}
		
//Inner Classes------------------------------------------------------------------------------------------------------------------------------------------------------
		//Sorts by degree
		public class myComparable implements Comparator<Vertex> {
			@Override
			public int compare(Vertex v, Vertex u) {
				if (v.getDegree() > u.getDegree()) {
					return -1;
				}
			    else if (v.getDegree() < u.getDegree()) {
			    	return 1;
			    }
				
			    return 0;
			}
		}
		//Sorts by number of vertices
		public class sizeComparable implements Comparator<ArrayList<Vertex>> {
			@Override
			public int compare(ArrayList<Vertex> va1, ArrayList<Vertex> va2) {
				if (va1.size() > va2.size()) {
					return -1;
				}
				else if(va1.size() < va2.size()) {
					return 1;
				}
				return 0;
			}
		}
		
//DEBUGGING-----------------------------------------------------------------------------------------------------------------------------------------------------------
		//Prints the vertices.
		public void printVertices() {
			Iterator<Vertex> itr = vertices.iterator();
			System.out.println("Total Number Of Vertex: " + vertices.size());
			while (itr.hasNext()) {
				System.out.println("Vertex: " + itr.next().getId() + " ");

			}
			System.out.println();
		}
		
		//Prints the edges.
		public void printEdges() {
			Iterator<Edge> itr = edges.iterator();
			System.out.println("Total Number Of Edges: " + edges.size());
			while (itr.hasNext()) {
				Edge e = itr.next();
				System.out.println("Edge: " +"("+e.getOrigin().getId() +" , " + e.getDestination().getId()+")" + "  Directed?: " + e.getDirection());
			}
			System.out.println();
		}
		
		//Prints the incident Edges for each vertex.
		public void printIncidentEdge() {
			System.out.println();
			System.out.println("--------------------------------------------------------------");
			System.out.println(":Incident Edges For Each Vertex:");
			System.out.println("--------------------------------------------------------------");
			Iterator<Vertex> itr = vertices.iterator();
			while(itr.hasNext()) {
				Vertex v = itr.next();
				System.out.println("Vertex: " + v.getId());
				Iterator<Edge> itr2 = v.incidentEdges();
				while(itr2.hasNext()) {
					Edge e = itr2.next();
					System.out.println("Edge: " +"("+e.getOrigin().getId() +" , " + e.getDestination().getId()+")" + "  Directed?: " + e.getDirection());
				}
				System.out.println();
			}
			
		}
		
}
