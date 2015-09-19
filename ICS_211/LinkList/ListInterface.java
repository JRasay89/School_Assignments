/*
 *
 ** John Rasay
 ** ListInterface.java
 ** Honolulu Community College
 ** Last Modified on January 15, 2010
 */
 
 /**
   * An interface for the ADT List.
   * The first item on a List is at position 1.
   */
 public interface ListInterface<T>
 {
 
    /**
      * Add an item to the List. The item is added at the end of the List.
      * @param item to be added.
      */
    public void add(T item);
    
    /**
      * Add an item to the List in a specified position. Items already at that
      * position and below (if any) are moved down. Throws a ListException if 
      * the position is invalid.
      * @param item The item to be added.
      * @param position on the List for the new item.
      * @throws ListException if the position is invalid.
      */
    public void add(T item, int position) throws ListException;
    
    /**
      * Replace an item on the List in a specified position. The item at 
      * that position is replaced by the new item. Throws a ListException if the
      * position is invalid
      * @param item The item to be added.
      * @param position on the List for the new item.
      * @throws ListException if the position is invalid.
      */
    public void replace(T item, int position) throws ListException;
    
    /**
      * Remove an item from the list. If there are duplicate copies of the item
      * on the list, all duplicates are also removed. If the item is not on the 
      * list, an exception is thrown. Items below the removed items, if any,
      * are moved up.
      * @param item The item to be added.
      * @throws ListException if the position is invalid.
      */
    public void remove(T item) throws ListException;
    
    /** 
      * Remove the item at the specified position from the list. If the 
      * specified position is invalid, an exception is thrown.
      * @param position on the List for the new item.
      * @throws ListException if the position is invalid.
      */
    public void remove(int position) throws ListException;
    
    /** 
      * Retrieve(without removing) the item at the position specified. If the
      * specified position is invalid, an exception is thrown.
      * @param position on the list for the new item.
      * @throws ListException if the position is invalid.
      */
    public T retrieve(int position) throws ListException;
    
    /**
      * Get the length of the list.
      * @return length of the list.
      */
    public int length();
    
    /**
      * Search the list for an item. Return the position of the first matching
      * item on the list. Return 0 if the item is not on the list.
      * @param item to be added.
      * @return position of the item
      */
    public int contains(T item);
    
    /**
      * Determine whether or not the list is empty
      * @return true if empty, false otherwise.
      */
    public boolean isEmpty();
    
 }

