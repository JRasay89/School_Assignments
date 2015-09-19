/*
 ** List.java
 ** John Rasay
 ** Honolulu Community College
 ** ICS211
 ** Last modified: March, 12, 2010
 ** The Link List
 */
 
 public class List <T> implements ListInterface<T>
 {
    /**
      * This points to the first link of the list.
      */
    private Node head;
    
    /**
      * This points to the last link of the list.
      */
    private Node tail;
    
    /**
      * This is the length of the list.
      */
    private int length;
    
    /**
      * This creats a list with head and tail set to null and length to 0.
      */
    public List()
    {
       head = null;
       tail = null;
       length = 0;
    }
   /**
     * Add an item to the List. The item is added at the end of the List.
     * @param item The item to be added.
     */
    public void add(T item)
    {
       if (tail == null) {
          head = new Node(item, null);
          tail = head;
       }
       else {
          tail.next = new Node(item, null);
          tail = tail.next;
       }
       length++;
    }
    
    /**
      * Add an item to the List in a specified position. Items already at that
      * position and below (if any) are moved down. Throws a ListException if 
      * the position is invalid.
      * @param item The item to be added.
      * @param position The position on the List for the new item.
      * @throws ListException if the position is invalid.
      */
    public void add(T item, int position) throws ListException
    {
       if (position < 1 || position > length + 1)
	      throw new ListException("Cannot add item, position is invalid.");
       Node insertItem = new Node(item,null);
       if (position == 1) {
	       insertItem.next = head;
          head = insertItem;
          if (tail == null) {
             tail = head;
          }
       }
       else if (position == length + 1) {
          tail.next = insertItem;
          tail = insertItem;
       }
       else {
          int count = 0;
          Node temp = head;
          while (count < position - 2) {
             temp = temp.next;
             count++;
          }
          insertItem.next = temp.next;
          temp.next = insertItem;
       }
       length++;
    }
    
     /**
      * Replace an item on the List in a specified position. The item at 
      * that position is replaced by the new item. Throws a ListException if the
      * position is invalid
      * @param item The item to be added.
      * @param position The position on the List for the new item.
      * @throws ListException if the position is invalid.
      */
    public void replace(T item, int position) throws ListException
    {
       if (position < 1 || position > length)
	      throw new ListException("Cannot replace, position is invalid.");
       if (position == 1)
          head.data = item;
       else {
          int count = 1;
          Node temp = head;
          while (count <= position - 1) {
             temp = temp.next;
             count++;
          }
          temp.data = item;
       }
    }
    
    /**
      * Remove an item from the list. If there are duplicate copies of the item
      * on the list, all duplicates are also removed. If the item is not on the 
      * list, an exception is thrown. Items below the removed items, if any,
      * are moved up.
      * @param item The item to be added.
      * @throws ListException if the position is invalid.
      */
    public void remove(T item) throws ListException
    {
       int position = contains(item);
       if (position == 0)
          throw new ListException("Item is not on the list, cannot remove.");
       while (position > 0) {
          remove(position);
          position = contains(item);
       }
    }
    
    /** 
      * Remove the item at the specified position from the list. If the 
      * specified position is invalid, an exception is thrown.
      * @param position The position on the List for the new item.
      * @throws ListException if the position is invalid.
      */
    public void remove(int position) throws ListException
    {
       if (position < 1 || position > length)
          throw new ListException("Cannot remove item, position is invalid.");
       if (position == 1) {
          head = head.next;
          if (length == 1)
             tail = head;
       }
       else {
          int count = 1;
          Node temp = head;
          while (count < position - 1) {
             temp = temp.next;
             count++;
          }
          temp.next = temp.next.next;
          if (position == length)
             tail = temp;
       }
       length--;
    }
    
    /** 
      * Retrieve(without removing) the item at the position specified. If the
      * specified position is invalid, an exception is thrown.
      * @param position The position on the list for the new item.
      * @throws ListException if the position is invalid.
      */
     public T retrieve(int position) throws ListException
     {
        if (position < 1 || position > length)
           throw new ListException("Cannot retrieve item, invalid position.");
        Node temp = head;
        int count = 1;
        while (count < position) {
           temp = temp.next;
           count++;
        }
        return temp.data;
     }
     
    /**
      * Get the length of the list.
      * @return length of the list.
      */
     public int length()
     {
        return length;
     }
	   
    /**
      * Search the list for an item. Return the position of the first matching
      * item on the list. Return 0 if the item is not on the list.
      * @param item The item to be added.
      * @return position of the item.
      * @return 0 if item is not present.
      */
    public int contains(T item)
    {
       Node temp = head;
       int position = 1;
       while (temp != null) {
          if (temp.data.equals(item))
             return position;
          position++;
          temp = temp.next;
       }
       return 0;
    }
     
    /**
      * Determine whether or not the list is empty
      * @return true if list is empty
      * @return false if list is not empty.
      */
    public boolean isEmpty()
    {
       if (head == null)
          return true;
       else
          return false;
    }
     
     /**
       * The equals method checks to see if the List is equal to the parameter.
       * @param other The Object being compared to the List.
       * @return true if the List and the parameter are the same, 
       *         false otherwise.
       */
    public boolean equals(Object other)
    {
       if (other == null || getClass() != other.getClass())
          return false;
       List temp = (List) other;
       if (length() != temp.length())
          return false;
       try {
          for (int i = 1; i <= length(); i++)
             if ( ! retrieve(i).equals(temp.retrieve(i)))
                return false;
       }
       catch (ListException e) {
          return false;
       }
       return true;
    }
     
    class Node
    {
       /**
         * The data inside the Node.
	      */
       T data;
       
       /**
         * The  next link of the node.
	      */
       Node next;
     
       /**
         * Creates a node with the item, and the next node.
	      */
       Node(T item, Node nextNode)
       {
          data = item;
          next = nextNode;
       }
    }
 } 
