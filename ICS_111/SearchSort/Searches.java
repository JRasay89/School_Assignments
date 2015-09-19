/*
 * Searches.java
 *
 * John Rasay
 * ICS 111
 * Last modified: November 15, 2009
 */
 
/**
  * This class contains two searching methods the linear and binary search.
  */
public class Searches
{
   /**
     * This method compares every element in the array with a key and returns
     * once an equal value is found.
     * @param a an array that holds int values.
     * @param key the number that the user inputed.
     * @return i when the key is found
     * @return -1 when the key is not found.
     */
   public static int linearSearch(int[] a, int key) 
   {
      for (int i = 0; i < a.length; i++)
        if (a[i] == key)
	   return i;
        return -1;
   }
   
   /**
     * This method compares the key to the middle of the array and if it equals
     * to the value of the middle the search is done. If the key is less than 
     * the middle then it searches the top part of the array, if its greater
     * than the middle then it searches the bottom part of the array.
     * @param a an array that holds int values.
     * @param key the number that the user inputed.
     * @return -1 when the key is not found.
     */
   public static int binarySearch(int[] a, int key)
   {
      int top = 0;
      int bottom = a.length -1;
      while (top <=  bottom) {
          int middle = (top + bottom)/2;
          if (a[middle] == key)
             return middle;
          if (a[middle] < key)
	     top = middle + 1;
          else
	     bottom = middle - 1;
      }
      return -1;
   }
}

