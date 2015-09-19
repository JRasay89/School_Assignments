import java.util.HashMap;
/**
 * ICS 311 Implementation Project 2
 * @author John Rasay
 * @School University OF Hawaii
 * @Class ICS 311
 * @Fall 2012
 *
 */
public class Edge {
	
	//Instance Variables
		private Vertex origin;
		private Vertex destination;
		private Object info;
		private boolean isDirected;
		private HashMap<Object, Object> annotations;
	
	//The constructors
		public Edge(Vertex o, Vertex d, Object info, boolean isDir) {
			origin = o;
			destination = d;
			o.insertIncidenceCollection(this);
			d.insertIncidenceCollection(this);
			this.info = info;
			isDirected = isDir;
			annotations = new HashMap<Object, Object>();
		}
	
	//Getter methods
		/**
		 * Gets the origin vertex of the edge
		 * @return the origin vertex of the edge
		 */
		public Vertex getOrigin() {
			return origin;
		}
		/**
		 * Gets the destination vertex of the edge
		 * @return the destination vertex of the edge
		 */
		public Vertex getDestination() {
			return destination;
		}
		
		/**
		 * See if the edge is directed or undirected
		 * @return
		 */
		public boolean getDirection(){
			return isDirected;
		}
		
		/**
		 * Gets the end vertices of the edge
		 * @return an array containing the end vertices of the edge
		 */
		public Vertex[] getEndVertices() {
			Vertex[] endVertices = new Vertex[2];
			endVertices[0] = origin;
			endVertices[1] = destination;
			return endVertices;
		}
		
		//Setter methods
		
		/**
		 * Set the the edge to be a directed edge or undirected edge.
		 * @param d is the parameter which is either true or false.
		 */
		public void setDirection(boolean d) {
			isDirected = d;
		}
		
		/**
		 * Sets the Origin of the edge
		 * @param newOrigin is the origin of the edge
		 */
		public void setOrigin(Vertex newOrigin) {
			origin = newOrigin;
		}
		
		/**
		 * Sets the Destination of the edge
		 * @param newDestination is the destination of the edge
		 */
		public void setDestination(Vertex newDestination) {
			destination = newDestination;
		}
		
		public void reverseDirection() {
			Vertex temp = destination;
			destination = origin;
			origin = temp;
		}
		
		//Annotators
		/**
		 * Annotates an edge with object o indexed by key k.
		 * @param k is the key.
		 * @param o is the object.
		 */
		public void setAnnotation(Object k, Object o) {
			annotations.put(k, o);
		}

		/**
		 * Returns the object indexed by k annotating an edge.
		 * @param k is the key.
		 * @return the object.
		 */
		public Object getAnnotation(Object k) {
			return annotations.get(k);
		}
		
		/**
		 * Removes the annotation on a vertex or edge indexed by k and returns it.
		 * @param k is the key.
		 * @return the annotation removed.
		 */
		public Object removeAnnotation(Object k) {
			return annotations.remove(k);
		}

		
}
