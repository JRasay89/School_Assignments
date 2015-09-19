/**
 * 
 * @author John Rasay
 * @School University OF Hawaii
 * @Class ICS 311
 *
 *
 */
public class BSTDynamicSet<E extends Comparable<? super E>> {
	
	/*
	 * BinaryTreeNode class implementation, stores the data and a left, right and parent pointer
	 */
	private class BinaryTreeNode {
		//Instance Variables
		private E idata;
		private BinaryTreeNode left;
		private BinaryTreeNode right;
		private BinaryTreeNode parent;
		
		public BinaryTreeNode(E element) {
			idata = element;
		}
	}
	
	//Instance Variables
	private BinaryTreeNode root;
	private int size;
	
	//Constructor
	/*
	 * Constructor of the BSTDynamicSet, sets root to null
	 */
	public BSTDynamicSet() {
		root = null;
	}
	
	/*
	 * This returns the number of elements in the Binary Search Tree
	 * 
	 * @return count
	 */
	public int size() {
		return size;
	}
	
	/*
	 * Adds the specified element to the Binary Search Tree, if the element is not already in the tree
	 * 
	 * @param The element to be inserted
	 */
	public void insert(E element) {
		BinaryTreeNode newNode = new BinaryTreeNode(element);
		if (root == null) {
			root = newNode;
			root.right = null;
			root.left = null;
			root.parent = null;
			size++;
		}
		else {
			BinaryTreeNode temp = root;
			BinaryTreeNode current = root;
			BinaryTreeNode theParent;
			while(true) {
				theParent = current;
				current = temp;
				if (newNode.idata.compareTo(temp.idata) < 0) {		//if element is less than the current item, go left
					temp = temp.left;
					if(temp == null) {
						current.left = newNode;
						if (current.left.left == null) {			//if the new node is a leaf, make the parent current
							current.left.parent = current;
						}
						else {
							current.parent = theParent;
						}
						size++;
						return;
					}
				}
				else {											    //if element is greater than the current item, go right
					temp = temp.right;
					if (temp == null) {
						current.right = newNode;
						if (current.right.right == null) {	        //if the new node is a leaf, make the parent current
							current.right.parent = current;
						}
						else {
							current.parent = theParent;
						}
						size++;
						return;
					}//End of If
					
				}//End of else
			}//End of While
		}//End of else
	}//End of method declaration
	
	
	/*
	 * Deletes the given element in the list
	 */
	public void delete(E element) throws DynamicSetException{
		BinaryTreeNode currentPtr = root;
		BinaryTreeNode theParent = root;
		Boolean isLeftChild = true;
		while  (!element.equals(currentPtr.idata)) {
			theParent = currentPtr;
			if (element.compareTo(currentPtr.idata) < 0) {						//go left if item is smaller
				isLeftChild = true;
				currentPtr = currentPtr.left;
				
			}
			else {
				isLeftChild = false;
				currentPtr = currentPtr.right;			  						// go right
			}
			
			if (currentPtr == null) {											//If item is not on the list, throws an exception
				throw new DynamicSetException("Element is not on the list, cannot Delete.");
			}
		}																		//end of while loop
		
		if (currentPtr.left == null && currentPtr.right == null) {       		//IF node has no children
			if (currentPtr == root) {
				root = null;
			}
			
			else if (isLeftChild) {												//If the node to be deleted is a left child
				theParent.left = null;
			}
			else {													  			//If the node to be deleted is a right child
				theParent.right = null;
			}
			size--;
		}
		
		else if (currentPtr.right == null) {									//If the node to be deleted has only a left child
			if (currentPtr == root) {
				root = currentPtr.left;
			}
			else if(isLeftChild) {
				theParent.left = currentPtr.left;
			}
			else {
				theParent.right = currentPtr.left;
			}
			size--;
		}
	
		else if (currentPtr.left == null) {										//if the node to be deleted has only a right child
			if (currentPtr == root) {
				root = currentPtr.right;
			}
			else if(isLeftChild) {
				theParent.left = currentPtr.right;
			}
			else {
				theParent.right = currentPtr.right;
			}
			size--;
		}
		
		else {														//If the node to be deleted has two children
			BinaryTreeNode theSuccessor = successor(currentPtr);
			if (currentPtr == root) {
				currentPtr.idata = theSuccessor.idata;
				theSuccessor.parent.left = null;
			}
			else if (isLeftChild) {
				currentPtr.idata = theSuccessor.idata;
				theSuccessor.parent.left = null;
			}
			else {
				currentPtr.idata = theSuccessor.idata;
				theSuccessor.parent.left = null;
			}
			size--;
			
		}
	}//End of method
	
