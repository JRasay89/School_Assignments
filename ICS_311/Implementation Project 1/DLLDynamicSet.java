/**
 * 
 * @author John Rasay
 * @School University OF Hawaii
 * @Class ICS 311
 *
 *
 */
public class DLLDynamicSet<E extends Comparable<? super E>>{
	
    /*
     * The Node is an implementation of a DLL node, which stores a data
     * element and references to the subsequent nodes.
     */
	private class node {
		public E idata;
		public node next;
		public node previous;
		
		public node(E item) {
			idata = item;
		}
		
		public String toString() {
			return "{" + idata + "} ";
		}
	}//End of the private node class 
	
	
	//The Doubly Linked List Instance Variables
	private node head;
	private node tail;
	
	public DLLDynamicSet() {
		head = null;
		tail = null;
	}
	
	/*
	 * Checks if the Linked List is empty
	 * 
	 * @return null the Linked List is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	
	/*
	 * Prints the size of the link list
	 */
	public int size() {
		node currptr = head;
		int size = 0;
		while (currptr != null) {
			currptr = currptr.next;
			size++;
		}
		return size;
	}
	
	/*
	 *	Adds an element at the end of the list, sorts the items from minimum to max,
	 *  Assumes that no duplicates are allowed, and throws an exception if attempted.
	 *  
	 *  @param the item to be added in the list
	 */
	public void insert(E item){
		node newNode = new node(item);
		node currentPtr = head;
		if (head == null) {
			head = newNode;
			tail = head;
			newNode.previous = null;
			newNode.next = null;
		}
		//Insert at the front of the DLL
		else if (item.compareTo(head.idata) < 0) {
			newNode.next = head;
			head.previous = newNode;
			head = newNode;
		}
		//Insert at the end of the DLL
		else if (item.compareTo(tail.idata) > 0 ) {
			newNode.previous = tail;
			tail.next = newNode;
			tail = newNode;
		}
		//Insert in the middle
		else {
			while (item.compareTo(currentPtr.idata) > 0) {
				currentPtr = currentPtr.next;
			}
			newNode.next = currentPtr;
			newNode.previous = currentPtr.previous;
			currentPtr.previous.next = newNode;
			currentPtr.previous = newNode;
		}
			
	}
	
	/**
	 * 
	 * @param item
	 * @throws DynamicSetException if item is not in the list
	 */
	public void delete(String item) throws DynamicSetException {
		node temp = head;
		node temp2;
		while (!temp.idata.equals(item)) {
			temp = temp.next;
			if (temp == null) {
				throw new DynamicSetException("Item is not on the list, cannot Delete.");
			}
		}
		if (temp == head) {
			head = temp.next;
			head.previous = null;
		}
		else if (temp == tail) {
			tail = temp.previous;
			tail.next = null;
		}
		else {
			temp2 = temp.previous;
			temp2.next = temp.next;
			temp.next.previous = temp2;
		}
	}
	
	/*
	 * Search for the given item and return its position
	 * @param the item to be search
	 */
	public E search(E item) {
		node temp = head;
		while (!temp.idata.equals(item)) {
			temp = temp.next;
			if (temp == null) {
				return null;
			}
		}
		return temp.idata;
	}
	
	/*
	 *Returns a pointer to the smallest element in the list
	 */
	public E minimum() {
		if (head == null) {
			return null;
		}
		return head.idata;
	}
	
	//Returns the biggest element in the list
	public E maximum() {
		if (tail == null) {
			return null;
		}
		return tail.idata;
	}
	
	/**
	 * gets the Item's successor in the Linked List
	 * @param item
	 * @return the items successor
	 * @throws DynamicSetException if item not found or item is the successor
	 */
	public E successor(E item) throws DynamicSetException{
		node currentPtr;
		currentPtr = head;
		if (head == null) {					//If List is empty
			throw new DynamicSetException("The list is empty. ");
		}
		while (!item.equals(currentPtr.idata)) {
			currentPtr = currentPtr.next;
			if (currentPtr == null) {
				throw new DynamicSetException("The item is not in the list.");
			}
		}
		if (currentPtr == tail) {
			throw new DynamicSetException("The item is the larges element in the list. ");
		}
		else {
			return currentPtr.next.idata;
		}
	}
	
	/**
	 * Get the predecessor of the item give
	 * @param item
	 * @return the predecessor of the item
	 * @throws DynamicSetException if item not found or item is the predecessor
	 */
	public E predecessor(E item) throws DynamicSetException {
		node currentPtr;
		currentPtr = head;
		if (head == null) {
			throw new DynamicSetException("The list is empty.");
		}
		while (!item.equals(currentPtr.idata)) {
			currentPtr = currentPtr.next;
			if (currentPtr == null) {
				throw new DynamicSetException("The item is not in the list. ");
			}
		}
		if (currentPtr == head) {
			throw new DynamicSetException("The item is the smallest element in the list. ");
		}
		else {
			return currentPtr.previous.idata;
		}
	}
	
	/**
	 ** Returns a string representation of this List
	 **
	 ** @return a string representation of this Doubly Linked List
	 **/
	public String toString() {
		String str = "";
		node currptr = head;
		while (currptr != null) {
			str += currptr.toString();
			currptr = currptr.next;
		}
		return str;
	}
	
	/**
	 ** Clear the entire Doubly Linked List
	 **/
	public void clear() {
		head = null;
	}

}
