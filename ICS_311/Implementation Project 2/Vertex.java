import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * ICS 311 Implementation Project 2
 * @author John Rasay
 * @School University OF Hawaii
 * @Class ICS 311
 * @Fall 2012
 *
 */
public class Vertex {
	//Instance Variables
	private int id;
	private Object data;
	private HashMap<Object, Object> annotations;
	ArrayList<Edge> incidenceCollection;
	
	//The Constructor
	public Vertex (Object d) {
		data = d;
		annotations = new HashMap<Object, Object>();
		incidenceCollection = new ArrayList<Edge>();
	}
	
	//Getter Methods
	/**
	 * Returns the the degree of the vertex.
	 * @return the degree of the vertex.
	 */
	public int getDegree() {
		return incidenceCollection.size();
	}
	
	/**
	 * Returns the id of the vertex.
	 * @return the id of the vertex.
	 */
	public int getId() {
		return id;
	}
	
	//Setter Methods
	/**
	 * Sets the id of the vertex.
	 * @param id is the id of the vertex.
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	//mutators
	/**
	 * Insert an edge in the vertex incidence collection.
	 * @param e is the edge to be inserted.
	 */
	public void insertIncidenceCollection(Edge e) {
		incidenceCollection.add(e);
	}
	
	/**
	 * Removes an edge in the vertex incident collection.
	 * @param e is the edge to be remove.
	 */
	public void removeIncidentEdge(Edge e) {
		incidenceCollection.remove(e);
	}
	
	/**
	 * Returns an iterator of the vertex incidence collection.
	 * @return
	 */
	public Iterator<Edge> incidentEdges() {
		return incidenceCollection.iterator();
	}
	
	
	
	// Annotators
	/**
	 * Annotates a vertex with object o indexed by key k
	 * @param k is the key
	 * @param o is the object
	 */
	public void setAnnotation(Object k, Object o) {
		annotations.put(k, o);
	}

	/**
	 * Returns the object indexed by k annotating a vertex or edge.
	 * @param k is the key
	 * @return the object
	 */
	public Object getAnnotation(Object k) {
		return annotations.get(k);
	}
	
	/**
	 * Removes the annotation on a vertex or edge indexed by k and returns it.
	 * @param k is the key
	 * @return the annotation remove.
	 */
	public Object removeAnnotation(Object k) {
		return annotations.remove(k);
	}

}