	/*
	 * Search for the given element in the Binary Search Tree
	 */
	public E search(E element){
		BinaryTreeNode currentPtr = root;
		if (root == null) {
			return null;
		}
		else {
			while (!element.equals(currentPtr.idata)) {
				if (element.compareTo(currentPtr.idata) < 0) {
					currentPtr = currentPtr.left;
					if (currentPtr == null) {
						return null;
					}
				}
				else if (element.compareTo(currentPtr.idata) > 0) {
					currentPtr = currentPtr.right;
					if (currentPtr == null) {
						return null;
					}
				}
			}
		}
		return currentPtr.idata;	
	}
	
	/*
	 * Finds the minimum element in the list and return a pointer to it
	 * 
	 * @return returns the pointer of the minimum element
	 */
	public E minimum() {
		BinaryTreeNode currentPtr;
		BinaryTreeNode current;
		currentPtr = root;
		current = currentPtr;
		while (currentPtr != null) {
			current = currentPtr;
			currentPtr = currentPtr.left;
		}
		if (current == root) {
			return null;
		}
		return current.idata;
	}
	
	/*
	 * Finds the maximum element in the list and return a pointer to it
	 */
	public E maximum() {
		BinaryTreeNode currentPtr;
		BinaryTreeNode current;
		currentPtr = root;
		current = currentPtr;
		while (currentPtr != null) {
			current = currentPtr;
			currentPtr = currentPtr.right;
		}
		if (current == root) {
			return null;
		}
		else {
			return current.idata;
		}
	}
	/*
	 * Gets the successor of the give element
	 */
	public E getSuccessor(E element) throws DynamicSetException {
		BinaryTreeNode currentPtr = root;
		if (root == null) {
			return null;
		}
		if (element.equals(this.maximum())) {
			throw new DynamicSetException("Item is the largest element in the Tree. ");
		}
		else {
			while (!element.equals(currentPtr.idata)) {
				if (element.compareTo(currentPtr.idata) < 0) {
					currentPtr = currentPtr.left;
					if (currentPtr == null) {						//IF item not found
						throw new DynamicSetException("Item is not in the Tree. ");
					}
				}
				else if (element.compareTo(currentPtr.idata) > 0) {
					currentPtr = currentPtr.right;
					if (currentPtr == null) {
						throw new DynamicSetException("Item is not in the Tree. ");
					}
				}
			}
		}
		currentPtr = successor(currentPtr);
		return currentPtr.idata;
	}
	
	/*
	 * Gets the predecessor of the given element
	 */
	public E getPredecessor(E element) throws DynamicSetException {
		BinaryTreeNode currentPtr = root;
		if (root == null) {
			return null;
		}
		if (element.equals(this.minimum())) {
			throw new DynamicSetException("Item is the smallest element in the Tree. ");
		}
		else {
			while (!element.equals(currentPtr.idata)) {
				if (element.compareTo(currentPtr.idata) < 0) {
					currentPtr = currentPtr.left;
					if (currentPtr == null) {
						throw new DynamicSetException("Item is not in the Tree. ");
					}
				}
				else if (element.compareTo(currentPtr.idata) > 0) {
					currentPtr = currentPtr.right;
					if (currentPtr == null) {
						throw new DynamicSetException("Item is not in the Tree. ");
					}
				}
			}
		}
		currentPtr = predecessor(currentPtr);
		return currentPtr.idata;
	}
	
	/*
	 *Private method to find the successor of the Binary Search Tree
	 * 
	 * @return a node of the successor
	 */
	private BinaryTreeNode successor(BinaryTreeNode itemNode) {
		if (itemNode.right != null) {
			BinaryTreeNode currentPtr;
			BinaryTreeNode current;
			currentPtr = itemNode.right;
			current = itemNode;
			while (currentPtr != null) {
				current = currentPtr;
				currentPtr = currentPtr.left;
			}
			return current;
		}
		BinaryTreeNode current = itemNode.parent;
		while (current != null && itemNode == current.right) {
			itemNode = current;
			current = current.parent;
		}
		return current;
		
	}
	
	/*
	 * Finds the predecessor of the Binary Search Tree
	 * 
	 * @return a node of the predecessor
	 */
	private BinaryTreeNode predecessor(BinaryTreeNode itemNode) {
		if (itemNode.left != null) {
			BinaryTreeNode currentPtr;
			BinaryTreeNode current;
			currentPtr = itemNode.left;
			current = itemNode;
			while (currentPtr != null) {
				current = currentPtr;
				currentPtr = currentPtr.right;
			}
			return current;
		}
		BinaryTreeNode current = itemNode.parent;
		while (current != null && itemNode == current.left) {
			itemNode = current;
			current = current.parent;
		}
		return current;
	}
	
	/*
	 * Clear the entire tree
	 */
	public void clear() {
		root = null;
	}
}
